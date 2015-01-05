package ranges;

import primitiveCollections.CharListSorted;

/** A {@link CharSearcher} that contains a set of chars to compare input char to.
 * @author TeamworkGuy2
 * @since 2014-11-1
 */
@javax.annotation.Generated("StringTemplate")
public final class CharSearcherMutable implements CharSearcher {
	private CharListSorted values;
	private final boolean locked;


	@SafeVarargs
	public CharSearcherMutable(char... cs) {
		this.locked = false;
		if(cs != null) {
			this.values = new CharListSorted(cs.length);
			for(char c : cs) {
				if(this.values.contains(c)) {
					throw new IllegalArgumentException("duplicate searcher char '" + c + "'");
				}
				this.values.add(c);
			}
		}
		else {
			this.values = new CharListSorted();
		}
	}


	public CharSearcherMutable(CharSearcherMutable valueSearcher, boolean locked) {
		this.locked = locked;
		this.values = valueSearcher.values.copy();

		for(int i = 0, size = this.values.size(); i < size; i++) {
			char c = this.values.get(i);
			if(this.values.contains(c)) {
				throw new IllegalArgumentException("duplicate searcher char '" + c + "'");
			}
		}
	}


	@Override
	public boolean isMatch(char value) {
		return indexOfMatch(value) > -1;
	}


	@Override
	public int indexOfMatch(char value) {
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
			throw new IllegalStateException("cannot modify a locked CharSearcher");
		}
	}


	public void addChar(char value) {
		checkLocked();
		if(values.indexOf(value) > -1) {
			throw new IllegalArgumentException("duplicate searcher char '" + value + "'");
		}
		values.add(value);
	}


	public boolean removeCharAt(int index) {
		checkLocked();
		values.remove(index);
		return true;
	}


	public boolean removeChar(char value) {
		checkLocked();
		int index = values.indexOf(value);
		if (index == -1) {
			return false;
		}
		values.remove(index);
		return true;
	}


	public CharSearcher toImmutable() {
		return new CharSearcherMutable(this, true);
	}

}
