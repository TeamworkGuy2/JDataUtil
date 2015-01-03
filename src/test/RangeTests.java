package test;

import org.junit.Assert;
import org.junit.Test;

import primitiveCollections.IntArrayList;
import primitiveCollections.IntList;
import ranges.IntRangeSearcherMutable;
import ranges.IntRangeSearcherMutableImpl;
import checks.Check;

/**
 * @author TeamworkGuy2
 * @since 2014-12-13
 */
public class RangeTests {


	@Test
	public void testRemoveRangeSections() {
		IntList rangePoints = new IntArrayList(new int[] { 0, 10, 20, 24, 50, 54 });
		IntRangeSearcherMutable ranges = new IntRangeSearcherMutableImpl(rangePoints, false, false, true);

		Integer[] indices = { 0, 1, 9, 10, 24, 26, 54 };
		Boolean[] expected = { true, true, true, true, true, false, true };

		Check.assertTests(indices, expected, "", "", (val) -> ranges.isMatch(val));

		int size = ranges.size();
		ranges.addRange(25, 30);
		Assert.assertEquals("ranges did not combine correctly", size, ranges.size());

		ranges.removeRange(23, 27);

		indices = new Integer[] { 19, 20, 22, 23, 26, 28, 30, 31 };
		expected = new Boolean[] { false, true, true, false, false, true, true, false };

		Check.assertTests(indices, expected, "", "", (val) -> ranges.isMatch(val));

		ranges.removeRange(25, 30);
		ranges.removeRange(40, 50);
		ranges.removeRange(54, 54);
		ranges.removeRange(52, 52);
		ranges.removeRange(50, 54);

		Assert.assertEquals("ranges did not remove correctly", 2, ranges.size());
	}


}
