package tests;

import java.util.Arrays;
import java.util.List;

import checks.Check;
import dataUtility.StringIndex;
import dataUtility.StringReplace;
import dataUtility.StringTransformIo;

/**
 * @author TeamworkGuy2
 * @since 2014-8-24
 */
public class StringAndArrayTests {

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
			System.out.println(Arrays.toString(StringTransformIo.split(match, "//", 5)));
			System.out.println(Arrays.toString(match.split("//", 5)));
		}
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


	public static void arrayUtilTest() {
		;
	}


	public static void main(String[] args) {
		//stringReplaceTest();
		indexOfNotPrefixedByTest();
		//stringUtilTest();
		//arrayUtilTest();
	}

}
