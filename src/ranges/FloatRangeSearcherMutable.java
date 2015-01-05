package ranges;

/** A mutable {@link FloatRangeSearcher} that allows for the addition and removal of ranges.<br>
 * 
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
@javax.annotation.Generated("StringTemplate")
public interface FloatRangeSearcherMutable extends FloatRangeSearcher {


	/** Add a range to this set of ranges
	 * @param start the inclusive start of the range
	 * @param end the inclusive end of the range
	 */
	public void addRange(float start, float end);


	/** Remove the specified range
	 * @param index the range to remove, between {@code [0, }{@link #size()}{@code -1]}
	 * @return true if the range was successfully removed, false if not
	 */
	public boolean removeRangeAt(int index);


	/** Remove an existing range that exactly matches the specified range
	 * @param start the inclusive start of the range
	 * @param end the inclusive end of the range
	 * @return true if an equal range was found and successfully removed, false if not
	 */
	public boolean removeEqualRange(float start, float end);


	/** Remove any portions of existing ranges that include the specified range
	 * @param start the inclusive start of the range
	 * @param end the inclusive end of the range
	 */
	public void removeRange(float start, float end);


	/** Create an immutable copy of this float range set
	 * @return an immutable copy of this float range set
	 */
	public FloatRangeSearcher toImmutable();

}
