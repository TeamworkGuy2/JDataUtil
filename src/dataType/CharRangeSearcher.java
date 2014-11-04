package dataType;

/** A char range set represents multiple sets of contiguous characters.
 * The character ranges support 32-bit characters, and each range
 * is represented by an inclusive lower and inclusive upper bound.<br>
 * For example a CharRangeSearcher with {@code size()==1} that returns
 * {@code getLowerBound(0)=='A'} and {@code getUpperBound(0)=='Z'}
 * represents all characters between {@code 'A'} and {@code 'Z'}, including 'A' and 'Z'.<br>
 * A CharRangeSearcher may contain multiple character ranges, for example, 'A' through 'Z'
 * and '0' through '9'.
 * @author TeamworkGuy2
 * @since 2014-10-29
 */
public interface CharRangeSearcher extends CharSearcher {


	/**
	 * @param i the sub range index to retrieve the lower bound of
	 * @return the least char, inclusive, of a specific sub range in this CharRangeSearcher
	 */
	public int getLowerBound(int i);


	/**
	 * @param i the sub range index to retrieve the upper bound of
	 * @return the greatest char, inclusive, of a specific sub range in this CharRangeSearcher
	 */
	public int getUpperBound(int i);

}
