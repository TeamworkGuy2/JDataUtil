package ranges;

/**
 * @author TeamworkGuy2
 * @since 2014-12-13
 * @see IntRange
 * @see IntSearcher
 */
public interface IntRangeSearcher extends IntRange, IntSearcher {

	@Override
	public boolean isMatch(int val);


	@Override
	public abstract int indexOfMatch(int val);


	@Override
	public abstract int getLowerBound(int i);


	@Override
	public abstract int getUpperBound(int i);


	/**
	 * @return the number of integer sub ranges in this IntRangeSearcher
	 */
	public int size();

}