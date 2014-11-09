package dataUtility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/** A utility for simple string manipulation
 * @author TeamworkGuy2
 * @since 2013-12-21
 */
public final class StringTransformIo {
	/** The default size of a contain to hold a split string if the maximum number of splits
	 * is undefined or greater than MAX_SPLIT_SIZE */
	private static final int DEFAULT_SPLIT_SIZE = 10;
	/** The maximum size of a contain to hold split strings, if the split limit is greater than
	 * this value, than default to {@link StringUtility#DEFAULT_SPLIT_SIZE DEFAULT_SPLIT_SIZE} */
	private static final int MAX_SPLIT_SIZE = 100;
	public static final char escapeStart = '\\';


	private StringTransformIo() { throw new AssertionError("cannot instantiate static class StringUtility"); }


	/** A slightly faster version of {@link String#split(String)} that does not
	 * used {@link Pattern}, instead the pattern is interpreted literally
	 * and the input string is split based on the literal split string.
	 * @param input the input char sequence to split
	 * @param pattern the exact pattern to find and split around
	 * @param limit the maximum number of splits to make, zero indicates that
	 * an infinite number of splits can occur
	 * @return an array of strings containing the resulting stings after
	 * splitting the input string
	 */
	public static final String[] split(String input, String pattern, int limit) {
		if(input == null || pattern == null) {
			return new String[] {input};
		}
		// If the limit is zero (meaning an infinite number of splits) make the
		// limit negative so that it never matches the comparison in the loop
		limit = (limit == 0) ? -1 : limit;
		int inputSize = input.length();
		int patternSize = pattern.length();
		int index = 0;
		int indexPlusPatternSize = 0;
		int nextIndex = 0;
		int matchingCount = 0;
		ArrayList<String> list = new ArrayList<String>((limit > 0 && limit < MAX_SPLIT_SIZE) ?
				limit : DEFAULT_SPLIT_SIZE);
		// Since the first .indexOf() call uses index+patternSize, we want the first index to be at 0
		index = -patternSize;
		// Keep adding new strings until on cannot be found
		// (reaching the maximum number of strings is handled inside the loop)
		do {
			indexPlusPatternSize = index+patternSize; // small optimization (me being picky)
			// Find the next matching string index after the current matching string index
			nextIndex = input.indexOf(pattern, indexPlusPatternSize);
			// If the maximum number of strings have been match, set the next index to -1
			// so that the next statement acts as if the end of the string has been reached
			if(matchingCount == limit-1) { nextIndex = -1; }
			// If no more matching strings can be found, include the remainder of
			// the string after the last matching string
			nextIndex = (nextIndex == -1) ? inputSize : nextIndex;
			// If the matching string index is greater than or equal to the end
			// of the string, then break, there is no more string left to parse
			if(indexPlusPatternSize > inputSize) { break; }
			// Add the new sub string between the end of the previous matching
			// pattern and the next matching pattern to the list of splits
			list.add(input.substring(indexPlusPatternSize, nextIndex));
			// If the next found index is the end of the string, set the index
			// to -1 so that the outer while loop ends, else keep the current index
			index = (nextIndex == inputSize) ? -1 : nextIndex;
			// Increment the number of split sub strings
			matchingCount++;
		} while(index != -1); // While at end because (index = -patternSize) could equal -1 if the pattern size is -1
		// so initialize everything first and run the first loop before evaluating the while statement
		// Return the array of split sub strings
		return list.toArray(new String[matchingCount]);
	}


