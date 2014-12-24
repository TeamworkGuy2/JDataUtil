package ranges;

/** A condition to check if {@link Integer Integers} meet a specific criteria
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
public interface IntSearcher {

	/** Check if {@code val} matches one of the ranges specified by this IntSearcher
	 * @param val the int to check
	 * @return true if the specified int is within one of the ranged
	 * defined by this IntSearcher
	 */
	public boolean isMatch(int val);


	/** Check if {@code val} matches one of the ranges specified by this IntSearcher
	 * @param val the int to check
	 * @return the index of the sub range {@code val} is in, or -1 if {@code val}
	 * is not in any of this IntSearcher's sub ranges
	 */
	public int indexOfMatch(int val);

}
