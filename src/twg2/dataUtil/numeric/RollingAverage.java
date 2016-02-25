package twg2.dataUtil.numeric;


/** Utility methods for calculating rolling average values.
 * @author TeamworkGuy2
 * @since 2014-12-15
 */
public final class RollingAverage {

	private RollingAverage() { throw new AssertionError("cannot instantiate static class RollingAverage"); }


	/** Update this slide average value with a new value
	 * @param average the current sliding average
	 * @param newValue the next value to average into the sliding average
	 * @param newValueWeight the weight between {@code [0, 1.0]} of newly added values
	 * @return the new sliding average
	 */
	public static final double updateAvg(float average, int newValue, float newValueWeight) {
		float diff = (newValue - average);
		return (average * (1 - newValueWeight)) + (diff * newValueWeight);
	}


	/** Update this slide average value with a new value
	 * @param average the current sliding average
	 * @param newValue the next value to average into the sliding average
	 * @param newValueWeight the weight between {@code [0, 1.0]} of newly added values
	 * @return the new sliding average
	 */
	public static final double updateAvg(float average, float newValue, float newValueWeight) {
		float diff = (newValue - average);
		return (average * (1 - newValueWeight)) + (diff * newValueWeight);
	}


	/** Update this slide average value with a new value
	 * @param average the current sliding average
	 * @param newValue the next value to average into the sliding average
	 * @param newValueWeight the weight between {@code [0, 1.0]} of newly added values
	 * @return the new sliding average
	 */
	public static final double updateAvg(double average, long newValue, double newValueWeight) {
		double diff = (newValue - average);
		return (average * (1 - newValueWeight)) + (diff * newValueWeight);
	}


	/** Update this slide average value with a new value
	 * @param average the current sliding average
	 * @param newValue the next value to average into the sliding average
	 * @param newValueWeight the weight between {@code [0, 1.0]} of newly added values
	 * @return the new sliding average
	 */
	public static final double updateAvg(double average, double newValue, double newValueWeight) {
		double diff = (newValue - average);
		return (average * (1 - newValueWeight)) + (diff * newValueWeight);
	}

}
