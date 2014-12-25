package ranges;

import primitiveCollections.FloatListSorted;

/** A {@link FloatSearcher} that contains a set of floats to compare input float to.
 * @author TeamworkGuy2
 * @since 2014-11-1
 */
public final class FloatSearcherMutable implements FloatSearcher {
	private FloatListSorted values;
	private final boolean locked;


	@SafeVarargs
	public FloatSearcherMutable(float... cs) {
		this.locked = false;
		if(cs != null) {
			this.values = new FloatListSorted(cs.length);
			for(float c : cs) {
				if(this.values.contains(c)) {
					throw new IllegalArgumentException("duplicate searcher float '" + c + "'");
				}
				this.values.add(c);
			}
		}
		else {
			this.values = new FloatListSorted();
		}
	}


	public FloatSearcherMutable(FloatSearcherMutable valueSearcher, boolean locked) {
		this.locked = locked;
		this.values = valueSearcher.values.copy();

		for(int i = 0, size = this.values.size(); i < size; i++) {
			float c = this.values.get(i);
			if(this.values.contains(c)) {
				throw new IllegalArgumentException("duplicate searcher float '" + c + "'");
			}
		}
	}


	@Override
	public boolean isMatch(float value) {
		return indexOfMatch(value) > -1;
	}


	@Override
	public int indexOfMatch(float value) {
		return values.indexOf(value);
	}


	public int size() {
		return values.size();
	}


	public boolean isLocked() {
		return locked;
	}


	private void checkLocked() {
		if(locked) {
			throw new IllegalStateException("cannot modify a locked FloatSearcher");
		}
	}


	public void addFloat(float value) {
		checkLocked();
		if(values.indexOf(value) > -1) {
			throw new IllegalArgumentException("duplicate searcher float '" + value + "'");
		}
		values.add(value);
	}


	public boolean removeFloatAt(int index) {
		checkLocked();
		values.remove(index);
		return true;
	}


	public boolean removeFloat(float value) {
		checkLocked();
		int index = values.indexOf(value);
		if (index == -1) {
			return false;
		}
		values.remove(index);
		return true;
	}


	public FloatSearcher toImmutable() {
		return new FloatSearcherMutable(this, true);
	}

}
