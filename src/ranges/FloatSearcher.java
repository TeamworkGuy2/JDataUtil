package ranges;

/** A condition to check if {@link Float Floats} meet a specific criteria
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
@javax.annotation.Generated("StringTemplate")
public interface FloatSearcher {

	/** Check if {@code val} matches one of the ranges specified by this FloatSearcher
	 * @param val the float to check
	 * @return true if the specified float is within one of the ranged
	 * defined by this FloatSearcher
	 */
	public boolean isMatch(float val);


	/** Check if {@code val} matches one of the ranges specified by this FloatSearcher
	 * @param val the float to check
	 * @return the index of the sub range {@code val} is in, or -1 if {@code val}
	 * is not in any of this FloatSearcher's sub ranges
	 */
	public int indexOfMatch(float val);

}
