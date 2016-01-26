package dataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * @author TeamworkGuy2
 * @since 2016-1-20
 */
public class ParallelWork {

	private ParallelWork() { throw new AssertionError("cannot instantiate static class ParallelWork"); }


	/** Contains a {@link ExecutorService} and work block size which can be used to split up a collection of work into chunks and execute it
	 * @author TeamworkGuy2
	 * @since 2016-1-20
	 */
	public static class WorkBlockPolicy {

		enum PolicyAlgorithm {
			FIXED_BLOCK_SIZE() {
				@Override public long calc(long totalWorkSize, double fixedBlockSize) {
					return (long)fixedBlockSize;
				}
			},
			PERCENTAGE_BLOCK_SIZE() {
				@Override public long calc(long totalWorkSize, double percentageBlockSize) {
					return Math.max((long)Math.floor(totalWorkSize * percentageBlockSize), 1);
				}
			};

			public abstract long calc(long totalWorkSize, double workBlockSize);
		}

		private final ExecutorService executor;
		private final double workBlockSize;
		private final PolicyAlgorithm type;

		/**
		 * @param executor
		 * @param workBlockSize
		 */
		private WorkBlockPolicy(ExecutorService executor, double workBlockSize, PolicyAlgorithm type) {
			super();
			this.executor = executor;
			this.workBlockSize = workBlockSize;
			this.type = type;
		}


		public int getWorkBlockSize(int totalWorkSize) {
			return (int)type.calc(totalWorkSize, workBlockSize);
		}


		public static final WorkBlockPolicy newFixedBlockSize(int workBlockSize, ExecutorService executor) {
			return new WorkBlockPolicy(executor, workBlockSize, PolicyAlgorithm.FIXED_BLOCK_SIZE);
		}


		public static final WorkBlockPolicy newPercentageOfTotalBlockSize(double workPercentageBlockSize, ExecutorService executor) {
			return new WorkBlockPolicy(executor, workPercentageBlockSize, PolicyAlgorithm.PERCENTAGE_BLOCK_SIZE);
		}

	}




	/** If any of the units of work throw an exception while executing, the resulting exception is wrapped
	 * in a {@link RuntimeException} and thrown from this method
	 * @param policy
	 * @param data
	 * @param worker
	 * @return the input data transformed through the worker function, using the {@link WorkBlockPolicy}
	 */
	public static final <T, R> List<R> transformBlocks(WorkBlockPolicy policy, List<T> data, Function<T, R> worker) {
		ExecutorService executor = policy.executor;
		int dataCount = data.size();
		int blockSize = policy.getWorkBlockSize(dataCount);
		int blockCount = dataCount / blockSize;
		int remaining = dataCount % blockSize;
		int worksetCount = blockCount + (remaining > 0 ? 1 : 0);
		@SuppressWarnings("unchecked")
		Callable<List<R>>[] tasks = new Callable[worksetCount];

		// run executor for each block
		for(int i = 0; i < blockCount; i++) {
			final int ival = i;
			tasks[ival] = () -> {
				List<R> resSet = transformSubBlock(data, ival * blockSize, blockSize, worker, new ArrayList<>());
				return resSet;
			};
		}
		// run last executor for remaining
		if(remaining > 0) {
			tasks[worksetCount - 1] = () -> {
				List<R> resSet = transformSubBlock(data, dataCount - remaining, remaining, worker, new ArrayList<>());
				return resSet;
			};
		}

		// TODO debugging
		System.out.println("transform count: " + worksetCount);

		List<Future<List<R>>> resultSets;
		try {
			resultSets = executor.invokeAll(Arrays.asList(tasks));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		return flattenFutureResultSets(resultSets);
	}


	public static final <T, R, U extends List<R>> U transformSubBlock(List<T> data, int off, int len, Function<T, R> worker, U dst) {
		return transformSubBlock(data, off, len, worker, dst, false, null);
	}


	public static final <T, R, U extends List<R>> U transformSubBlock(List<T> data, int off, int len, Function<T, R> worker, U dst, boolean dstSynchronized, Object dstSyncLock) {
		if(dstSynchronized) {
			for(int i = off, size = off + len; i < size; i++) {
				R res = worker.apply(data.get(i));
				synchronized (dstSyncLock) {
					dst.add(res);
				}
			}
		}
		else {
			for(int i = off, size = off + len; i < size; i++) {
				R res = worker.apply(data.get(i));
				dst.add(res);
			}
		}
		return dst;
	}


	private static final <T> List<T> flattenFutureResultSets(List<Future<List<T>>> workSets) {
		// flatten the nested result list
		List<T> results = new ArrayList<>();
		for(int i = 0, size = workSets.size(); i < size; i++) {
			List<T> resSet;
			try {
				resSet = workSets.get(i).get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}
			for(int ii = 0, sizeI = resSet.size(); ii < sizeI; ii++) {
				results.add(resSet.get(ii));
			}
		}
		return results;
	}

}
