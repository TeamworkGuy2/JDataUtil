package ranges;

/** An int range set represents multiple sets of contiguous Integers.
 * Each range is represented by an inclusive lower and inclusive upper bound.<br>
 * For example a IntRange with {@code size()==1} that returns
 * {@code getLowerBound(0)==20} and {@code getUpperBound(0)==150}
 * matches all Integers between {@code 20} and {@code 150} including {@code 20} and {@code 150}.<br>
 * An IntRange may contain multiple Integers ranges, for example, 0 through 10
 * and 90 through 100.
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
@javax.annotation.Generated("StringTemplate")
public interface IntRange {

	/**
	 * @param val the sub range index to retrieve the lower bound of
	 * @return the least Integer, inclusive, of a specific sub range in this IntRange
	 */
	public int getLowerBound(int index);


	/**
	 * @param val the sub range index to retrieve the upper bound of
	 * @return the greatest Integer, inclusive, of a specific sub range in this IntRange
	 */
	public int getUpperBound(int index);


	/**
	 * @return the number of int sub ranges in this IntRange
	 */
	public int size();

}
