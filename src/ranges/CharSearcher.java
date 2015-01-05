package ranges;

/** A condition to check if {@link Character Characters} meet a specific criteria
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
@javax.annotation.Generated("StringTemplate")
public interface CharSearcher {

	/** Check if {@code val} matches one of the ranges specified by this CharSearcher
	 * @param val the char to check
	 * @return true if the specified char is within one of the ranged
	 * defined by this CharSearcher
	 */
	public boolean isMatch(char val);


	/** Check if {@code val} matches one of the ranges specified by this CharSearcher
	 * @param val the char to check
	 * @return the index of the sub range {@code val} is in, or -1 if {@code val}
	 * is not in any of this CharSearcher's sub ranges
	 */
	public int indexOfMatch(char val);

}
