package dataType;

import dataCollections.IntListDefault;

/**
 * @author TeamworkGuy2
 * @since 2014-10-29
 */
public final class CharRangeSearcherMutable implements CharRangeSearcher {
	IntListDefault rangePoints;
	boolean locked;


	public CharRangeSearcherMutable(int start1, int end1) {
		this();
		rangePoints.add(start1);
		rangePoints.add(end1);
	}


	public CharRangeSearcherMutable(int start1, int end1, int start2, int end2) {
		this();
		rangePoints.add(start1);
		rangePoints.add(end1);
		rangePoints.add(start2);
		rangePoints.add(end2);
	}


	public CharRangeSearcherMutable(int start1, int end1, int start2, int end2, int start3, int end3) {
		this();
		rangePoints.add(start1);
		rangePoints.add(end1);
		rangePoints.add(start2);
		rangePoints.add(end2);
		rangePoints.add(start3);
		rangePoints.add(end3);
	}


	public CharRangeSearcherMutable(CharRangeSearcher src) {
		this();
		for(int i = 0, size = src.size(); i < size; i++) {
			rangePoints.add(src.getLowerBound(i));
			rangePoints.add(src.getUpperBound(i));
		}
	}


	public CharRangeSearcherMutable() {
		this.rangePoints = new IntListDefault();
		this.locked = false;
	}


	@Override
	public boolean isMatch(int ch) {
		return indexOfMatch(ch) > -1;
	}


	@Override
	public int indexOfMatch(int ch) {
		for(int index = 0, size = rangePoints.size(); index < size; index += 2) {
			if(rangePoints.get(index) <= ch && rangePoints.get(index+1) >= ch) {
				return index;
			}
		}
		return -1;
	}


	@Override
	public int getLowerBound(int i) {
		return rangePoints.get(i << 1);
	}


	@Override
	public int getUpperBound(int i) {
		return rangePoints.get((i << 1) + 1);
	}


	@Override
	public int size() {
		return rangePoints.size() >> 1;
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


	public void addRange(int start, int end) {
		checkLocked();
		rangePoints.add(start);
		rangePoints.add(end);
	}


	public boolean removeRangeAt(int index) {
		checkLocked();
		rangePoints.remove((index << 1) + 1);
		rangePoints.remove((index << 1));
		return true;
	}


	public boolean removeRange(int start, int end) {
		checkLocked();
		for(int index = 0, size = rangePoints.size(); index < size; ) {
			index = rangePoints.indexOf(start, index);
			if (index == -1) {
				break;
			}
			if(index < size-1 && rangePoints.get(index+1) == end) {
				rangePoints.remove(index+1);
				rangePoints.remove(index);
				return true;
			}
		}
		return false;
	}


	public CharRangeSearcher toImmutable() {
		this.setLocked(true);
		return this;
	}


	private final boolean doRangesOverlap(int start1, int end1, int start2, int end2) {
		if(start1 < end1 || start2 < end2) {
			throw new IllegalArgumentException("range overlap check, range end is less than range start, " +
					"start1='" + start1 + "' end1='" + end1 + "', start2='" + start2 + " end2='" + end2);
		}
		return !(start1 > end2) && !(end1 < start2);
	}

}
