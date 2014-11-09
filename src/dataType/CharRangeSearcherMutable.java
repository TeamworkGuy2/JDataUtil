package dataType;

import dataCollections.IntList;
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
		if(rangesEqual(start1, end1, start2, end2)) {
			throw new IllegalArgumentException("ranges equal [" + start1 + ", " + end1 + "] to [" + start2 + ", " + end2 + "]");
		}
		rangePoints.add(start1);
		rangePoints.add(end1);
		rangePoints.add(start2);
		rangePoints.add(end2);
	}


	public CharRangeSearcherMutable(int start1, int end1, int start2, int end2, int start3, int end3) {
		this();
		if(rangesEqual(start1, end1, start2, end2)) {
			throw new IllegalArgumentException("ranges equal [" + start1 + ", " + end1 + "] to [" + start2 + ", " + end2 + "]");
		}
		if(rangesEqual(start1, end1, start3, end3)) {
			throw new IllegalArgumentException("ranges equal [" + start1 + ", " + end1 + "] to [" + start3 + ", " + end3 + "]");
		}
		if(rangesEqual(start2, end2, start3, end3)) {
			throw new IllegalArgumentException("ranges equal [" + start2 + ", " + end2 + "] to [" + start3 + ", " + end3 + "]");
		}
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
			int start = src.getLowerBound(i);
			int end = src.getUpperBound(i);
			checkForExistingRange(rangePoints, start, end, true);
			rangePoints.add(start);
			rangePoints.add(end);
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
		checkForExistingRange(rangePoints, start, end, true);
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


	/** Check whether to ranges of values overlap.<br>
	 * For example {@code doRangesOverlap(35, 50, 48, 120)}
	 * returns {@code true}<br>
	 * For example {@code doRangesOverlap(35, 50, 55, 120)}
	 * returns {@code false}
	 * @param start1 the start of the first range, inclusive
	 * @param end1 the end of the first range, inclusive
	 * @param start2 the start of the second range, inclusive
	 * @param end2 the end of the second range, inclusive
	 * @return true if the ranges overlap, false if they do not overlap
	 */
	public static final boolean doRangesOverlap(int start1, int end1, int start2, int end2) {
		if(start1 > end1 || start2 > end2) {
			throw new IllegalArgumentException("range overlap check, range end is less than range start, " +
					" [" + start1 + ", " + end1 + "], to [" + start2 + ", " + end2 + "]");
		}
		return !(start1 > end2) && !(end1 < start2);
	}


	/** return the number of values that overlap between to ranges.<br>
	 * For example {@code doRangesOverlap(35, 50, 48, 120)}
	 * returns {@code 3}<br>
	 * For example {@code doRangesOverlap(35, 50, 55, 120)}
	 * returns {@code 0}
	 * @param start1 the start of the first range, inclusive
	 * @param end1 the end of the first range, inclusive
	 * @param start2 the start of the second range, inclusive
	 * @param end2 the end of the second range, inclusive
	 * @return the number of elements that overlap between the two ranges
	 */
	public static final int rangeOverlapCount(int start1, int end1, int start2, int end2) {
		return doRangesOverlap(start1, end1, start2, end2) ? Math.min(end1, end2) - Math.max(start1, start2) + 1 : 0;
	}


	/** return whether two ranges are equal.<br>
	 * For example {@code rangesEqual(35, 50, 48, 50)}
	 * returns {@code false}<br>
	 * For example {@code rangesEqual(35, 50, 35, 50)}
	 * returns {@code true}
	 * @param start1 the start of the first range, inclusive
	 * @param end1 the end of the first range, inclusive
	 * @param start2 the start of the second range, inclusive
	 * @param end2 the end of the second range, inclusive
	 * @return true if the two ranges are exactly equal, false otherwise
	 */
	public static final boolean rangesEqual(int start1, int end1, int start2, int end2) {
		return doRangesOverlap(start1, end1, start2, end2) ? start1 == start2 && end1 == end2 : false;
	}


	/** Check for duplicates in the specified list of range start and end points
	 * @param rangePoints the list of range start and end points, the size of this
	 * list must be divisible by 2, every 2 points represent the start and end point of a range
	 * @param throwDuplicateException throw an exception if duplicate ranges are found
	 * @return the index of the start point of the existing range in {@code rangePoints}
	 * or -1 if the range does not exist in {@code rangePoints}
	 */
	public static final int checkForExistingRange(IntList rangePoints, int rangeStart, int rangeEnd,
			boolean throwDuplicateException) {
		// search each range
		for(int i = rangePoints.size() - 1; i > -1; i -= 2) {
			// looking for a duplicate range
			if(rangeStart == rangePoints.get(i-1) && rangeEnd == rangePoints.get(i)) {
				if(throwDuplicateException) {
					throw new IllegalArgumentException("existing range " + i/2 + " [" + rangePoints.get(i-1) + ", " +
							rangePoints.get(i) + "] equals [" + rangeStart + ", " + rangeEnd + "] are equal");
				}
				return i-1;
			}
		}
		return -1;
	}


	/** Check for duplicates in the specified list of range start and end points
	 * @param rangePoints the list of range start and end points, the size of this
	 * list must be divisible by 2, every 2 points represent the start and end point of a range
	 * @param removeDuplicates true to remove duplicates from the list of points
	 * @param throwDuplicateException throw an exception if duplicate ranges are found
	 */
	public static final void checkForDuplicateRanges(IntList rangePoints, boolean removeDuplicates,
			boolean throwDuplicateException) {
		// search each range
		for(int i = rangePoints.size() - 1; i > -1; i -= 2) {
			int startA = rangePoints.get(i-1);
			int endA = rangePoints.get(i);
			// against every other range
			for(int ii = rangePoints.size() - 1; ii > -1; ii -= 2) {
				if(i != ii) {
					// looking for duplicate ranges
					if(startA == rangePoints.get(ii-1) && endA == rangePoints.get(ii)) {
						if(removeDuplicates) {
							rangePoints.remove(ii - 1);
							rangePoints.remove(ii - 1);
							// if a range not yet checked by the outer loop is removed, shift the search
							// down by one so that the outer loop does not check the same range twice
							if(i >= ii) {
								i -= 2;
							}
						}
						if(throwDuplicateException) {
							throw new IllegalArgumentException("ranges " + i/2 + " and " + ii/2 + " [" + startA + ", " + endA +
									"] and [" + rangePoints.get(ii-1) + ", " + rangePoints.get(ii) + "] are equal");
						}
					}
				}
			}
		}
	}

}