	/** A slightly faster version of {@link String#split(String)} that does not
	 * used {@link Pattern}, instead the pattern is interpreted literally
	 * and the input string is split based on the literal split string.<br/>
	 * This method is more space efficient than the {@link StringUtility#split(String, String, int)} version
	 * since no internal structure is created to store the split strings, instead the array provided
	 * is filled with the split strings.
	 * @param input the input char sequence to split
	 * @param pattern the exact pattern to find and split around
	 * @param dst an array of strings equal in length to the number of strings to split the {@code input} string into
	 * @return the {@code results} array of strings passed into the method
	 */
	public static final String[] split(String input, String pattern, String[] dst) {
		if(input == null || pattern == null) {
			return new String[] {input};
		}
		// If the limit is zero (meaning an infinite number of splits) make the
		// limit negative so that it never matches the comparison in the loop
		int limit = dst.length;
		int inputSize = input.length();
		int patternSize = pattern.length();
		int index = 0;
		int indexPlusPatternSize = 0;
		int nextIndex = 0;
		int matchingCount = 0;
		// Since the first .indexOf() call uses index+patternSize, we want the first index to be at 0
		index = -patternSize;
		// Keep adding new strings until on cannot be found
		// (reaching the maximum number of strings is handled inside the loop)
		do {
			indexPlusPatternSize = index+patternSize; // small optimization (me being picky)
			// Find the next matching string index after the current matching string index
			nextIndex = input.indexOf(pattern, indexPlusPatternSize);
			// If the maximum number of strings have been match, set the next index to -1
			// so that the next statement acts as if the end of the string has been reached
			if(matchingCount == limit-1) { nextIndex = -1; }
			// If no more matching strings can be found, include the remainder of
			// the string after the last matching string
			nextIndex = (nextIndex == -1) ? inputSize : nextIndex;
			// If the matching string index is greater than or equal to the end
			// of the string, then break, there is no more string left to parse
			if(indexPlusPatternSize > inputSize) { break; }
			// Add the new sub string between the end of the previous matching
			// pattern and the next matching pattern to the list of splits
			dst[matchingCount] = input.substring(indexPlusPatternSize, nextIndex);
			// If the next found index is the end of the string, set the index
			// to -1 so that the outer while loop ends, else keep the current index
			index = (nextIndex == inputSize) ? -1 : nextIndex;
			// Increment the number of split sub strings
			matchingCount++;
		} while(index != -1); // While at end because (index = -patternSize) could equal -1 if the pattern size is -1,
		// so initialize everything first and run the first loop before evaluating the while statement
		// Return the array of split sub strings
		return dst;
	}


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
	 * @see StringTransformIo#unescape(CharSequence, int, char, char, Appendable)
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
	 * @see StringTransformIo#escape(CharSequence, char, char, char, Appendable) 
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
					dst.append(src.charAt(i));
				}
				else {
					dst.append(chI);
				}
			}
		} catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		return i;
	}


	/** Check for a common prefix among the keys an array of {@link Map#Entry} values
	 * @param offset the offset into each key string to start comparing values
	 * @param strs an array of map entries to compare the keys of
	 * @return the common string prefix of all of the map entry keys
	 */
	public static String commonPrefix(int offset, Map.Entry<String, String>[] strs) {
		StringBuilder strB = new StringBuilder();
		String firstString = strs[0].getKey();
		int size = strs.length;
		for(int i = offset; ; i++) {
			boolean match = true;
			if(i >= firstString.length()) {
				return strB.toString();
			}
			char c = firstString.charAt(i);
			for(int ii = 0; ii < size; ii++) {
				String str = strs[ii].getKey();
				if(i >= str.length()) {
					return strB.toString();
				}
				if(str.charAt(i) != c) {
					match = false;
					break;
				}
			}
			if(match) {
				strB.append(c);
			}
			else {
				return strB.toString();
			}
		}
	}


	/** Find a common prefix of an array of strings, for example, if:
	 * {@code strs = ["v_alpha", "v_beta", "v_be"]}, the returned prefix would be {@code "v_"}
	 * @param stringOffset the offset into each string at which to start searching for a common prefix
	 * @param strs the array of strings to search
	 * @return the common prefix shared by all the strings searched or an empty
	 * string if there is no common prefix
	 */
	public static String commonPrefix(int stringOffset, String[] strs) {
		StringBuilder strB = new StringBuilder();
		String firstString = strs[0];
		for(int i = stringOffset; ; i++) {
			boolean match = true;
			if(i >= firstString.length()) {
				return strB.toString();
			}
			char c = firstString.charAt(i);
			for(String str : strs) {
				if(i >= str.length()) {
					return strB.toString();
				}
				if(str.charAt(i) != c) {
					match = false;
					break;
				}
			}
			if(match) {
				strB.append(c);
			}
			else {
				return strB.toString();
			}
		}
	}


	/** Find a common suffix of an array of strings, for example, if:
	 * {@code strs = ["jumping", "swimming", "laughing"]}, the returned prefix would be {@code "ing"}
	 * @param stringOffset the offset from the end of each string at which to start searching for a common suffix
	 * @param strs the array of strings to search
	 * @return the common suffix shared by all the strings searched or an empty
	 * string if there is no common suffix
	 */
	public static String commonSuffix(int offset, String[] strs) {
		StringBuilder strB = new StringBuilder();
		String firstString = strs[0];
		// i = 1 because suffixes are searched in reverse order starting at {@code string.length()-i}
		for(int i = 1+offset; ; i++) {
			boolean match = true;
			if(i > firstString.length()) {
				strB.reverse();
				return strB.toString();
			}
			char c = firstString.charAt(firstString.length()-i);
			for(String str : strs) {
				if(i > str.length()) {
					return strB.toString();
				}
				if(str.charAt(str.length()-i) != c) {
					match = false;
					break;
				}
			}
			if(match) {
				strB.append(c);
			}
			else {
				return strB.toString();
			}
		}
	}


	/** Parse a string into an array of strings by splitting at spaces and
	 * quotes. Primarily used to parse program/command-line parameter strings into a lists of parameters
	 * @param param the string to split
	 * @return a list of strings containing the original string split at
	 * spaces or quotes. Quotes without spaces before them are not split.
	 * Spaces inside quotes are not split.
	 */
	public static final List<String> parseParameters(String param) {
		param = param.trim();
		final int size = param.length();
		List<String> params = new ArrayList<String>();
		char quote = '"';
		boolean lookForQuote = false;
		boolean lookForWhitespace = false;
		boolean finishedQuotes = false;
		int startIndex = -1;
		char c = 0;
		// Initialize the parsing by determining of the string starts with quoted section of text or not
		if(param.charAt(0) == quote) {
			lookForQuote = true;
			startIndex = -1;
		}
		else {
			lookForWhitespace = true;
			startIndex = -1;
		}
		// Loop through every character in the string to parse it
		for(int i = 0; i < size; i++) {
			c = param.charAt(i);
			// The end of a quoted chunk of text, {@code i != 0} to avoid recognizing a starting quote as
			// the start and end of a quoted section of text
			if(i != 0 && lookForQuote && c == quote) {
				lookForQuote = false;
				finishedQuotes = true;
				continue;
			}
			// The start of a quoted chunk of text
			if(c == quote) {
				lookForQuote = true;
				if(lookForWhitespace) {
					lookForWhitespace = false;
				}
				else {
					startIndex = i-1;
				}
			}
			// The end of a whitespace separated parameter
			if(!lookForQuote && lookForWhitespace && Character.isWhitespace(c)) {
				params.add(param.substring(startIndex+1, i));
				lookForWhitespace = false;
				startIndex = -1;
				//continue;
			}
			// The beginning of a whitespace separated parameter
			if(!lookForQuote && Character.isWhitespace(c)) {
				lookForWhitespace = true;
				if(finishedQuotes) {
					finishedQuotes = false;
					params.add(param.substring(startIndex+1, i+1));
					//startIndex = -1;
				}
				startIndex = i;
			}
		}
		if(startIndex != -1) {
			params.add(param.substring(startIndex+1, param.length()));
			startIndex = -1;
		}
		else if(params.size() == 0) {
			params.add(param.substring(0, param.length()));
			startIndex = -1;
		}
		return params;
	}


	/** Convert a list of key-value pair strings to valid key-value strings
	 * that can be written to an output destination and later read and parsed
	 * by {@link #loadKeyValueStrings(List, Collection)}.
	 * The key-value pairs stored in the list should be written in the format:
	 * {@code key + '=' + value}.
	 * @param keyValueStrings a list of key-value pairs to convert to valid
	 * strings that can be written in the format {@code (key + "=" + value)}.
	 * Each value in the list is overwritten with a new key-value pair containing the
	 * previous key-value converted to valid output strings.
	 * @param dst the list of key-value strings to write the resulting key-value strings to.
	 * This parameter may be the same object as {@code keyValueStrings}
	 * @see java.util.Properties#saveConvert()
	 */
	public static final void saveKeyValueStrings(List<Map.Entry<String, String>> keyValueStrings,
			List<Map.Entry<String, String>> dst) {
		StringBuilder buf = new StringBuilder();
		for(int i = 0, size = keyValueStrings.size(); i < size; i++) {
			Map.Entry<String, String> keyValuePair = keyValueStrings.get(i);
			String key = saveKeyValueString(keyValuePair.getKey(), true, false, buf).toString();
			buf.setLength(0);
			String value = saveKeyValueString(keyValuePair.getValue(), false, false, buf).toString();
			buf.setLength(0);

			Map.Entry<String, String> resultEntry = new AbstractMap.SimpleImmutableEntry<String, String>(key, value);
			if(dst.size() <= i) {
				dst.set(i, resultEntry);
			}
			else {
				dst.add(resultEntry);
			}
		}
	}


	private static final <T extends Appendable> T saveKeyValueString(String str, boolean escSpaces,
			boolean escUnicode, T dst) {
		int len = str.length();
		try {
			for(int x = 0; x < len; x++) {
				char a = str.charAt(x);
				if((a > 61) && (a < 127)) {
					if(a == '\\') {
						dst.append("\\\\");
						continue;
					}
					dst.append(a);
					continue;
				}
				switch(a) {
				case ' ':
					if(x == 0 || escSpaces) {
						dst.append('\\');
					}
					dst.append(' ');
					break;
				case '\t': dst.append("\\t");
				break;
				case '\n': dst.append("\\n");
				break;
				case '\r': dst.append("\\r");
				break;
				case '\f': dst.append("\\f");
				break;
				case '=': // fall through
				case ':': // fall through
				case '#': // fall through
				case '!': // handle {= : # !}
					dst.append('\\').append(a);
					break;
				default:
					if(((a < 0x0020) || (a > 0x007E)) && escUnicode) {
						dst.append("\\u");
						dst.append(StringTransformIo.toHex((a >> 12) & 0xF));
						dst.append(StringTransformIo.toHex((a >> 8) & 0xF));
						dst.append(StringTransformIo.toHex((a >> 4) & 0xF));
						dst.append(StringTransformIo.toHex(a & 0xF));
					}
					else {
						dst.append(a);
					}
				}
			}
		} catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		return dst;
	}


	/** Parse a list of strings into a collection of key-value pairs.
	 * Each string should contain a '=' which separates the key (on the left side of '=')
	 * from the value (on the right side of '='). The string is split into a pair of strings
	 * and each pair is added to the {@code dst} collection of {@link java.util.Map.Entry Map.Entries}.
	 * @param strs a list of strings, each of which contains a '=' character splitting the
	 * string into a key-value pair
	 * @param dst the destination collection of key-value entries in which to store the parsed
	 * key-value string pairs
	 * @see java.util.Properties#load0()
	 */
	public static final void loadKeyValueStrings(List<String> strs, Collection<Map.Entry<String, String>> dst) {
		StringBuilder strB = new StringBuilder();
		int totalLength;
		int keyLength;
		int valueStart;
		boolean hasSeparator = false;
		boolean precedingBackslash = false;

		for(int i = 0, size = strs.size(); i < size; i++) {
			String str = strs.get(i);
			totalLength = str.length();
			keyLength = 0;
			valueStart = totalLength;
			for(int ii = 0; ii < totalLength; ii++) {
				char c = str.charAt(ii);
				if((c == '=' || c == ':') && !precedingBackslash) {
					valueStart = ii+1;
					hasSeparator = true;
					break;
				}
				else if((c == ' ' || c == '\t' || c == '\f') && !precedingBackslash) {
					valueStart = ii+1;
					break;
				}
				if(c == '\\') {
					precedingBackslash = true;
				}
				else {
					precedingBackslash = false;
				}
			}
			for( ; valueStart < totalLength; valueStart++) {
				char c = str.charAt(valueStart);
				if(c != ' ' && c != '\t' && c != '\f') {
					if(!hasSeparator && (c == '=' || c == ':')) {
						hasSeparator = true;
					}
					else {
						break;
					}
				}
			}
			try {
				loadKeyValueString(str, 0, keyLength, strB);
				String key = strB.toString();
				strB.setLength(0);
				loadKeyValueString(str, valueStart, totalLength - valueStart, strB);
				String value = strB.toString();
				strB.setLength(0);
				dst.add(new AbstractMap.SimpleImmutableEntry<String, String>(key, value));
			} catch (IOException e) {
				throw new Error("StringBuilder threw IOException", e);
			}
		}
	}


	private static final <T extends Appendable> void loadKeyValueString(String str, int off, int len, T dst)
			throws IOException {
		char a;
		int end = off + len;
		while(off < end) {
			a = str.charAt(off++);
			if(a == '\\') {
				a = str.charAt(off++);
				if(a == 'u') {
					int value = 0;
					for(int i = 0; i < 4; i++) {
						a = str.charAt(off++);
						switch(a) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + a - '0';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + a - 'A';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + a - 'a';
							break;
						default:
							throw new IllegalArgumentException("illegal \\uxxxx character encoding");
						}
					}
					dst.append((char)value);
				}
				else {
					if(a == 't') { a = '\t'; }
					else if(a == 'r') { a = '\r'; }
					else if(a == 'n') { a = '\n'; }
					else if(a == 'f') { a = '\f'; }
					dst.append(a);
				}
			}
			else {
				dst.append(a);
			}
		}
	}


	/** Write the hexadecimal value of the specified byte array to the specified
	 * appendable output.
	 * @param hexBytes the array of bytes to read values from
	 * @param offset the offset into the byte array to start converting bytes to hexadecimal digits
	 * @param length the number of bytes to convert, starting at the offset index in the array
	 * @param output the {@link Appendable} output to write the hexadecimal digits to
	 * @throws IOException if there is an error writing to the output stream
	 */
	public static final void writeHexString(final byte[] hexBytes, final int offset, final int length,
			final Appendable output) throws IOException {
		final int size = offset+length;
		for(int i = offset; i < size; i++) {
			byte b = hexBytes[i];
			byte v = (byte)((b >>> 4) & 0x0F);
			//output.append((char)(v < 10 ? (v+48) : (v+55)));
			output.append((char)(55 + v + (((v-10) >> 31) & -7)));
			v = (byte)(b & 0x0F);
			//output.append((char)(v < 10 ? (v+48) : (v+55)));
			output.append((char)(55 + v + (((v-10) >> 31) & -7)));
		}
	}


	/** Write the hexadecimal value of the specified byte array to a string
	 * @param hexBytes the array of bytes to convert to a hexadecimal string
	 * @return a string containing the hexadecimal value of the input byte array
	 */
	public static final String toHexString(final byte[] hexBytes) {
		return toHexString(hexBytes, 0, hexBytes.length);
	}


	/** Write the hexadecimal value of the specified byte array to a string
	 * @param hexBytes the array of bytes to convert to a hexadecimal string
	 * @param offset the offset into the byte array to start converting bytes to hexadecimal digits
	 * @param length the number of bytes to convert, starting at the offset index in the array
	 * @return a string containing the hexadecimal value of the input byte array
	 * @throws IOException if there is an error writing to the output stream
	 */
	public static final String toHexString(final byte[] hexBytes, final int offset, final int length) {
		final int size = offset+length;
		char[] c = new char[size << 1];
		for(int i = offset, a = 0; i < size; i++, a+=2) {
			byte b = hexBytes[i];
			byte v = (byte)((b >>> 4) & 0x0F);
			//output.append((char)(v < 10 ? (v+48) : (v+55)));
			c[a] = (char)(55 + v + (((v-10) >> 31) & -7));
			v = (byte)(b & 0x0F);
			//output.append((char)(v < 10 ? (v+48) : (v+55)));
			c[a+1] = (char)(55 + v + (((v-10) >> 31) & -7));
		}
		return new String(c, 0, size << 1);
	}


	/** Convert a hexadecimal string to an array of bytes.
	 * If the string's length is not divisble by 2, the last hexadecimal character
	 * is stored in the high nibble of the last byte of the returned byte array.
	 * For example, if the string contains the characters "{@code 3A9}", the byte array
	 * returned is {@code [0x3A, 0x90]}.
	 * @param input string to convert to an array of bytes.
	 * @return an array of bytes containing the interpreted byte values of the hexadecimal string
	 */
	public static final byte[] decodeHexString(final String input) {
		// Note whether the string length is odd
		boolean oddCount = (input.length() & 0x1) == 1;
		int len = input.length() / 2;
		byte[] bytes = new byte[len + (input.length() & 0x1)];
		// Convert pairs of hex characters from the string into bytes
		for(int i = 0; i < len; i++) {
			char c = input.charAt(i << 1);
			byte v1 = (byte)((c-48+(((64-c) >> 31) & -7)) << 4);
			c = input.charAt((i << 1) + 1);
			byte v2 = (byte)((c-48+(((64-c) >> 31) & -7)) & 0xF);
			bytes[i] = (byte)(v1 | v2);
		}
		// If the string length was odd, save it until this point
		// and write it as one character
		if(oddCount) {
			char c = input.charAt(len << 1);
			byte v1 = (byte)((c-48+(((64-c) >> 31) & -7)) << 4);
			bytes[len] = (byte)(v1);
		}

		return bytes;
	}


	/** Convert a stream of US-ASCII encoded hexadecimal characters to an array of bytes.
	 * If the stream's length is not divisible by 2, the last hexadecimal character
	 * is stored in the high nibble of the last byte of the returned byte array.
	 * For example, if the reader contains the data "{@code 3A9}", the byte array
	 * returned is {@code [0x3A, 0x90]}.
	 * @param input string to convert to an array of bytes.
	 * @return an array of bytes containing the interpreted byte values of the hexadecimal stream
	 * @throws IOException if there is an error reading from the input reader
	 */
	public static final byte[] decodeHexStream(final Reader input) throws IOException {
		int size = 8192; // must be divisible by 2
		ByteArrayOutputStream out = new ByteArrayOutputStream(size);
		char[] cbuf = new char[size];
		boolean lastReadOdd = false;
		byte lastByte = 0;
		int read = -1;
		// Read blocks of the stream and convert to hex, two chararacters at a time
		while((read = input.read(cbuf)) != -1) {
			int offset = 0;
			// If the last block was an odd length, combine it's last character and this block's first character
			if(lastReadOdd == true) {
				char c = cbuf[0];
				byte v2 = (byte)((c-48+(((64-c) >> 31) & -7)) & 0xF);
				out.write((byte)(lastByte | v2));
				offset = 1;
			}
			// Convert the rest of the block two characters at a time
			lastReadOdd = false;
			// If the block length is odd, don't write the last character,
			// wait to see if it is the end of the stream
			if(((read-offset) & 0x1) == 1) {
				read--;
				for(int i = offset; i < read; i+=2) {
					char c = cbuf[i];
					byte v1 = (byte)((c-48+(((64-c) >> 31) & -7)) << 4);
					c = cbuf[i+1];
					byte v2 = (byte)((c-48+(((64-c) >> 31) & -7)) & 0xF);
					out.write((byte)(v1 | v2));
				}
				char c = cbuf[read];
				lastByte = (byte)((c-48+(((64-c) >> 31) & -7)) << 4);
				lastReadOdd = true;
			}
			else {
				for(int i = offset; i < read; i+=2) {
					char c = cbuf[i];
					byte v1 = (byte)((c-48+(((64-c) >> 31) & -7)) << 4);
					c = cbuf[i+1];
					byte v2 = (byte)((c-48+(((64-c) >> 31) & -7)) & 0xF);
					out.write((byte)(v1 | v2));
				}
			}
		}
		// If the block length was odd on the last block, write the last character now
		if(lastReadOdd == true) {
			out.write((byte)(lastByte));
		}
		return out.toByteArray();
	}


	/** Convert the lower four bits of a value to a hex character
	 * @param b the value to convert to a hex character, only the lowest four bits are used to calculate the hex value
	 * @return the hex character of the four lowest bits of the value
	 */
	public static final char toHex(int b) {
		return (char)((b = (b & 0xF)) < 10 ? (b+48) : (b+55));
	}


	/** Convert a hexadecimal character to a byte value between [0, 15]
	 * @param c the character, one of: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F, to convert to a integer value
	 * @return the value of the hex digit, or 0 if the digit is not a valid hex digit
	 */
	public static final byte hexToByte(char c) {
		return (byte)((c > 47 && c < 58) ? (c-48) : (c > 64 && c < 71) ? (c-55) : 0);
	}

}
