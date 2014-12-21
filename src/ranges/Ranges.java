package ranges;

import primitiveCollections.IntArrayList;
import primitiveCollections.IntList;

/** Methods for checking if integer ranges overlap, contain a value, or adding/merging
 * overlapping ranges.
 * @author TeamworkGuy2
 * @since 2014-12-13
 * @see IntRange
 * @see IntSearcher
 */
public final class Ranges {

	private Ranges() { throw new ExceptionInInitializerError("cannot instantiate Ranges"); }


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
		return (start1 <= end2) && (end1 >= start2);
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
		return start1 == start2 && end1 == end2;
	}


	public static final int indexOfRange(IntArrayList rangePoints, int start, int end) {
		for(int index = 0, size = rangePoints.size(); index < size; ) {
			index = rangePoints.indexOf(start, index);
			if (index == -1) {
				break;
			}
			if(index < size-1 && rangePoints.get(index + 1) == end) {
				return index;
			}
		}
		return -1;
	}


	public static final int removeMatchingRangeSections(IntArrayList rangePoints, int rangeStart, int rangeEnd) {
		int rangeCountRemoved = 0;
		for(int i = rangePoints.size() - 1; i > -1; i -= 2) {
			int startA = rangePoints.get(i - 1);
			int endA = rangePoints.get(i);
			if(doRangesOverlap(startA, endA, rangeStart, rangeEnd)) {
				// if the range to remove surrounds the current range
				if(rangeStart <= startA && rangeEnd >= endA) {
					rangePoints.remove(i);
					rangePoints.remove(i - 1);

					rangeCountRemoved += endA - (startA - 1);
				}
				// if the range to remove is contained inside the current range
				else if(rangeStart > startA && rangeEnd < endA) {
					rangePoints.set(i, rangeStart - 1);
					// add a new range for the ending portion of the old range
					rangePoints.add(i + 1, rangeEnd + 1);
					rangePoints.add(i + 2, endA);

					rangeCountRemoved += endA - (startA - 1);
				}
				// if the range to remove overlaps with the top of the current range
				if(rangeStart > startA && rangeEnd >= endA) {
					rangePoints.set(i, rangeStart - 1);

					rangeCountRemoved += endA - (startA - 1);
				}
				// if the range to remove overlaps with the bottom of the current range
				else if(rangeStart <= startA && rangeEnd < endA) {
					rangePoints.set(i - 1, rangeEnd + 1);

					rangeCountRemoved += endA - (rangeEnd - 1);
				}
			}
		}
		return rangeCountRemoved;
	}


	//@return the index of the start point of the existing range in {@code rangePoints}
	// * or -1 if the range does not exist in {@code rangePoints}
	/** Check for duplicates in the specified list of range start and end points
	 * @param rangePoints the list of range start and end points, the size of this
	 * list must be divisible by 2, every 2 points represent the start and end point of a range
	 */
	public static final void throwIfRangeExists(IntList rangePoints, int rangeStart, int rangeEnd) {
		// search each range
		for(int i = rangePoints.size() - 1; i > -1; i -= 2) {
			// looking for a duplicate range
			if(rangeStart == rangePoints.get(i - 1) && rangeEnd == rangePoints.get(i)) {
				throw new IllegalArgumentException("existing range " + i/2 + " [" + rangePoints.get(i-1) + ", " +
						rangePoints.get(i) + "] equals [" + rangeStart + ", " + rangeEnd + "] are equal");
			}
		}
	}


	public static final void throwIfDuplicateRanges(IntList rangePoints) {
		handleDuplicateRanges(rangePoints, false, true);
	}


	public static final void removeDuplicateRanges(IntList rangePoints) {
		handleDuplicateRanges(rangePoints, true, false);
	}


	/** Check for duplicates in the specified list of range start and end points
	 * @param rangePoints the list of range start and end points, the size of this
	 * list must be divisible by 2, every 2 points represent the start and end point of a range
	 * @param removeDuplicates true to remove duplicates from the list of points
	 * @param throwDuplicateException throw an exception if duplicate ranges are found
	 */
	private static final void handleDuplicateRanges(IntList rangePoints, boolean removeDuplicates,
			boolean throwDuplicateException) {
		// search each range
		for(int i = rangePoints.size() - 1; i > -1; i -= 2) {
			int startA = rangePoints.get(i - 1);
			int endA = rangePoints.get(i);
			// against every other range
			for(int ii = rangePoints.size() - 1; ii > -1; ii -= 2) {
				if(i == ii) {
					continue;
				}
				// looking for duplicate ranges
				if(startA == rangePoints.get(ii - 1) && endA == rangePoints.get(ii)) {
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


	public static final void mergeDuplicates(IntArrayList rangePoints) {
		for(int i = rangePoints.size() - 1; i > 0; i -= 2) {
			int startA = rangePoints.get(i - 1);
			int endA = rangePoints.get(i);
			for(int ii = rangePoints.size() - 1; ii > 0; ii -= 2) {
				if(i == ii) {
					continue;
				}
				int startB = rangePoints.get(ii - 1);
				int endB = rangePoints.get(ii);
				if(doRangesOverlap(startA, endA, startB, endB)) {
					rangePoints.set(i - 1, Math.min(startA, startB));
					rangePoints.set(i, Math.max(endA, endB));
					rangePoints.remove(ii - 1);
					rangePoints.remove(ii - 1);
					if(i >= ii) {
						i -= 2;
					}
				}
			}
		}
	}


	public static final void addRangeCombineOverlap(IntArrayList rangePoints, int start1, int end1) {
		boolean added = false;
		for(int i = rangePoints.size() - 1; i > 0; i -= 2) {
			int startA = rangePoints.get(i - 1);
			int endA = rangePoints.get(i);
			if(doRangesOverlap(startA, endA, start1, end1)) {
				rangePoints.set(i - 1, Math.min(startA, start1));
				rangePoints.set(i, Math.max(endA, end1));
				added = true;
				break;
			}
			// if the end of the current range is adjacent to the beginning of the new range
			else if(endA + 1 == start1) {
				rangePoints.set(i, end1);
				added = true;
				break;
			}
			// if the start of the current range is adjacent to the end of the new range
			else if(startA - 1 == end1) {
				rangePoints.set(i - 1, start1);
				added = true;
				break;
			}
		}
		if(!added) {
			rangePoints.add(start1);
			rangePoints.add(end1);
		}
	}


	public static final IntRangeSearcher newIntRangeSearcherMutable() {
		return new IntRangeSearcherMutableImpl();
	}


	public static final CharRangeSearcherMutable newCharRangeSearcherMutable() {
		return new IntRangeSearcherMutableImpl();
	}

}
