package arrayUtils;

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

	@SuppressWarnings("unused")
	private Object ary;
	@SuppressWarnings("unused")
	private int offset;
	@SuppressWarnings("unused")
	private int length;


	public ArrayUtil(byte[] ary) {
		this(ary, 0, ary.length);
	}


	public ArrayUtil(byte[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public ArrayUtil(boolean[] ary) {
		this(ary, 0, ary.length);
	}


	public ArrayUtil(boolean[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public ArrayUtil(short[] ary) {
		this(ary, 0, ary.length);
	}


	public ArrayUtil(short[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public ArrayUtil(char[] ary) {
		this(ary, 0, ary.length);
	}


	public ArrayUtil(char[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public ArrayUtil(int[] ary) {
		this(ary, 0, ary.length);
	}


	public ArrayUtil(int[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public ArrayUtil(float[] ary) {
		this(ary, 0, ary.length);
	}


	public ArrayUtil(float[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public ArrayUtil(long[] ary) {
		this(ary, 0, ary.length);
	}


	public ArrayUtil(long[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public ArrayUtil(double[] ary) {
		this(ary, 0, ary.length);
	}


	public ArrayUtil(double[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public <T> ArrayUtil(T[] ary) {
		this(ary, 0, ary.length);
	}


	public <T> ArrayUtil(T[] ary, int offset, int length) {
		this.ary = ary;
		this.offset = offset;
		this.length = length;
	}


	public static final int indexOf(byte[] a, byte b) {
		for(int i = 0, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(byte[] a, int offA, byte b) {
		for(int i = offA, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(char[] a, char b) {
		for(int i = 0, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(char[] a, int offA, char b) {
		for(int i = offA, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(short[] a, short b) {
		for(int i = 0, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(short[] a, int offA, short b) {
		for(int i = offA, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(int[] a, int b) {
		for(int i = 0, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(int[] a, int offA, int b) {
		for(int i = offA, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(long[] a, long b) {
		for(int i = 0, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(long[] a, int offA, long b) {
		for(int i = offA, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(float[] a, float b) {
		for(int i = 0, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(float[] a, int offA, float b) {
		for(int i = offA, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(double[] a, double b) {
		for(int i = 0, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(double[] a, int offA, double b) {
		for(int i = offA, size = a.length; i < size; i++) {
			if(a[i] == b) { return i; }
		}
		return -1;
	}


	public static final int indexOf(Object[] a, Object b) {
		for(int i = 0, size = a.length; i < size; i++) {
			if(a[i].equals(b)) { return i; }
		}
		return -1;
	}


	public static final int indexOf(Object[] a, int offA, Object b) {
		for(int i = offA, size = a.length; i < size; i++) {
			if(a[i].equals(b)) { return i; }
		}
		return -1;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final byte[] concat(byte[] a, byte[] b) {
		byte[] r = new byte[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final char[] concat(char[] a, char[] b) {
		char[] r = new char[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final int[] concat(int[] a, int[] b) {
		int[] r = new int[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final float[] concat(float[] a, float[] b) {
		float[] r = new float[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}



	/** Return an array containing a copy of array {@code a} and {@code b}
	 * @param a the first array
	 * @param b the second array
	 * @return a new array containing a shallow copy of both arrays
	 */
	public static final <T> T[] concat(T[] a, T[] b) {
		@SuppressWarnings("unchecked")
		T[] r = (T[])new Object[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}


	/** Sum of an array
	 * @param ary the array of integers to sum
	 * @return the sum of all of the elements in the specified array
	 */
	public static final int sum(int[] ary) {
		return sum(ary, 0, ary.length);
	}


	/** Sum of the subset of an array
	 * @param ary the array of integers to sum
	 * @param offset the offset into the array at which to start summing values
	 * @param length the number of elements to sum from the array starting
	 * at {@code offset}
	 * @return the sum of the specified array subset
	 */
	public static final int sum(int[] ary, final int offset, final int length) {
		int offLen = offset+length;
		int sum = 0;
		for(int i = 0; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** Reverse an int array.<br/>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(int[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of an int array.<br/>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(int[] ary, final int offset, final int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			int temp = ary[i];
			int index = length-i-1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static float avg(int[] values) {
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
		int offsetLength = offset+length;
		for(int i = offset; i < offsetLength; i++) {
			total += values[i];
		}
		return total/(float)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final int min(int[] values, int offset, int length) {
		int min = Integer.MAX_VALUE;
		int offsetLength = offset+length;
		for(int i = offset; i < offsetLength; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static final int max(int[] values, int offset, int length) {
		int max = Integer.MIN_VALUE;
		int offsetLength = offset+length;
		for(int i = offset; i < offsetLength; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}


	/** Sum of an array
	 * @param ary the array of floats to sum
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
	public static final float sum(float[] ary, final int offset, final int length) {
		int offLen = offset+length;
		float sum = 0;
		for(int i = 0; i < offLen; i++) {
			sum += ary[i];
		}
		return sum;
	}


	/** Reverse a float array.<br/>
	 * For example {@code ary=[0, 1, 2, 3]}, the result would be {@code ary=[3, 2, 1, 0]}.
	 * @param ary the array to reverse a portion of
	 */
	public static final void reverse(float[] ary) {
		reverse(ary, 0, ary.length);
	}


	/** Reverse a subset of a float array.<br/>
	 * For example {@code ary=[0, 1, 2, 3, 4, 5]} and {@code offset=2} and {@code length=3},
	 * the result would be {@code ary=[0, 1, 4, 3, 2, 5]}.
	 * @param ary the array to reverse a portion of
	 * @param offset the offset into the array at which to begin reversing the order of elements
	 * @param length the number of elements to reverse
	 */
	public static final void reverse(float[] ary, final int offset, final int length) {
		final int len = length/2;
		for(int i = 0; i < len; i++) {
			float temp = ary[i];
			int index = length-i-1;
			ary[i] = ary[index];
			ary[index] = temp;
		}
	}


	/** The average of an array
	 * @param values the array of values to average
	 * @return the average of the array
	 */
	public static float avg(float[] values) {
		return avg(values, 0, values.length);
	}


	/** The average of a subset of an array
	 * @param values the array of values to average
	 * @param offset the offset into the array at which to start averaging values
	 * @param length the number of values to average from the array
	 * @return the average of the array subset specified
	 */
	public static float avg(float[] values, int offset, int length) {
		float total = 0;
		int offsetLength = offset+length;
		for(int i = offset; i < offsetLength; i++) {
			total += values[i];
		}
		return total/(float)length;
	}


	/** Get the minimum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static float min(float[] values, int offset, int length) {
		float min = Float.MAX_VALUE;
		int offsetLength = offset+length;
		for(int i = offset; i < offsetLength; i++) {
			if(min > values[i]) { min = values[i]; }
		}
		return min;
	}


	/** Get the maximum value in an array subset
	 * @param values the array to search
	 * @param offset the offset into the array at which to start searching
	 * @param length the number of values to search
	 * @return the minimum value found in the array subset specified
	 */
	public static float max(float[] values, int offset, int length) {
		float max = Float.MIN_VALUE;
		int offsetLength = offset+length;
		for(int i = offset; i < offsetLength; i++) {
			if(max < values[i]) { max = values[i]; }
		}
		return max;
	}


	// AND operations on primitive arrays
	/**
	 * @return a newly created array containing the ANDed results of {@code a} and {@code b}
	 * @see ArrayUtil#and(byte[], int, byte[], int, byte[], int, int) and()
	 */
	public static byte[] and(byte[] a, byte[] b) {
		byte[] dst = new byte[a.length];
		and(a, 0, b, 0, dst, 0, dst.length);
		return dst;
	}


	/**
	 * @see ArrayUtil#and(byte[], int, byte[], int, byte[], int, int) and()
	 */
	public static void and(byte[] a, byte[] b, byte[] dst, int length) {
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
	public static void and(byte[] a, int offsetA, byte[] b, int offsetB, byte[] dst, int dstOffset, int length) {
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
	public static byte[] or(byte[] a, byte[] b) {
		byte[] dst = new byte[a.length];
		or(a, 0, b, 0, dst, 0, dst.length);
		return dst;
	}


	/**
	 * @see ArrayUtil#or(byte[], int, byte[], int, byte[], int, int) or()
	 */
	public static void or(byte[] a, byte[] b, byte[] dst, int length) {
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
	public static void or(byte[] a, int offsetA, byte[] b, int offsetB, byte[] dst, int dstOffset, int length) {
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
	public static byte[] xor(byte[] a, byte[] b) {
		byte[] dst = new byte[a.length];
		xor(a, 0, b, 0, dst, 0, dst.length);
		return dst;
	}


	/**
	 * @see ArrayUtil#xor(byte[], int, byte[], int, byte[], int, int) xor()
	 */
	public static void xor(byte[] a, byte[] b, byte[] dst, int length) {
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
	public static void xor(byte[] a, int offsetA, byte[] b, int offsetB, byte[] dst, int dstOffset, int length) {
		for(int i = 0, aI = offsetA, bI = offsetB, dstI = dstOffset; i < length; i++, aI++, bI++, dstI++) {
			dst[dstI] = (byte)(a[aI] ^ b[bI]);
		}
	}
	// end XOR

}
