package test;

import org.junit.Test;

import arrayUtils.ArrayUtil;
import checks.Check;

/**
 * @author TeamworkGuy2
 * @since 2014-12-6
 */
public final class ArrayTests {
	private static float[][] fVals1 = new float[][] { { 2, 5.5f, 3.4f }, { 0.25f, 0.50f, 0.75f }, { 0, -1, 1 }, {} };
	private static int[][] iVals1 = new int[][] { { 2, 3, 6 }, { 5, 10, 15 }, { 0, -1, 1 }, {} };


	@Test
	public final void testArraySums() {
		Float[] fSumExpect = new Float[] { 10.9f, 1.5f, 0f, 0f };
		Integer[] iSumExpect = new Integer[] { 11, 30, 0, 0 };

		Check.assertTests(fVals1, fSumExpect, "", "", (fAry) -> ArrayUtil.sum(fAry));
		Check.assertTests(iVals1, iSumExpect, "", "", (iAry) -> ArrayUtil.sum(iAry));
	}


	@Test
	public final void testArrayAvg() {
		Float[] fSumExpect = new Float[] { 3.6333333f, 0.5f, 0f, Float.NaN };
		Float[] iSumExpect = new Float[] { 3.6666667f, 10f, 0f, Float.NaN };

		Check.assertTests(fVals1, fSumExpect, "", "", (fAry) -> ArrayUtil.avg(fAry));
		Check.assertTests(iVals1, iSumExpect, "", "", (iAry) -> ArrayUtil.avg(iAry));
	}

}
