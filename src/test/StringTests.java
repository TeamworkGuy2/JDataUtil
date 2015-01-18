package test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import primitiveCollections.IntArrayList;
import ranges.Ranges;
import stringUtils.StringCase;
import stringUtils.StringCommonality;
import stringUtils.StringConvert;
import stringUtils.StringIndex;
import stringUtils.StringModify;
import stringUtils.StringReplace;
import checks.Check;
import dataUtils.ListUtil;

/**
 * @author TeamworkGuy2
 * @since 2014-8-24
 */
public class StringTests {

	@Test
	public void testEscapeUnescape() {
		String src = "a \\\"block\\\" char '\\\"'";
		StringBuilder strDst = new StringBuilder();
		StringConvert.unwrapChar(src, 0, '\\', '"', strDst);
		String unwrapped = strDst.toString();
		Assert.assertTrue("a \"block\" char '\"'".equals(unwrapped));

		strDst.setLength(0);
		StringConvert.wrapChar(unwrapped, '\\', '"', (char)0, strDst);
		String wrapped = strDst.toString();
		Assert.assertTrue(src.equals(wrapped));
	}


	@Test
	public void stringUtilTest() {
		String str = StringReplace.replaceEscapeLiterals("kd\\t\\nwith\\\\and \\\"\\f\\\'");
		Assert.assertTrue("replace escape literals: " + str, str.equals("kd\t\nwith\\and \"\f\'"));
	}


	@Test
	public void stringSplitTest() {
		String[] matches = new String[] {
				"a somewhat very long string without any matching values except at the end //",
				"something shorter//split into pieces//at least I hope//so",
				"//beginning split",
				"////split////",
				"",
				"//a//b//c//d//e//f//g",
				"1 2",
				"//",
				"5//",
		};
		for(String match : matches) {
			Assert.assertArrayEquals(match.split("//", 5), StringModify.split(match, "//", 5));
		}
	}


	@Test
	public void charSearchRangeTest() {
		int[] duplicateRanges = new int[] {
				1, 2,
				3, 5,
				3, 5,
				1, 2,
				3, 5,
		};
		IntArrayList duplicateRangeList = new IntArrayList(duplicateRanges, 0, duplicateRanges.length);
		System.out.println("with duplicate ranges:\t\t" + duplicateRangeList);
		Check.assertException(() -> Ranges.throwIfDuplicateRanges(duplicateRangeList));
		System.out.println("removed duplicate ranges:\t" + duplicateRangeList);

		int[][] ranges = new int[][] {
				{ 1, 4, 3, 6 },
				{ 1, 7, 3, 6 },
				{ 1, 2, 3, 6 },
				{ 3, 6, 6, 7 },
				{ 1, 2, 3, 3 },
				{ 3, 3, 2, 4 },
		};
		// expected range overlap values
		Boolean[] expectedOverlap = new Boolean[] { true, true, false, true, false, true };
		// expected range overlap counts
		Integer[] expectedOverlapCount = new Integer[] { 2, 4, 0, 1, 0, 1 };

		for(int i = 0, size = ranges.length; i < size; i ++) {
			System.out.println("range [" + ranges[i][0] + ", " + ranges[i][1] +
					"], to [" + ranges[i][2] + ", " + ranges[i][3] + "], overlap: " +
					Ranges.doRangesOverlap(ranges[i][0], ranges[i][1], ranges[i][2], ranges[i][3]) +
					", count: " + Ranges.rangeOverlapCount(ranges[i][0], ranges[i][1], ranges[i][2], ranges[i][3]));
		}

		Check.assertTests(ranges, expectedOverlap, "", "",
				(range) -> Ranges.doRangesOverlap(range[0], range[1], range[2], range[3]));
		Check.assertTests(ranges, expectedOverlapCount, "", "",
				(range) -> Ranges.rangeOverlapCount(range[0], range[1], range[2], range[3]));
	}


	@Test
	public void listUtilAddTest() {
		@SuppressWarnings("unchecked")
		List<String>[] lists = new List[] {
				Arrays.asList("a", "unique", "list"),
				Arrays.asList("list", "non", "unique", "list"),
				Arrays.asList(""),
				Arrays.asList(),
		};
		Boolean[] expect = new Boolean[] { true, false, true, true };

		Check.assertTests(lists, expect, "", "", (list) -> ListUtil.isUnique(list));
	}


	@Test
	public void indexOfNotPrefixedByTest() {
		{
			String[] strs = new String[] {	"string //!#with !#text", "!#", "//!#!#", "and //!#///!#", "/!#//!#"};
			Integer[] expect = new Integer[] {16, 0, 4, -1, 1};

			String str = "!#";
			String prefix = "//";
			int strOff = 0;
			int prefixOff = 0;
			char[] strC = str.toCharArray();
			char[] prefixC = prefix.toCharArray();

			Check.assertTests(strs, expect, "index of " + str + " without " + prefix + " prefix ", "",
					(s) -> StringIndex.indexOfNotPrefixedBy(s.toCharArray(), 0, strC, strOff, prefixC, prefixOff));
		}

		{
			String[] strs2 = new String[] {	"string //!#with !#text", "!#", "//!#!#", "and //!#///!#", "/!#//!#"};
			Integer[] expect2 = new Integer[] {16, 0, 4, -1, 1};

			String str2 = "!#";
			String prefix2 = "//";
			int strOff2 = 0;
			int prefixOff2 = 0;
			char[] strC2 = str2.toCharArray();
			char[] prefixC2 = prefix2.toCharArray();

			Check.assertTests(strs2, expect2, "index of " + str2 + " without " + prefix2 + " prefix ", "",
					(s) -> StringIndex.indexOfNotPrefixedBy(s.toCharArray(), 0, strC2, strOff2, prefixC2, prefixOff2));
		}

		{
			String[] strs2 = new String[] {	"string <%-with text-%>", "<%-a", "-%>", "and <!--<%-%>", "<!--%>"};
			Integer[] expect2 = new Integer[] {7, 0, 0, 10, 3};

			String str2 = "!#";
			String prefix = "<!--";
			List<String> matchStrs = Arrays.asList("<%-", "-%>");
			int prefixOff = 0;
			char[] prefixC = prefix.toCharArray();

			Check.assertTests(strs2, expect2, "index of " + str2 + " without " + prefix + " prefix ", "",
					(s) -> {
						int index = StringIndex.indexOfMatchNotPrefixedBy(s.toCharArray(), 0, matchStrs, prefixC, prefixOff);
						if(index > -1) {
							index = StringIndex.indexOfNotPrefixedBy(s.toCharArray(), 0, matchStrs.get(index), 0, prefixC, prefixOff);
						}
						return index;
					});
		}
	}


