package ranges;

import primitiveCollections.IntArrayList;


/** A {@link CharSearcher} that contains a set of chars to compare input characters to.
 * @author TeamworkGuy2
 * @since 2014-11-1
 */
public final class CharSearcherMutable implements CharSearcher {
	private IntArrayList chars;
	private final boolean locked;


	@SafeVarargs
	public CharSearcherMutable(char... cs) {
		this.locked = false;
		if(cs != null) {
			this.chars = new IntArrayList(cs.length);
			for(char c : cs) {
				if(this.chars.contains(c)) {
					throw new IllegalArgumentException("duplicate searcher char '" + c + "'");
				}
				this.chars.add(c);
			}
		}
		else {
			this.chars = new IntArrayList();
		}
	}


	public CharSearcherMutable(CharSearcherMutable charSearcher, boolean locked) {
		this.locked = locked;
		this.chars = charSearcher.chars.copy();

		for(int i = 0, size = this.chars.size(); i < size; i++) {
			int c = this.chars.get(i);
			if(this.chars.contains(c)) {
				throw new IllegalArgumentException("duplicate searcher char '" + c + "'");
			}
		}
	}


	@Override
	public boolean isMatch(char ch) {
		return indexOfMatch(ch) > -1;
	}


	@Override
	public int indexOfMatch(char ch) {
		for(int i = 0, size = chars.size(); i < size; i++) {
			if(chars.get(i) == ch) {
				return i;
			}
		}
		return -1;
	}


	public int size() {
		return chars.size();
	}


	public boolean isLocked() {
		return locked;
	}


	private void checkLocked() {
		if(locked) {
			throw new IllegalStateException("cannot modify a locked CharSearcher");
		}
	}


	public void addChar(int ch) {
		checkLocked();
		if(chars.indexOf(ch) > -1) {
			throw new IllegalArgumentException("duplicate searcher char '" + ch + "'");
		}
		chars.add(ch);
	}


	public boolean removeCharAt(int index) {
		checkLocked();
		chars.remove(index);
		return true;
	}


	public boolean removeChar(char ch) {
		checkLocked();
		int index = chars.indexOf(ch, 0);
		if (index == -1) {
			return false;
		}
		chars.remove(index);
		return true;
	}


	public CharSearcher toImmutable() {
		return new CharSearcherMutable(this, true);
	}

}
