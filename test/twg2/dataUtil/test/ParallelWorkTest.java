package twg2.dataUtil.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import twg2.dataUtil.dataUtils.ParallelWork;
import twg2.dataUtil.dataUtils.ParallelWork.WorkBlockPolicy;
import checks.CheckCollections;

/**
 * @author TeamworkGuy2
 * @since 2016-1-20
 */
public class ParallelWorkTest {

	@Test
	public void transformBlocks() {
		List<Character> data = Arrays.asList(
			'A', 'B', 'C', 'D', 'F', 'I', 'J', 'M', 'P', 'V', 'W'
		);
		Function<Character, Integer> worker = (ch) -> {
			// TODO testing (do threads interleave)
			System.out.println("data '" + ch + "' (thread: " + Thread.currentThread().getName() + ")");

			// just some extra work to slow down the threads (hopefully allow them to interleave/context switch)
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i <= ch; i++) {
				list.add(expensiveIntToInt((int)ch));
			}
			return list.get(ch);
		};
		List<Integer> expect = Arrays.asList(
			65, 66, 67, 68, 70, 73, 74, 77, 80, 86, 87
		);

		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		List<Integer> res;
		for(int i = 0; i < 3; i++) {
			res = ParallelWork.transformBlocks(WorkBlockPolicy.newFixedBlockSize(3, executor), data, worker);
			CheckCollections.assertLooseEquals(expect, res);

			res = ParallelWork.transformBlocks(WorkBlockPolicy.newPercentageOfTotalBlockSize(0.2, executor), data, worker);
			CheckCollections.assertLooseEquals(expect, res);
		}

		executor.shutdown();
	}


	@Test
	public void expensiveIntToIntTest() {
		for(int i = -5; i <= 5; i++) {
			Assert.assertEquals(i, expensiveIntToInt(i));
		}
	}


	// for use with thread tests (to give them something to do that hopefully isn't optimized away)
	private static int expensiveIntToInt(int value) {
		int absValue = Math.abs(value);

		// silly (but fun!) loop to create a string builder containing each integer from 0 to 'absValue' as a string
		List<StringBuilder> parts = new ArrayList<>();
		for(int i = 0; i <= absValue; i++) {
			parts.add(new StringBuilder().append(i));
		}

		// N is positive

		// sum of integers from 0 to N
		Integer sum = Integer.valueOf(0);
		for(int i = 0; i < parts.size(); i++) {
			sum += Integer.parseInt(parts.get(i).toString());
		}

		// sum of integers from 0 to N = (x * (x - 1) / 2), except for 1 and 0
		int calcSum = absValue > 1 ? (absValue * (absValue + 1) / 2) : absValue;

		if(calcSum != sum) {
			throw new IllegalArgumentException("sum of integers from 1 to " + absValue + " summed " + sum + " does not equal calculated " + calcSum);
		}

		int res = Integer.parseInt(new String(new StringBuilder().append(value).toString().toCharArray()));
		return res;
	}

}
