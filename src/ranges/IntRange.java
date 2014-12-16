package ranges;

/** An integer range set represents multiple sets of contiguous integer.
 * Each range is represented by an inclusive lower and inclusive upper bound.<br>
 * For example a IntRange with {@code size()==1} that returns
 * {@code getLowerBound(0)==20} and {@code getUpperBound(0)==150}
 * matches all integers between {@code 20} and {@code 150} including {@code 20} and {@code 150}.<br>
 * An IntRange may contain multiple integer ranges, for example, 0 through 10
 * and 90 through 100.
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
public interface IntRange {

	/**
	 * @param val the sub range index to retrieve the lower bound of
	 * @return the least integer, inclusive, of a specific sub range in this IntRange
	 */
	public int getLowerBound(int val);


	/**
	 * @param val the sub range index to retrieve the upper bound of
	 * @return the greatest integer, inclusive, of a specific sub range in this IntRange
	 */
	public int getUpperBound(int val);

}