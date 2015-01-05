package ranges;

/**
 * @author TeamworkGuy2
 * @since 2014-10-29
 */
@javax.annotation.Generated("StringTemplate")
public final class IntRangeSearcherMutableImpl implements IntRangeSearcherMutable {
	private primitiveCollections.IntArrayList rangePoints;
	private final boolean locked;
	private boolean throwIfRangeExists;
	private boolean combineOverlap;


	public IntRangeSearcherMutableImpl(int start1, int end1) {
		this(false);
		rangePoints.add(start1);
		rangePoints.add(end1);
	}


	public IntRangeSearcherMutableImpl(int start1, int end1, int start2, int end2) {
		this(false);
		rangePoints.add(start1);
		rangePoints.add(end1);
		addRange(start2, end2);
	}


	public IntRangeSearcherMutableImpl(int start1, int end1, int start2, int end2, int start3, int end3) {
		this(false);
		rangePoints.add(start1);
		rangePoints.add(end1);
		addRange(start2, end2);
		addRange(start3, end3);
	}


	public IntRangeSearcherMutableImpl(primitiveCollections.IntList rangePoints) {
		this(rangePoints, false, true, false);
	}


	public IntRangeSearcherMutableImpl(primitiveCollections.IntList rangePoints, boolean locked,
			boolean throwErrorIfEqualRangeExists, boolean combineOverlappingRanges) {
		this(locked, throwErrorIfEqualRangeExists, combineOverlappingRanges);

		for(int i = 0, size = rangePoints.size(); i < size; i += 2) {
			addRange(rangePoints.get(i), rangePoints.get(i + 1));
		}
	}


	public IntRangeSearcherMutableImpl(IntRangeSearcher src) {
		this(src, false, true, false);
	}


	public IntRangeSearcherMutableImpl(IntRangeSearcher src, boolean locked,
			boolean throwErrorIfEqualRangeExists, boolean combineOverlappingRanges) {
		this(locked, throwErrorIfEqualRangeExists, combineOverlappingRanges);

		for(int i = 0, size = src.size(); i < size; i++) {
			int start = src.getLowerBound(i);
			int end = src.getUpperBound(i);
			if(throwIfRangeExists) {
				Ranges.throwIfRangeExists(rangePoints, start, end);
			}
			addRange(start, end);
		}
	}


	public IntRangeSearcherMutableImpl() {
		this(false);
	}


	public IntRangeSearcherMutableImpl(boolean locked) {
		this(locked, true, false);
	}


	public IntRangeSearcherMutableImpl(boolean locked, boolean throwErrorIfEqualRangeExists,
			boolean combineOverlappingRanges) {
		this.rangePoints = new primitiveCollections.IntArrayList();
		this.locked = locked;
		this.throwIfRangeExists = throwErrorIfEqualRangeExists;
		this.combineOverlap = combineOverlappingRanges;
	}


	@Override
	public boolean isMatch(int val) {
		return indexOfMatch(val) > -1;
	}


	@Override
	public int indexOfMatch(int val) {
		for(int index = 0, size = rangePoints.size(); index < size; index += 2) {
			if(rangePoints.get(index) <= val && rangePoints.get(index + 1) >= val) {
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


	private void checkLocked() {
		if(locked) {
			throw new IllegalStateException("cannot modify a locked IntRangeSearcherMutableImpl");
		}
	}


	@Override
	public void addRange(int start, int end) {
		checkLocked();
		if(throwIfRangeExists) {
			Ranges.throwIfRangeExists(rangePoints, start, end);
		}
		if(combineOverlap) {
			Ranges.addRangeCombineOverlap(rangePoints, start, end);
		}
		else {
			rangePoints.add(start);
			rangePoints.add(end);
		}
	}


	@Override
	public boolean removeRangeAt(int index) {
		checkLocked();
		rangePoints.remove((index << 1) + 1);
		rangePoints.remove((index << 1));
		return true;
	}


	@Override
	public boolean removeEqualRange(int start, int end) {
		checkLocked();
		int index = Ranges.indexOfRange(rangePoints, start, end);
		if(index > -1) {
			rangePoints.remove(index + 1);
			rangePoints.remove(index);
			return true;
		}
		return false;
	}


	@Override
	public void removeRange(int start, int end) {
		Ranges.removeMatchingRangeSections(rangePoints, start, end);
	}


	@Override
	public IntRangeSearcher toImmutable() {
		return new IntRangeSearcherMutableImpl(this, true, true, false);
	}

}
