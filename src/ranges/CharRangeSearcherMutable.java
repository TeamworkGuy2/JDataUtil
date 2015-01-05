package ranges;

/** A mutable {@link CharRangeSearcher} that allows for the addition and removal of ranges.<br>
 * 
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
@javax.annotation.Generated("StringTemplate")
public interface CharRangeSearcherMutable extends CharRangeSearcher {


	/** Add a range to this set of ranges
	 * @param start the inclusive start of the range
	 * @param end the inclusive end of the range
	 */
	public void addRange(char start, char end);


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
	public boolean removeEqualRange(char start, char end);


	/** Remove any portions of existing ranges that include the specified range
	 * @param start the inclusive start of the range
	 * @param end the inclusive end of the range
	 */
	public void removeRange(char start, char end);


	/** Create an immutable copy of this char range set
	 * @return an immutable copy of this char range set
	 */
	public CharRangeSearcher toImmutable();

}
