package ranges;

/** An char range set represents multiple sets of contiguous Characters.
 * Each range is represented by an inclusive lower and inclusive upper bound.<br>
 * For example a CharRange with {@code size()==1} that returns
 * {@code getLowerBound(0)==20} and {@code getUpperBound(0)==150}
 * matches all Characters between {@code 20} and {@code 150} including {@code 20} and {@code 150}.<br>
 * An CharRange may contain multiple Characters ranges, for example, 0 through 10
 * and 90 through 100.
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
@javax.annotation.Generated("StringTemplate")
public interface CharRange {

	/**
	 * @param val the sub range index to retrieve the lower bound of
	 * @return the least Character, inclusive, of a specific sub range in this CharRange
	 */
	public char getLowerBound(int index);


	/**
	 * @param val the sub range index to retrieve the upper bound of
	 * @return the greatest Character, inclusive, of a specific sub range in this CharRange
	 */
	public char getUpperBound(int index);


	/**
	 * @return the number of char sub ranges in this CharRange
	 */
	public int size();

}
