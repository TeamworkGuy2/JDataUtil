package ranges;

/**
 * @author TeamworkGuy2
 * @since 2014-12-13
 * @see CharRange
 * @see CharSearcher
 */
public interface CharRangeSearcher extends CharRange, CharSearcher {

	@Override
	public boolean isMatch(char val);


	@Override
	public int indexOfMatch(char val);


	@Override
	public char getLowerBound(int i);


	@Override
	public char getUpperBound(int i);


	@Override
	public int size();

}
