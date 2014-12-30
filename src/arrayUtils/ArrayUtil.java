package arrayUtils;

import java.util.Arrays;

/** Methods for finding the index of a value in an array of values, concat arrays
 * together, reverse an array of values, and calculate the sum, average,
 * minimum, or maximum of a numeric array.
 * @author TeamworkGuy2
 * @since 2014-6-2
 */
public final class ArrayUtil {
	public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
	public static final short[] EMPTY_SHORT_ARRAY = new short[0];
	public static final char[] EMPTY_CHAR_ARRAY = new char[0];
	public static final int[] EMPTY_INT_ARRAY = new int[0];
	public static final long[] EMPTY_LONG_ARRAY = new long[0];
	public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
	public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
	public static final String[] EMPTY_STRING_ARRAY = new String[0];
	public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];


	// start boolean array methods
	/** Convert a set of boolean values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	public static final boolean[] asArray(boolean... values) {
		return values;
	}


	/** Search for a matching boolean in an array of booleans
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(boolean[] ary, boolean value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching boolean in an array of booleans
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(boolean[] ary, int off, int len, boolean value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i] == (value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final boolean[] concat(boolean[] a, boolean[] b) {
		boolean[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an boolean array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(boolean[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an boolean array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(boolean[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			boolean temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}
	// end boolean array methods


	// start byte array methods
	/** Convert a set of byte values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	public static final byte[] asArray(byte... values) {
		return values;
	}


	/** Search for a matching byte in an array of bytes
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(byte[] ary, byte value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching byte in an array of bytes
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(byte[] ary, int off, int len, byte value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i] == (value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final byte[] concat(byte[] a, byte[] b) {
		byte[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an byte array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(byte[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an byte array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(byte[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			byte temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** Sum of an array
	 * @param ary the array of integers to sum
	 * @return the sum of all of the elements in the specified array
	 */
	public static final int sum(byte[] ary) {
		return sum(ary, 0, ary.length);
	}


	/** Sum of the subset of an array
	 * @param ary the array of bytes to sum
	 * @param offset the offset into the array at which to start summing values
	 * @param length the number of elements to sum from the array starting
	 * at {@code offset}
	 * @return the sum of the specified array subset
	 */
	public static final int sum(byte[] ary, int offset, int length) {
		int offLen = offset + length;
		int sum = (byte)0;
		for(int i = offset; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static final float avg(byte[] values) {
		return avg(values, 0, values.length);
	}


	/** The average of a subset of an array
	 * @param values the array of values to average
	 * @param offset the offset into the array at which to start averaging values
	 * @param length the number of values to average from the array
	 * @return the average of the array subset specified
	 */
	public static final float avg(byte[] values, int offset, int length) {
		int total = 0;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			total += values[i];
		}
		return total/(float)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final byte min(byte[] values) {
		return min(values, 0, values.length);
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final byte min(byte[] values, int offset, int length) {
		byte min = Byte.MAX_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final byte max(byte[] values) {
		return max(values, 0, values.length);
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final byte max(byte[] values, int offset, int length) {
		byte max = Byte.MIN_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}
	// end byte array methods


	// start short array methods
	/** Convert a set of short values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	public static final short[] asArray(short... values) {
		return values;
	}


	/** Search for a matching short in an array of shorts
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(short[] ary, short value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching short in an array of shorts
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(short[] ary, int off, int len, short value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i] == (value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final short[] concat(short[] a, short[] b) {
		short[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an short array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(short[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an short array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(short[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			short temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** Sum of an array
	 * @param ary the array of integers to sum
	 * @return the sum of all of the elements in the specified array
	 */
	public static final int sum(short[] ary) {
		return sum(ary, 0, ary.length);
	}


	/** Sum of the subset of an array
	 * @param ary the array of shorts to sum
	 * @param offset the offset into the array at which to start summing values
	 * @param length the number of elements to sum from the array starting
	 * at {@code offset}
	 * @return the sum of the specified array subset
	 */
	public static final int sum(short[] ary, int offset, int length) {
		int offLen = offset + length;
		int sum = (short)0;
		for(int i = offset; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static final float avg(short[] values) {
		return avg(values, 0, values.length);
	}


	/** The average of a subset of an array
	 * @param values the array of values to average
	 * @param offset the offset into the array at which to start averaging values
	 * @param length the number of values to average from the array
	 * @return the average of the array subset specified
	 */
	public static final float avg(short[] values, int offset, int length) {
		int total = 0;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			total += values[i];
		}
		return total/(float)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final short min(short[] values) {
		return min(values, 0, values.length);
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final short min(short[] values, int offset, int length) {
		short min = Short.MAX_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final short max(short[] values) {
		return max(values, 0, values.length);
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final short max(short[] values, int offset, int length) {
		short max = Short.MIN_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}
	// end short array methods


	// start char array methods
	/** Convert a set of char values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	public static final char[] asArray(char... values) {
		return values;
	}


	/** Search for a matching char in an array of chars
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(char[] ary, char value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching char in an array of chars
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(char[] ary, int off, int len, char value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i] == (value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final char[] concat(char[] a, char[] b) {
		char[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an char array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(char[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an char array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(char[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			char temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** Sum of an array
	 * @param ary the array of integers to sum
	 * @return the sum of all of the elements in the specified array
	 */
	public static final int sum(char[] ary) {
		return sum(ary, 0, ary.length);
	}


	/** Sum of the subset of an array
	 * @param ary the array of chars to sum
	 * @param offset the offset into the array at which to start summing values
	 * @param length the number of elements to sum from the array starting
	 * at {@code offset}
	 * @return the sum of the specified array subset
	 */
	public static final int sum(char[] ary, int offset, int length) {
		int offLen = offset + length;
		int sum = (char)0;
		for(int i = offset; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static final float avg(char[] values) {
		return avg(values, 0, values.length);
	}


	/** The average of a subset of an array
	 * @param values the array of values to average
	 * @param offset the offset into the array at which to start averaging values
	 * @param length the number of values to average from the array
	 * @return the average of the array subset specified
	 */
	public static final float avg(char[] values, int offset, int length) {
		int total = 0;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			total += values[i];
		}
		return total/(float)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final char min(char[] values) {
		return min(values, 0, values.length);
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final char min(char[] values, int offset, int length) {
		char min = Character.MAX_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final char max(char[] values) {
		return max(values, 0, values.length);
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final char max(char[] values, int offset, int length) {
		char max = Character.MIN_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}
	// end char array methods


	// start int array methods
	/** Convert a set of int values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	public static final int[] asArray(int... values) {
		return values;
	}


	/** Search for a matching int in an array of ints
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(int[] ary, int value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching int in an array of ints
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(int[] ary, int off, int len, int value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i] == (value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final int[] concat(int[] a, int[] b) {
		int[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an int array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(int[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an int array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(int[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			int temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** Sum of an array
	 * @param ary the array of integers to sum
	 * @return the sum of all of the elements in the specified array
	 */
	public static final int sum(int[] ary) {
		return sum(ary, 0, ary.length);
	}


	/** Sum of the subset of an array
	 * @param ary the array of ints to sum
	 * @param offset the offset into the array at which to start summing values
	 * @param length the number of elements to sum from the array starting
	 * at {@code offset}
	 * @return the sum of the specified array subset
	 */
	public static final int sum(int[] ary, int offset, int length) {
		int offLen = offset + length;
		int sum = 0;
		for(int i = offset; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static final float avg(int[] values) {
		return avg(values, 0, values.length);
	}


	/** The average of a subset of an array
	 * @param values the array of values to average
	 * @param offset the offset into the array at which to start averaging values
	 * @param length the number of values to average from the array
	 * @return the average of the array subset specified
	 */
	public static final float avg(int[] values, int offset, int length) {
		int total = 0;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			total += values[i];
		}
		return total/(float)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final int min(int[] values) {
		return min(values, 0, values.length);
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final int min(int[] values, int offset, int length) {
		int min = Integer.MAX_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final int max(int[] values) {
		return max(values, 0, values.length);
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final int max(int[] values, int offset, int length) {
		int max = Integer.MIN_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}
	// end int array methods


	// start long array methods
	/** Convert a set of long values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	public static final long[] asArray(long... values) {
		return values;
	}


	/** Search for a matching long in an array of longs
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(long[] ary, long value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching long in an array of longs
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(long[] ary, int off, int len, long value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i] == (value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final long[] concat(long[] a, long[] b) {
		long[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an long array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(long[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an long array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(long[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			long temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** Sum of an array
	 * @param ary the array of integers to sum
	 * @return the sum of all of the elements in the specified array
	 */
	public static final long sum(long[] ary) {
		return sum(ary, 0, ary.length);
	}


	/** Sum of the subset of an array
	 * @param ary the array of longs to sum
	 * @param offset the offset into the array at which to start summing values
	 * @param length the number of elements to sum from the array starting
	 * at {@code offset}
	 * @return the sum of the specified array subset
	 */
	public static final long sum(long[] ary, int offset, int length) {
		int offLen = offset + length;
		long sum = 0L;
		for(int i = offset; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static final double avg(long[] values) {
		return avg(values, 0, values.length);
	}


	/** The average of a subset of an array
	 * @param values the array of values to average
	 * @param offset the offset into the array at which to start averaging values
	 * @param length the number of values to average from the array
	 * @return the average of the array subset specified
	 */
	public static final double avg(long[] values, int offset, int length) {
		long total = 0;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			total += values[i];
		}
		return total/(double)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final long min(long[] values) {
		return min(values, 0, values.length);
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final long min(long[] values, int offset, int length) {
		long min = Long.MAX_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final long max(long[] values) {
		return max(values, 0, values.length);
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final long max(long[] values, int offset, int length) {
		long max = Long.MIN_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}
	// end long array methods


	// start float array methods
	/** Convert a set of float values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	public static final float[] asArray(float... values) {
		return values;
	}


	/** Search for a matching float in an array of floats
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(float[] ary, float value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching float in an array of floats
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(float[] ary, int off, int len, float value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i] == (value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final float[] concat(float[] a, float[] b) {
		float[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an float array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(float[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an float array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(float[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			float temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** Sum of an array
	 * @param ary the array of integers to sum
	 * @return the sum of all of the elements in the specified array
	 */
	public static final float sum(float[] ary) {
		return sum(ary, 0, ary.length);
	}


	/** Sum of the subset of an array
	 * @param ary the array of floats to sum
	 * @param offset the offset into the array at which to start summing values
	 * @param length the number of elements to sum from the array starting
	 * at {@code offset}
	 * @return the sum of the specified array subset
	 */
	public static final float sum(float[] ary, int offset, int length) {
		int offLen = offset + length;
		float sum = 0f;
		for(int i = offset; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static final float avg(float[] values) {
		return avg(values, 0, values.length);
	}


	/** The average of a subset of an array
	 * @param values the array of values to average
	 * @param offset the offset into the array at which to start averaging values
	 * @param length the number of values to average from the array
	 * @return the average of the array subset specified
	 */
	public static final float avg(float[] values, int offset, int length) {
		float total = 0;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			total += values[i];
		}
		return total/(float)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final float min(float[] values) {
		return min(values, 0, values.length);
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final float min(float[] values, int offset, int length) {
		float min = Float.MAX_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final float max(float[] values) {
		return max(values, 0, values.length);
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final float max(float[] values, int offset, int length) {
		float max = Float.MIN_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}
	// end float array methods


	// start double array methods
	/** Convert a set of double values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	public static final double[] asArray(double... values) {
		return values;
	}


	/** Search for a matching double in an array of doubles
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(double[] ary, double value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching double in an array of doubles
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final int indexOf(double[] ary, int off, int len, double value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i] == (value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final double[] concat(double[] a, double[] b) {
		double[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an double array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(double[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an double array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(double[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			double temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** Sum of an array
	 * @param ary the array of integers to sum
	 * @return the sum of all of the elements in the specified array
	 */
	public static final double sum(double[] ary) {
		return sum(ary, 0, ary.length);
	}


	/** Sum of the subset of an array
	 * @param ary the array of doubles to sum
	 * @param offset the offset into the array at which to start summing values
	 * @param length the number of elements to sum from the array starting
	 * at {@code offset}
	 * @return the sum of the specified array subset
	 */
	public static final double sum(double[] ary, int offset, int length) {
		int offLen = offset + length;
		double sum = 0d;
		for(int i = offset; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static final double avg(double[] values) {
		return avg(values, 0, values.length);
	}


	/** The average of a subset of an array
	 * @param values the array of values to average
	 * @param offset the offset into the array at which to start averaging values
	 * @param length the number of values to average from the array
	 * @return the average of the array subset specified
	 */
	public static final double avg(double[] values, int offset, int length) {
		double total = 0;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			total += values[i];
		}
		return total/(double)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final double min(double[] values) {
		return min(values, 0, values.length);
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final double min(double[] values, int offset, int length) {
		double min = Double.MAX_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final double max(double[] values) {
		return max(values, 0, values.length);
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final double max(double[] values, int offset, int length) {
		double max = Double.MIN_VALUE;
		int offLen = offset + length;
		for(int i = offset; i < offLen; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}
	// end double array methods


	// start T array methods
	/** Convert a set of T values to an array
	 * @param values the set of values
	 * @return the array of values
	 */
	@SafeVarargs
	public static final <T> T[] asArray(T... values) {
		return values;
	}


	/** Search for a matching T in an array of Ts
	 * @param ary the array of values to search
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final <T> int indexOf(T[] ary, T value) {
		return indexOf(ary, 0, ary.length, value);
	}


	/** Search for a matching T in an array of Ts
	 * @param ary the array of values to search
	 * @param off the offset into {@code ary} at which to start searching for values
	 * @param len the number of values to search for starting at {@code off} in {@code ary}
	 * @param value the value to search for
	 * @return the index of the matching value, or -1 if the {@code value} could not be found
	 */
	public static final <T> int indexOf(T[] ary, int off, int len, T value) {
		for(int i = off, size = off + len; i < size; i++) {
			if(ary[i].equals(value)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final <T> T[] concat(T[] a, T[] b) {
		T[] r = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Reverse an T array.<br>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final <T> void reverse(T[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an T array.<br>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final <T> void reverse(T[] ary, int offset, int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			T temp = ary[i];
			int index = length - i - 1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}
	// end T array methods



	// AND operations on primitive arrays
	/**
	 * @return a newly created array containing the ANDed results of {@code a} and {@code b}
	 * @see ArrayUtil#and(byte[], int, byte[], int, byte[], int, int) and()
	 */
	public static final byte[] and(byte[] a, byte[] b) {
		byte[] dst = new byte[a.length];
		and(a, 0, b, 0, dst, 0, dst.length);
		return dst;
	}


	/**
	 * @see ArrayUtil#and(byte[], int, byte[], int, byte[], int, int) and()
	 */
	public static final void and(byte[] a, byte[] b, byte[] dst, int length) {
		and(a, 0, b, 0, dst, 0, length);
	}


	/** AND {@code length} number of bytes from {@code a} with {@code b} using the syntax ({@code a[i] & b[i]})
	 * and store the result in {@code dst}
	 * @param a the first array
	 * @param offsetA the offset into the {@code a} array at which to start ANDing values
	 * @param b the second array
	 * @param offsetB the offset into the {@code b} array at which to start ANDing values
	 * @param dst the destination array to store the ANDed results in
	 * @param dstOffset the offset into the {@code dst} array at which to start storing the ANDed values
	 * @param length the number of values to read from {@code a} and {@code b} and store in {@code dst}
	 */
	public static final void and(byte[] a, int offsetA, byte[] b, int offsetB, byte[] dst, int dstOffset, int length) {
		for(int i = 0, aI = offsetA, bI = offsetB, dstI = dstOffset; i < length; i++, aI++, bI++, dstI++) {
			dst[dstI] = (byte)(a[aI] & b[bI]);
		}
	}
	// end AND


	// OR operations on primitive arrays
	/**
	 * @return a newly created array containing the ORed results of {@code a} and {@code b}
	 * @see ArrayUtil#or(byte[], int, byte[], int, byte[], int, int) or()
	 */
	public static final byte[] or(byte[] a, byte[] b) {
		byte[] dst = new byte[a.length];
		or(a, 0, b, 0, dst, 0, dst.length);
		return dst;
	}


	/**
	 * @see ArrayUtil#or(byte[], int, byte[], int, byte[], int, int) or()
	 */
	public static final void or(byte[] a, byte[] b, byte[] dst, int length) {
		or(a, 0, b, 0, dst, 0, length);
	}


	/** OR {@code length} number of bytes from {@code a} with {@code b} using the syntax ({@code a[i] | b[i]})
	 * and store the result in {@code dst}
	 * @param a the first array
	 * @param offsetA the offset into the {@code a} array at which to start ORing values
	 * @param b the second array
	 * @param offsetB the offset into the {@code b} array at which to start ORing values
	 * @param dst the destination array to store the ORed results in
	 * @param dstOffset the offset into the {@code dst} array at which to start storing the ORed values
	 * @param length the number of values to read from {@code a} and {@code b} and store in {@code dst}
	 */
	public static final void or(byte[] a, int offsetA, byte[] b, int offsetB, byte[] dst, int dstOffset, int length) {
		for(int i = 0, aI = offsetA, bI = offsetB, dstI = dstOffset; i < length; i++, aI++, bI++, dstI++) {
			dst[dstI] = (byte)(a[aI] | b[bI]);
		}
	}
	// end OR


	// XOR operations on primitive arrays
	/**
	 * @return a newly created array containing the XORed results of {@code a} and {@code b}
	 * @see ArrayUtil#xor(byte[], int, byte[], int, byte[], int, int) xor()
	 */
	public static final byte[] xor(byte[] a, byte[] b) {
		byte[] dst = new byte[a.length];
		xor(a, 0, b, 0, dst, 0, dst.length);
		return dst;
	}


	/**
	 * @see ArrayUtil#xor(byte[], int, byte[], int, byte[], int, int) xor()
	 */
	public static final void xor(byte[] a, byte[] b, byte[] dst, int length) {
		xor(a, 0, b, 0, dst, 0, length);
	}


	/** XOR {@code length} number of bytes from {@code a} with {@code b} using the syntax ({@code a[i] ^ b[i]})
	 * and store the result in {@code dst}
	 * @param a the first array
	 * @param offsetA the offset into the {@code a} array at which to start XORing values
	 * @param b the second array
	 * @param offsetB the offset into the {@code b} array at which to start XORing values
	 * @param dst the destination array to store the XORed results in
	 * @param dstOffset the offset into the {@code dst} array at which to start storing the XORed values
	 * @param length the number of values to read from {@code a} and {@code b} and store in {@code dst}
	 */
	public static final void xor(byte[] a, int offsetA, byte[] b, int offsetB, byte[] dst, int dstOffset, int length) {
		for(int i = 0, aI = offsetA, bI = offsetB, dstI = dstOffset; i < length; i++, aI++, bI++, dstI++) {
			dst[dstI] = (byte)(a[aI] ^ b[bI]);
		}
	}
	// end XOR

}
