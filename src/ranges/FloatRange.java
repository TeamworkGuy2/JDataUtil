package ranges;

/** An float range set represents multiple sets of contiguous Floats.
 * Each range is represented by an inclusive lower and inclusive upper bound.<br>
 * For example a FloatRange with {@code size()==1} that returns
 * {@code getLowerBound(0)==20} and {@code getUpperBound(0)==150}
 * matches all Floats between {@code 20} and {@code 150} including {@code 20} and {@code 150}.<br>
 * An FloatRange may contain multiple Floats ranges, for example, 0 through 10
 * and 90 through 100.
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
@javax.annotation.Generated("StringTemplate")
public interface FloatRange {

	/**
	 * @param val the sub range index to retrieve the lower bound of
	 * @return the least Float, inclusive, of a specific sub range in this FloatRange
	 */
	public float getLowerBound(int index);


	/**
	 * @param val the sub range index to retrieve the upper bound of
	 * @return the greatest Float, inclusive, of a specific sub range in this FloatRange
	 */
	public float getUpperBound(int index);


	/**
	 * @return the number of float sub ranges in this FloatRange
	 */
	public int size();

}
