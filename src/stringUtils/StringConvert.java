package stringUtils;

import java.io.IOException;

/** Convert strings to and from various formats.<br>
 * This class includes methods for XML character escaping and user definable char escaping.
 * For example, convert XML special characters like {@code '&'} and {@code '<', '>'}
 * to their escape values {@code "&amp;"}, {@code "&lt;", "&gt;"}.
 * @author TeamworkGuy2
 * @since 2014-12-19
 */
public final class StringConvert {

	private StringConvert() { throw new AssertionError("cannot instantiate static class StringConvert"); }


	/** Convert an XML string containing invalid XML characters into XML values (&amp; &apos; etc.) by replacing
	 * invalid characters with their corresponding character codes
	 * @param content the String to convert non-XML character to XML characters
	 * @return String with invalid XML characters replaced with XML character codes
	 */
	public static String escapeXml(String content) {
		if(content.indexOf("&") == -1 && content.indexOf("'") == -1 && content.indexOf("\"") == -1 &&
				content.indexOf("<") == -1 && content.indexOf(">") == -1) {
			return content;
		}
		StringBuilder validated = new StringBuilder(content);
		int index = 0;
		index = validated.indexOf("&", 0);
		while(index > -1) {
			validated.replace(index, index+1, "&amp;");
			index = validated.indexOf("&", index+1);
		}
		index = validated.indexOf("'", 0);
		while(index > -1) {
			validated.replace(index, index+1, "&apos;");
			index = validated.indexOf("'", index+1);
		}
		index = validated.indexOf("\"", 0);
		while(index > -1) {
			validated.replace(index, index+1, "&quot;");
			index = validated.indexOf("\"", index+1);
		}
		index = validated.indexOf("<", 0);
		while(index > -1) {
			validated.replace(index, index+1, "&lt;");
			index = validated.indexOf("<", index+1);
		}
		index = validated.indexOf(">", 0);
		while(index > -1) {
			validated.replace(index, index+1, "&gt;");
			index = validated.indexOf(">", index+1);
		}
		return validated.toString();
	}


	/** Convert an XML string containing XML character codes (&amp; &apos; etc.) by replacing
	 * them with the corresponding character
	 * @param content the String to convert XML to non-XML characters (&amp; &quot; etc.)
	 * @return String with XML characters replaced with normal characters
	 */
	public static String unescapeXml(String content) {
		if(content.indexOf("&") == -1) {
			return content;
		}
		StringBuilder converted = new StringBuilder(content);
		int index = 0;
		index = converted.indexOf("&amp;", 0);
		while(index > -1) {
			converted.replace(index, index+5, "&");
			index = converted.indexOf("&amp;", index+1);
		}
		index = converted.indexOf("&apos;", 0);
		while(index > -1) {
			converted.replace(index, index+6, "'");
			index = converted.indexOf("&apos;", index+1);
		}
		index = converted.indexOf("&quot;", 0);
		while(index > -1) {
			converted.replace(index, index+6, "\"");
			index = converted.indexOf("&quot;", index+1);
		}
		index = converted.indexOf("&lt;", 0);
		while(index > -1) {
			converted.replace(index, index+4, "<");
			index = converted.indexOf("&lt;", index+1);
		}
		index = converted.indexOf("&gt;", 0);
		while(index > -1) {
			converted.replace(index, index+4, ">");
			index = converted.indexOf("&gt;", index+1);
		}
		return converted.toString();
	}



	/** Add escapes to special characters in a sequence of characters<br>
	 * For example, given:<br>
	 * {@code str = "a \"block\" char '\"'"}<br>
	 * a call to:<br>
	 * {@code escape(str, '\\', '\"', '\\', new StringBuilder())}<br>
	 * would return with the contents of the last, appendable, parameter equal to:<br>
	 * {@code a \\\"block\\\" char '\\\"'}
	 * @param str the input character sequence to escape
	 * @param escapeChar the escape character to add before {@code escape1} and {@code escape2}
	 * @param escape1 the first character to escape, this is normally a special character, like {@code quote "}
	 * @param escape2 the second character to escape, this is normally the escape character itself
	 * @param dst the destination to write the escape characters to
	 * @see StringModify#unescape(CharSequence, int, char, char, Appendable)
	 */
	public static final void escape(CharSequence str, char escapeChar, char escape1, char escape2, Appendable dst) {
		try {
			for(int i = 0, size = str.length(); i < size; i++) {
				char chI = str.charAt(i);
				if(chI == escape1) {
					dst.append(escapeChar);
					dst.append(escape1);
				}
				else if(chI == escape2) {
					dst.append(escapeChar);
					dst.append(escape2);
				}
				else {
					dst.append(chI);
				}
			}
		} catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}


	/** Unwrap a sequence of escaped characters.<br>
	 * For example, given:<br>
	 * {@code src = "a \\\"block\\\" char '\\\"'"}<br>
	 * a call to:<br>
	 * {@code unescape(src, 0, '\\', '"', new StringBuilder())}<br>
	 * would return {@code 21} (the index of the end character or end of the string)<br>
	 * and the last, appendable, parameter would contain:<br>
	 * {@code a "block" char '"'}
	 * @param src the input character sequence to read characters from
	 * @param offset the offset into {@code src} at which to start unwrapping characters
	 * @param escapeChar the escape character to drop 
	 * @param chEnd stop parsing when this character is encountered in the {@code src} stream
	 * @param dst the destination to write unwrapped characters to
	 * @return the index of the {@code chEnd} that parsing stopped at,
	 * or the length of the {@code src} string if no {@code chEnd} character was encountered
	 * @see StringModify#escape(CharSequence, char, char, char, Appendable) 
	 */
	public static final int unescape(CharSequence src, int offset, char escapeChar, char chEnd, Appendable dst) {
		int i = offset;
		try {
			for(int size = src.length(); i < size; i++) {
				char chI = src.charAt(i);
				if(chI == chEnd) {
					return i;
				}
				if(chI == escapeChar) {
					i++;
					if(i >= size) {
						return i;
					}
					chI = src.charAt(i);
				}
				dst.append(chI);
			}
		} catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		return i;
	}


	// TODO what does this and unwrapChar() do, provide examples
	public static final void wrapChar(String str, char chReplaceBefore, char ch1, char ch2, Appendable dst) {
		try {
			for(int i = 0, size = str.length(); i < size; i++) {
				char chI = str.charAt(i);
				if(chI == ch1) {
					dst.append(chReplaceBefore);
					dst.append(ch1);
				}
				else if(chI == ch2) {
					dst.append(chReplaceBefore);
					dst.append(ch2);
				}
				else {
					dst.append(chI);
				}
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * @param strSrc the source to read characters from
	 * @param offset the offset into {@code strSrc} at which to start reading characters
	 * @param chReplace the char to replace
	 * @param chEnd stop reading when this char is reached
	 * @param dst the destination to store the read characters in
	 * @return the index of the {@code chEnd} that parsing stopped at
	 */
	public static final int unwrapChar(CharSequence strSrc, int offset, char chReplace, char chEnd, Appendable dst) {
		int i = offset;
		try {
			for(int size = strSrc.length(); i < size; i++) {
				char chI = strSrc.charAt(i);
				if(chI == chEnd) {
					return i;
				}
				if(chI == chReplace) {
					i++;
					if(i >= size) {
						return i;
					}
					chI = strSrc.charAt(i);
				}
				dst.append(chI);
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return i;
	}

}
