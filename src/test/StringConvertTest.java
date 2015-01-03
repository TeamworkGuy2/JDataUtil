package test;

import org.junit.Assert;
import org.junit.Test;

import stringUtils.StringConvert;

/**
 * @author TeamworkGuy2
 * @since 2015-1-2
 */
public class StringConvertTest {

	@Test
	public void unescapeTest() {
		String[] inputs = new String[] {
				" -a \\\"block\\\" char '\\\"'",
				"1. \\\\abc",
				"2. \\abc",
				"= \\\"",
		};
		String[] expect = new String[] {
				"a \"block\" char '\"'",
				" \\abc",
				" abc",
				"\"",
		};
		StringBuilder dst = new StringBuilder();

		for(int i = 0, size = inputs.length; i < size; i++) {
			StringConvert.unescape(inputs[i], 2, '\\', '"', dst);
			Assert.assertEquals(expect[i], dst.toString());
			dst.setLength(0);
		}
	}


	@Test
	public void unescapePartialQuotedTest() {
		String[] inputs = new String[] {
				"with \"quoted block\"",
				" \\abc, xyz",
				" abc, xyz",
				"\\\"\"",
		};
		String[] expect = new String[] {
				"with \"quoted block\"",
				" \\abc",
				" abc",
				"\\\"\"",
		};
		StringBuilder dst = new StringBuilder();

		for(int i = 0, size = inputs.length; i < size; i++) {
			StringConvert.unescapePartialQuoted(inputs[i], 0, '\\', '"', ',', ']', dst);
			Assert.assertEquals(expect[i], dst.toString());
			dst.setLength(0);
		}
	}


	@Test
	public void escapeXml() {
		String[] inputs = new String[] {
				"<!-- xml comment -->",
				"<tag>with & in</tag>",
				"text~='string'",
				"<\"",
				""
		};
		String[] expect = new String[] {
				"&lt;!-- xml comment --&gt;",
				"&lt;tag&gt;with &amp; in&lt;/tag&gt;",
				"text~=&apos;string&apos;",
				"&lt;&quot;",
				""
		};
		StringBuilder dst = new StringBuilder();

		for(int i = 0, size = inputs.length; i < size; i++) {
			StringConvert.escapeXml(inputs[i], dst);
			Assert.assertEquals(expect[i], dst.toString());
			dst.setLength(0);
		}
	}


	@Test
	public void unescapeXml() {
		String[] inputs = new String[] {
				"&lt;!-- xml comment --&gt;",
				"&lt;tag&gt;with &amp; in&lt;/tag&gt;",
				"text~=&apos;string&apos;",
				"&lt;&quot;",
				""
		};
		String[] expect = new String[] {
				"<!-- xml comment -->",
				"<tag>with & in</tag>",
				"text~='string'",
				"<\"",
				""
		};
		StringBuilder dst = new StringBuilder();

		for(int i = 0, size = inputs.length; i < size; i++) {
			StringConvert.unescapeXml(inputs[i], dst);
			Assert.assertEquals(expect[i], dst.toString());
			dst.setLength(0);
		}
	}

}
