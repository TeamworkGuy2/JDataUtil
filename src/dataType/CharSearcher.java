package dataType;

/** A condition to check if {@link Character Characters} meet a specific criteria
 * @author TeamworkGuy2
 * @since 2014-11-2
 */
public interface CharSearcher {


	/** Check if {@code ch} matches one of the ranges specified by this CharRangeSearcher
	 * @param ch the character to check
	 * @return true if the specified character is within one of the ranged
	 * defined by this CharRangeSearcher
	 */
	public boolean isMatch(int ch);


	/** Check if {@code ch} matches one of the ranges specified by this CharRangeSearcher
	 * @param ch the character to check
	 * @return the index of the sub range {@code ch} is in, or -1 if {@code ch}
	 * is not in any of this CharRangeSearcher's sub ranges
	 */
	public int indexOfMatch(int ch);


	/**
	 * @return the number of char sub ranges in this CharRangeSearcher
	 */
	public int size();

}
