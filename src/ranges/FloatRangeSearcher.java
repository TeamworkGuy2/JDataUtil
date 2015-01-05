package ranges;

/**
 * @author TeamworkGuy2
 * @since 2014-12-13
 * @see FloatRange
 * @see FloatSearcher
 */
@javax.annotation.Generated("StringTemplate")
public interface FloatRangeSearcher extends FloatRange, FloatSearcher {

	@Override
	public boolean isMatch(float val);


	@Override
	public int indexOfMatch(float val);


	@Override
	public float getLowerBound(int i);


	@Override
	public float getUpperBound(int i);


	@Override
	public int size();

}
