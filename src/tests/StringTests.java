package tests;

import java.util.Arrays;
import java.util.List;

import ranges.Ranges;
import stringUtils.StringIndex;
import stringUtils.StringModify;
import stringUtils.StringReplace;
import checks.Check;
import dataCollections.IntArrayList;
import dataUtility.ListUtil;

/**
 * @author TeamworkGuy2
 * @since 2014-8-24
 */
public class StringTests {

	public static void stringUtilTest() {
		String str = StringReplace.replaceEscapeLiterals("kd\\t\\nwith\\\\and \\\"\\f\\\'");
		if(!str.equals("kd\t\nwith\\and \"\f\'")) { throw new Error("replace escape literals: " + str); }
		System.out.println("replace escape literals: " + str);
	}


	public static void stringSplitTest() {
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
			System.out.println(Arrays.toString(StringModify.split(match, "//", 5)));
			System.out.println(Arrays.toString(match.split("//", 5)));
		}
	}


	public static void charSearchRangeTest() {
		int[] duplicateRanges = new int[] {
				1, 2,
				3, 5,
				3, 5,
				1, 2,
				3, 5,
		};
		IntArrayList duplicateRangeList = new IntArrayList(duplicateRanges, 0, duplicateRanges.length);
		System.out.println("with duplicate ranges:\t\t" + duplicateRangeList);
		Ranges.throwIfDuplicateRanges(duplicateRangeList);
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

		Check.checkTests(ranges, expectedOverlap, "", "",
				(range) -> Ranges.doRangesOverlap(range[0], range[1], range[2], range[3]));
		Check.checkTests(ranges, expectedOverlapCount, "", "",
				(range) -> Ranges.rangeOverlapCount(range[0], range[1], range[2], range[3]));
	}


	public static void listUtilAddTest() {
		@SuppressWarnings("unchecked")
		List<String>[] lists = new List[] {
				Arrays.asList("a", "unique", "list"),
				Arrays.asList("list", "non", "unique", "list"),
				Arrays.asList(""),
				Arrays.asList(),
		};
		Boolean[] expect = new Boolean[] { true, false, true, true };

		Check.checkTests(lists, expect, "", "", (list) -> ListUtil.isUnique(list));
	}


	public static void indexOfNotPrefixedByTest() {
		{
			String[] strs = new String[] {	"string //!#with !#text", "!#", "//!#!#", "and //!#///!#", "/!#//!#"};
			Integer[] expect = new Integer[] {16, 0, 4, -1, 1};

			String str = "!#";
			String prefix = "//";
			int strOff = 0;
			int prefixOff = 0;
			char[] strC = str.toCharArray();
			char[] prefixC = prefix.toCharArray();

			Check.checkTests(strs, expect, "index of " + str + " without " + prefix + " prefix ", "",
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

			Check.checkTests(strs2, expect2, "index of " + str2 + " without " + prefix2 + " prefix ", "",
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

			Check.checkTests(strs2, expect2, "index of " + str2 + " without " + prefix + " prefix ", "",
					(s) -> {
						int index = StringIndex.indexOfMatchNotPrefixedBy(s.toCharArray(), 0, matchStrs, prefixC, prefixOff);
						if(index > -1) {
							index = StringIndex.indexOfNotPrefixedBy(s.toCharArray(), 0, matchStrs.get(index), 0, prefixC, prefixOff);
						}
						return index;
					});
		}
	}


	public static void stringJoin() {
		String[][] strs = new String[][] { { "Aa", "Bb" }, { "123", "_", ".." }, { "", "" }, { "" } };
		String[] delimiters = new String[] { "-", "||", "=", "=" };
		String[] expect = new String[] { "Aa-Bb", "123||_||..", "=", "" };

		Check.checkTests(strs, expect, "", "", (s, i) -> StringModify.join(s, delimiters[i]));
	}


	public static void stringReplaceTest() {
		{
			String[] strs = new String[] { 	"&amp; with &lt; or &gt;",	"*** or ** * ***",	"***",	"*** a six***seven***" };
			String[] expect = new String[] {"& with < or >",			"* or ** * *",		"*",	"* a six*seven*" };

			List<String> searchStrs = Arrays.asList("***", "&amp;", "&lt;", "&gt;");
			List<String> replaceStrs = Arrays.asList("*", "&", "<", ">");

			Check.checkTests(strs, expect, "", "",
					(s) -> StringReplace.replaceStrings(s, 0, searchStrs, replaceStrs));
		}

		{
			String[] strs2 = new String[] {		"&lt;+=&lt; or &gt;",	"*** or ** * ***",	"***",	"*** a six***seven***" };
			String[] expect2 = new String[] {	"&lt;+=< or >",			"*** or ** * *",	"***",	"*** a six*seven*" };

			List<String> searchStrs2 = Arrays.asList("***", "&amp;", "&lt;", "&gt;");
			List<String> replaceStrs2 = Arrays.asList("*", "&", "<", ">");

			int strOffset = 6;
			Check.checkTests(strs2, expect2, "", "",
					(s) -> StringReplace.replaceStrings(s, strOffset, searchStrs2, replaceStrs2));
		}
	}


	public static void commonPrefixSuffixTest() {
		// prefix
		{
			String[][] strs = new String[][] { {""}, {"a_b", "a_b_c"}, {"this.that", "this_that"}, {"abc", "c"} };
			String[] expect = new String[] { "", "a_b", "this", "" };

			Check.checkTests(strs, expect, "", "", (strSet) -> StringModify.commonPrefix(0, strSet));
		}
		// suffix
		{
			String[][] strs = new String[][] { {""}, {"a_b", "a_b_c"}, {"this.that", "this_that"}, {"abc", "c"} };
			String[] expect = new String[] { "", "", "that", "c" };

			Check.checkTests(strs, expect, "", "", (strSet) -> StringModify.commonSuffix(0, strSet));
		}
	}


	public static void arrayUtilTest() {
		;
	}

}