	@Test
	public void stringJoin() {
		String[][] strs = new String[][] { { "Aa", "Bb" }, { "123", "_", ".." }, { "", "" }, { "" } };
		String[] delimiters = new String[] { "-", "||", "=", "=" };
		String[] expect = new String[] { "Aa-Bb", "123||_||..", "=", "" };

		Check.assertTests(strs, expect, "", "", (s, i) -> StringModify.join(s, delimiters[i]));
	}


	@Test
	public void stringReplaceTest() {
		{
			String[] strs = new String[] {   "&amp; with &lt; or &gt;", "*** or ** * ***", "***", "*** a six***seven***" };
			String[] expect = new String[] { "& with < or >",           "* or ** * *",     "*",   "* a six*seven*" };

			List<String> searchStrs = Arrays.asList("***", "&amp;", "&lt;", "&gt;");
			List<String> replaceStrs = Arrays.asList("*", "&", "<", ">");

			Check.assertTests(strs, expect, "", "",
					(s) -> StringReplace.replaceStrings(s, 0, searchStrs, replaceStrs));
		}

		{
			String[] strs2 = new String[] {   "&lt;+=&lt; or &gt;", "*** or ** * ***", "***", "*** a six***seven***" };
			String[] expect2 = new String[] { "&lt;+=< or >",       "*** or ** * *",   "***", "*** a six*seven*" };

			List<String> searchStrs2 = Arrays.asList("***", "&amp;", "&lt;", "&gt;");
			List<String> replaceStrs2 = Arrays.asList("*", "&", "<", ">");

			int strOffset = 6;
			Check.assertTests(strs2, expect2, "", "",
					(s) -> StringReplace.replaceStrings(s, strOffset, searchStrs2, replaceStrs2));
		}
	}


	@Test
	public void commonPrefixSuffixTest() {
		// prefix
		{
			String[][] strs = new String[][] { { "" }, { "a_b", "a_b_c" }, { "this.that", "this_that" }, { "abc", "c" } };
			String[] expect = new String[] { "", "a_b", "this", "" };

			Check.assertTests(strs, expect, "", "", (strSet) -> StringCommonality.findPrefix(0, strSet));
		}
		// suffix
		{
			String[][] strs = new String[][] { { "" }, { "a_b", "a_b_c" }, { "this.that", "this_that" }, { "abc", "c" } };
			String[] expect = new String[] { "", "", "that", "c" };

			Check.assertTests(strs, expect, "", "", (strSet) -> StringCommonality.findSuffix(0, strSet));
		}
	}


	@Test
	public void stringCaseTest() {
		String[] strs = new String[] { "abc", "Alpha", "Subpar", "SupPar", "Al_Cid", "var_val_byte", "A_", "_A", "a", "_" };
		String[] camelCase = new String[] { "abc", "alpha", "subpar", "supPar", "alCid", "varValByte", "a", "a", "a", "_" };
		String[] titleCase = new String[] { "Abc", "Alpha", "Subpar", "SupPar", "AlCid", "VarValByte", "A", "A", "A", "_" };
		String[] underscoreLowerCase = new String[] { "abc", "alpha", "subpar", "sup_par", "al_cid", "var_val_byte", "a_", "_a", "a", "_" };
		String[] underscoreTitleCase = new String[] { "Abc", "Alpha", "Subpar", "Sup_Par", "Al_Cid", "Var_Val_Byte", "A_", "_A", "A", "_" };

		for(int i = 0, size = strs.length; i < size; i++) {
			Assert.assertEquals("camelCase", StringCase.toCamelCase(strs[i]), camelCase[i]);
			Assert.assertEquals("TitleCase", StringCase.toTitleCase(strs[i]), titleCase[i]);
			Assert.assertEquals("underscoreLowerCase", StringCase.toUnderscoreLowerCase(strs[i]), underscoreLowerCase[i]);
			Assert.assertEquals("underscoreTitleCase", StringCase.toUnderscoreTitleCase(strs[i]), underscoreTitleCase[i]);
			/*System.out.println("str: " + str +
					",\tcamelCase: " + StringCase.toCamelCase(str) +
					",\tTitleCase: " + StringCase.toTitleCase(str) +
					",\tunderscore_lower_case: " + StringCase.toUnderscoreLowerCase(str) +
					",\tUnderscore_Upper_Case: " + StringCase.toUnderscoreTitleCase(str));*/
		}
	}


	public static void arrayUtilTest() {
		;
	}

}
