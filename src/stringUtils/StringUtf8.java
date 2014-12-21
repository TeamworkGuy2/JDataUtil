package stringUtils;


/**
 * @author TeamworkGuy2
 * @since 2014-12-8
 */
// TODO for fun test implementation
public class StringUtf8 implements CharSequence {
	private byte[] chars;
	private int len;


	public StringUtf8(char[] chars) {
		this(chars, 0, chars.length);
	}


	public StringUtf8(char[] chars, int off, int len) {
		;
	}


	@Override
	public int length() {
		return len;
	}


	@Override
	public char charAt(int index) {
		return 0;
	}


	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}


	private static int indexUtf8(int index, byte[] chars, int off, int len) {
		int i = off;
		for(int indexCount = 0, size = off + len; indexCount < index && i < size; i++) {
			// more than one byte
			if((chars[i] & 0x70) == 1) {
				checkCodepointIndex(i + 1, size);
				if((chars[i + 1] & 0x70) == 1) {
					checkCodepointIndex(i + 2, size);
					if((chars[i + 2] & 0x70) == 1) {
						checkCodepointIndex(i + 3, size);
						if((chars[i + 3] & 0x70) == 1) {
							throw new StringIndexOutOfBoundsException("multi-byte char longer than 4 bytes not supported starting at index " + i);
						}
						// four byte char
						else {
							i += 4;
							indexCount++;
						}
					}
					// three byte char
					else {
						i += 3;
						indexCount++;
					}
				}
				// two byte char
				else {
					i += 2;
					indexCount++;
				}
			}
			// one byte char
			else {
				i++;
				indexCount++;
			}
		}
		return i;
	}


	private static void checkCodepointIndex(int index, int size) {
		if(index >= size) {
			throw new StringIndexOutOfBoundsException("end of string reached before end of multi-byte char");
		}
	}

}
