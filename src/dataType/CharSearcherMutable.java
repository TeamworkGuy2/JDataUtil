package dataType;

import dataCollections.IntListDefault;

/** A {@link CharSearcher} that contains a set of chars to compare input characters to.
 * TODO do not allow duplicate chars
 * @author TeamworkGuy2
 * @since 2014-11-1
 */
public final class CharSearcherMutable implements CharSearcher {
	private IntListDefault chars;
	private boolean locked;


	@SafeVarargs
	public CharSearcherMutable(char... cs) {
		if(cs != null) {
			this.chars = new IntListDefault(cs.length);
			for(char c : cs) {
				this.chars.add(c);
			}
		}
		else {
			this.chars = new IntListDefault();
		}
	}


	@Override
	public boolean isMatch(int ch) {
		return indexOfMatch(ch) > -1;
	}


	@Override
	public int indexOfMatch(int ch) {
		for(int i = 0, size = chars.size(); i < size; i++) {
			if(chars.get(i) == ch) {
				return i;
			}
		}
		return -1;
	}


	@Override
	public int size() {
		return chars.size();
	}


	public boolean isLocked() {
		return locked;
	}


	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	private void checkLocked() {
		if(locked) {
			throw new IllegalStateException("cannot modify a locked CharRangeSearcher");
		}
	}


	public void addChar(int ch) {
		checkLocked();
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
		this.setLocked(true);
		return this;
	}

}
