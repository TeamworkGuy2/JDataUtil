package dataUtility;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/** A class for calculating the amount of time between update calls and
 * estimating the best update point to run an internal task.
 * This is useful for synchronizing between the end of a high frequency
 * thread task and the end of a low frequency thread task.<br/> 
 * This method could be called once each time the fast thread runs and
 * will calculate which run is best point to synchronize with the end
 * of the slow thread.<br/>
 * For example if one thread is running at 60 hz and another thread is
 * running at 20 hz and this task is run with the 60 hz thread, then this
 * task will run it's internal task every third cycle of the 60 hz thread
 * to match the rate of the 20 hz thread.<br/>
 * Useful when used in conjunction with a {@link CyclicBarrier}
 * @see CyclicBarrier
 * @author TeamworkGuy2
 * @since 2014-12-15
 */
public final class RunTaskInterval {
	/** The weight used when recalculating the average run time based on
	 * the previous run times and the newest run time */
	private static final float AVERAGE_NEW_WEIGHT = 0.25f;

	private Runnable task;
	private double avgNewValueWeight;
	private float frequency;
	private double betweenUpdatesNano;
	// timing info for the last {@code task} run
	private long lastUpdateNano;
	private double updateAverage;
	// timing info for the last {@link #updatingSlidingAvg} call
	private long lastCallNano;
	private double callAverage;


	public RunTaskInterval(float frequencyPerSecond) {
		this(frequencyPerSecond, AVERAGE_NEW_WEIGHT, System.nanoTime(), null);
	}


	public RunTaskInterval(float frequencyPerSecond, double newValueWeight) {
		this(frequencyPerSecond, newValueWeight, System.nanoTime(), null);
	}


	public RunTaskInterval(float frequencyPerSecond, long currentTimeNano) {
		this(frequencyPerSecond, AVERAGE_NEW_WEIGHT, currentTimeNano, null);
	}


	public RunTaskInterval(float frequencyPerSecond, double newValueWeight, long currentTimeNano) {
		this(frequencyPerSecond, newValueWeight, currentTimeNano, null);
	}


	public RunTaskInterval(float frequencyPerSecond, Runnable task) {
		this(frequencyPerSecond, AVERAGE_NEW_WEIGHT, System.nanoTime(), task);
	}


	public RunTaskInterval(float frequencyPerSecond, double newValueWeight, Runnable task) {
		this(frequencyPerSecond, newValueWeight, System.nanoTime(), task);
	}


	public RunTaskInterval(float frequencyPerSecond, long currentTimeNano, Runnable task) {
		this(frequencyPerSecond, AVERAGE_NEW_WEIGHT, currentTimeNano, task);
	}


	/** Create an updatable time task that tries to runs a task at a specific
	 * frequency regardless of the update frequency of this object.
	 * @param frequencyPerSecond the number of times per second to run the
	 * task.  This value may be a frame rate like 60 (every 16.7 milliseconds),
	 * or a timer like 0.1 (once every 10 seconds)
	 * @param newValueWeight the weight between {@code [0, 1.0]} of newly added values
	 * @param currentTimeNano the optional start time of this task in nanoseconds.
	 * May allow the timer to better smooth the average time of its first few runs.
	 * @param task the task to run at the specified frequency
	 */
	public RunTaskInterval(float frequencyPerSecond, double newValueWeight, long currentTimeNano, Runnable task) {
		this.task = task;
		this.avgNewValueWeight = newValueWeight;
		this.frequency = frequencyPerSecond;
		this.betweenUpdatesNano = TimeUnit.SECONDS.toNanos(1) / frequencyPerSecond;
		this.updateAverage = frequencyPerSecond;
		this.callAverage = frequencyPerSecond;
		this.lastUpdateNano = currentTimeNano;
		this.lastCallNano = currentTimeNano;
	}



	/** Calculate if the internal task should be run based on past update
	 * times and the current {@link System#nanoTime()} to match the requested frequency.
	 */
	public void updateRollingAvg() {
		updateRollingAvg(System.nanoTime());
	}


	/** Calculate if the internal task should be run based on past update
	 * times and the provided time to match the requested frequency.
	 * @param timeNano the current time in nano seconds
	 */
	public void updateRollingAvg(long timeNano) {
		long sinceLastUpdateNano = (timeNano - lastUpdateNano);
		// Number of nanoseconds until task should run.
		// Positive values are in the future, negative values are in the past
		double currentVsTargetDiff = betweenUpdatesNano - sinceLastUpdateNano;
		double nextVsTargetDiff = betweenUpdatesNano - (sinceLastUpdateNano + callAverage);

		// If the current time is closer to the target than the next estimated
		// time, or the current time is past the target time by over the time
		// between updates, then run the task
		if(Math.abs(currentVsTargetDiff) < Math.abs(nextVsTargetDiff) || currentVsTargetDiff < -(betweenUpdatesNano / 2)) {
			// Update the average nano seconds between running the task
			updateAverage = (updateAverage * (1 - avgNewValueWeight)) + (sinceLastUpdateNano * avgNewValueWeight);
			lastUpdateNano = timeNano;
			if(task != null) {
				task.run();
			}
		}

		// Update the average nanoseconds between running this method
		long sinceLastCallNano = (timeNano - lastCallNano);
		callAverage = (callAverage * (1 - avgNewValueWeight)) + (sinceLastCallNano * avgNewValueWeight);
		lastCallNano = timeNano;
	}


	public void setTask(Runnable task) {
		this.task = task;
	}


	/**
	 * @return the target frequency at which this task will attempt to run.
	 * This value is relative to 1 second, once every 10 seconds is 0.1, once
	 * every 16.7 milliseconds is 60.
	 */
	public float getTaskFrequency() {
		return frequency;
	}

}
