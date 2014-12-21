package ranges;

/**
 * @author TeamworkGuy2
 * @since 2014-12-13
 * @see CharRange
 * @see CharSearcher
 */
public interface CharRangeSearcher extends CharRange, CharSearcher {

	@Override
	public boolean isMatch(int ch);


	@Override
	public int indexOfMatch(int ch);


	@Override
	public int getLowerBound(int ch);


	@Override
	public int getUpperBound(int ch);


	/**
	 * @return the number of char sub ranges in this CharRangeSearcher
	 */
	public int size();

}
