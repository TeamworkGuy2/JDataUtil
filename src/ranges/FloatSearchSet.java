package ranges;

import primitiveCollections.FloatListSorted;

/** A {@link FloatSearcher} that contains a {@link FloatRange}
 * and a {@link CharSearcherMutable}
 * @author TeamworkGuy2
 * @since 2014-11-2
 */
@javax.annotation.Generated("StringTemplate")
public final class FloatSearchSet implements FloatSearcher {
	private FloatListSorted values;
	private FloatRangeSearcherMutableImpl ranges;
	private boolean locked;


	public FloatSearchSet() {
		this.values = new FloatListSorted();
		this.ranges = new FloatRangeSearcherMutableImpl();
	}


	@Override
	public boolean isMatch(float ch) {
		return values.indexOf(ch) > -1 || ranges.isMatch(ch);
	}


	@Override
	public int indexOfMatch(float ch) {
		int index = values.indexOf(ch);
		if(index > -1) {
			return index;
		}
		else {
			index = ranges.indexOfMatch(ch);
			if(index > -1) {
				return values.size() + index;
			}
			else {
				return -1;
			}
		}
	}


	public int size() {
		return values.size() + ranges.size();
	}


	public void addFloat(float ch) {
		checkLocked();
		if(values.contains(ch)) {
			throw new IllegalArgumentException("duplicate searcher float '" + ch + "'");
		}
		values.add(ch);
	}


	public boolean removeFloatAt(int index) {
		checkLocked();
		values.remove(index);
		return false;
	}


	public boolean removeFloat(float ch) {
		checkLocked();
		return values.removeValue(ch);
	}


	public void addRange(float start, float end) {
		checkLocked();
		ranges.addRange(start, end);
	}


	public boolean removeRangeAt(int index) {
		checkLocked();
		ranges.removeRangeAt(index);
		return true;
	}


	public boolean removeRange(float start, float end) {
		checkLocked();
		return ranges.removeEqualRange(start, end);
	}


	public boolean isLocked() {
		return locked;
	}


	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	private void checkLocked() {
		if(locked) {
			throw new IllegalStateException("cannot modify a locked ");
		}
	}


	public FloatSearcher toImmutable() {
		this.setLocked(true);
		return this;
	}

}
