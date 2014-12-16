package ranges;

/** A mutable {@link IntRange} that allows for the addition and removal of ranges.<br>
 * 
 * @author TeamworkGuy2
 * @since 2014-12-13
 * @see IntRange
 */
public interface IntRangeSearcherMutable extends IntRangeSearcher {


	/** Add a range to this set of ranges
	 * @param start the inclusive start of the range
	 * @param end the inclusive end of the range
	 */
	public void addRange(int start, int end);


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
	public boolean removeEqualRange(int start, int end);


	/** Remove any portions of existing ranges that include the specified range
	 * @param start the inclusive start of the range
	 * @param end the inclusive end of the range
	 */
	public void removeRange(int start, int end);


	/** Create an immutable copy of this integer range set
	 * @return an immutable copy of this integer range set
	 */
	public IntRange toImmutable();

}