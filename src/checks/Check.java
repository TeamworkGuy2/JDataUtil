package checks;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Assert;

/** Static methods for checking type, bounds, and other requirements
 * @author TeamworkGuy2
 * @since 2014-6-3
 */
public final class Check {

	/** Throw an array index out of bounds exception if the {@code index} is less
	 * than {@code 0} or greater than or equal to {@code length} 
	 * @param index the index to check
	 * @param length the maximum value of the index (exclusive)
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range specified
	 */
	public static final void bounds(int index, int length) {
		if(index < 0 || index > length) {
			throw new ArrayIndexOutOfBoundsException(index + ", length=" + length);
		}
	}


	/** Throw an index out of bounds exception if the {@code index} is less
	 * than {@code min} or greater than or equal to {@code max} 
	 * @param index the index to check
	 * @param min the minimum value of the index (inclusive)
	 * @param max the maximum value of the index (exclusive)
	 * @throws IndexOutOfBoundsException if the index is outside the range specified
	 */
	public static final void bounds(int index, int min, int max) {
		if(index < min || index >= max) {
			throw new IndexOutOfBoundsException(index + ", min" + min + ", max=" + max);
		}
	}


	/** Clamp the specified value to the specified range. For example:<br/>
	 * {@code clamp(24, 0, 10) returns 10}<br/>
	 * {@code clamp(4, 1, 20) returns 4}<br/>
	 * {@code clamp(-31, 0, 100) returns 0}
	 * @param num the number to clamp
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return {@code num}, or {@code min} if {@code num} is less than {@code min},
	 * or {@code max} if {@code num} is greater than {@code max}
	 */
	public static final int clamp(int num, int min, int max) {
		return Math.max(Math.min(num, max), min);
	}


	/**
	 * @see #clamp(int, int, int)
	 */
	public static final float clamp(float num, float min, float max) {
		return Math.max(Math.min(num, max), min);
	}


	/**
	 * @see #clamp(int, int, int)
	 */
	public static final long clamp(long num, long min, long max) {
		return Math.max(Math.min(num, max), min);
	}


	/**
	 * @see #clamp(int, int, int)
	 */
	public static final double clamp(double num, double min, double max) {
		return Math.max(Math.min(num, max), min);
	}


	/** Throw a null pointer exception if the object is null
	 * @param obj the object to check
	 * @throws NullPointerException if the object is null
	 */
	public static final void nullArg(Object obj) {
		if(obj == null) {
			throw new NullPointerException();
		}
	}


	/** Check if the string is null, length zero, or contains only whitespace characters.<br/>
	 * Equivalent to: {@code str == null ? true : (str.trim().length() == 0 ? true : false);}
	 * @param str the string to check
	 * @return true if the string is null, length zero, or contains only whitespace characters, false otherwise
	 */
	public static final boolean isEmpty(String str) {
		if(str == null || str.length() == 0) { return true; }
		int len = str.length();
		int start = 0;
		
		while((start < len) && (str.charAt(start) <= ' ')) {
			start++;
		}
		while((start < len) && (str.charAt(len - 1) <= ' ' )) {
			len--;
		}
		if(start == len) { return true; }
		return false;
	}


	/** Return an empty string if the string is null, length zero, or contains only whitespace characters.<br/>
	 * Equivalent to: {@code str == null ? "" : (str.trim().length() == 0 ? "" : str);}
	 * @param str the string to check
	 * @return an empty string ({@code ""}) if the string is null, length zero, or contains only
	 * whitespace characters, returns {@code str} otherwise
	 */
	public static final String checkEmpty(String str) {
		if(str == null || str.length() == 0) { return ""; }
		int len = str.length();
		int start = 0;
		
		while((start < len) && (str.charAt(start) <= ' ')) {
			start++;
		}
		while((start < len) && (str.charAt(len - 1) <= ' ' )) {
			len--;
		}
		if(start == len) { return ""; }
		return str;
	}


	/** Check if two strings are equal
	 * @param str the first string to compare
	 * @param str2 the second string to compare
	 * @param msg a message to include in the error if the two strings are not equal
	 * @throws Error if {@code str} does not equal {@code str2}
	 */
	public static final void checkEqual(String str, String str2, String msg) {
		if(!str.equals(str2)) {
			throw new Error("input: '" + str + "' does not match expected: '" + str2 + "', " + msg);
		}
	}


	/**
	 * @param task the action to perform
	 * @param throwIfNoError true to throw an error if {@code task} does not
	 * throw an {@code Exception}
	 * @return true if the task did not throw an error, false if it did not throw an error
	 */
	public static final boolean handleException(boolean throwIfNoError, Runnable task) {
		Throwable error = null;
		try {
			task.run();
		} catch(Exception e) {
			error = e;
		} finally {
			if(throwIfNoError && error == null) {
				throw new Error("task was expected to throw error but did not");
			}
		}
		return error == null;
	}


	/**
	 * @param task the action to perform
	 * @param throwIfNoError true to throw an error if {@code task} does not
	 * throw a {@code Throwable}
	 * @return true if the task did not throw an error, false if it did not throw an error
	 */
	public static final void handleThrowable(boolean throwIfNoError, Runnable task) {
		Throwable error = null;
		try {
			task.run();
		} catch(Throwable e) {
			error = e;
		} finally {
			if(throwIfNoError && error == null) {
				throw new Error("task was expected to throw error but did not");
			}
		}
	}


	/** Run a series of test against a set of inputs and expected results.
	 * Each input is converted to a result via a function and the result is compared
	 * to the expected result using {@link #equals(Object)}
	 * @param inputs the array of inputs to process
	 * @param expected the expected results of processing each input
	 * @param perCheckMessage the message to print with each successfully result
	 * @param errorMessage the message to include in the error message if the result
	 * does not match the expected result 
	 * @param action the action that takes an input from {@code inputs} and converts
	 * it to a result value to compare to {@code expected}
	 */
	public static final <T, R> void checkTests(T[] inputs, R[] expected, String perCheckMessage, String errorMessage,
			Function<T, R> action) {
		expectLength(inputs.length, expected.length);

		for(int i = 0, size = inputs.length; i < size; i++) {
			R res = action.apply(inputs[i]);
			if(!res.equals(expected[i])) {
				throw new Error(errorMessage + ", input " + i + ": '" + res + "' does not match expected: '" +
						expected[i] + "'");
			}
			else if(perCheckMessage != null) {
				System.out.println(perCheckMessage + "input " + i + ": '" + res + "' to: '" + expected[i] + "'");
			}
		}
	}


	public static final <T, R> void checkTests(T[] inputs, R[] expected, String perCheckMessage, String errorMessage,
			BiFunction<T, Integer, R> action) {
		expectLength(inputs.length, expected.length);

		for(int i = 0, size = inputs.length; i < size; i++) {
			R res = action.apply(inputs[i], i);
			if(!res.equals(expected[i])) {
				throw new Error(errorMessage + ", input " + i + ": '" + res + "' does not match expected: '" +
						expected[i] + "'");
			}
			else if(perCheckMessage != null) {
				System.out.println(perCheckMessage + "input " + i + ": '" + res + "' to: '" + expected[i] + "'");
			}
		}
	}


	private static final void expectLength(int len1, int len2) {
		if(len1 != len2) {
			throw new IllegalArgumentException("inputs (length: " + len1 + ") should be equal in length to (length: " + len2 + ")");
		}

	}


	/**
	 * @param task the action to perform
	 * @param throwIfNoError true to throw an error if {@code task} does not
	 * throw an {@code Exception}
	 * @return true if the task did not throw an error, false if it did not throw an error
	 */
	public static final boolean assertException(boolean throwIfNoError, Runnable task) {
		Throwable error = null;
		try {
			task.run();
		} catch(Exception e) {
			error = e;
		} finally {
			if(throwIfNoError) {
				Assert.assertTrue("task was expected to throw error but did not", error == null);
			}
		}
		return error == null;
	}


	/**
	 * @param task the action to perform
	 * @param throwIfNoError true to throw an error if {@code task} does not
	 * throw a {@code Throwable}
	 * @return true if the task did not throw an error, false if it did not throw an error
	 */
	public static final void assertThrowable(boolean throwIfNoError, Runnable task) {
		Throwable error = null;
		try {
			task.run();
		} catch(Throwable e) {
			error = e;
		} finally {
			if(throwIfNoError) {
				Assert.assertTrue("task was expected to throw error but did not", error == null);
			}
		}
	}


	/** Run a series of test against a set of inputs and expected results.
	 * Each input is converted to a result via a function and the result is compared
	 * to the expected result using {@link #equals(Object)}
	 * @param inputs the array of inputs to process
	 * @param expected the expected results of processing each input
	 * @param perCheckMessage the message to print with each successfully result
	 * @param errorMessage the message to include in the error message if the result
	 * does not match the expected result 
	 * @param action the action that takes an input from {@code inputs} and converts
	 * it to a result value to compare to {@code expected}
	 */
	public static final <T, R> void assertTests(T[] inputs, R[] expected, String perCheckMessage, String errorMessage,
			Function<T, R> action) {
		assertLengthEqual(inputs.length, expected.length);

		for(int i = 0, size = inputs.length; i < size; i++) {
			R res = action.apply(inputs[i]);
			Assert.assertEquals(errorMessage + ", input " + i + ": '" + res + "' does not match expected: '" +
						expected[i] + "'", expected[i], res);
		}
	}


	public static final <T, R> void assertTests(T[] inputs, R[] expected, String perCheckMessage, String errorMessage,
			BiFunction<T, Integer, R> action) {
		assertLengthEqual(inputs.length, expected.length);

		for(int i = 0, size = inputs.length; i < size; i++) {
			R res = action.apply(inputs[i], i);
			Assert.assertEquals(errorMessage + ", input " + i + ": '" + res + "' does not match expected: '" +
					expected[i] + "'", expected[i], res);
		}
	}


	private static final void assertLengthEqual(int len1, int len2) {
		Assert.assertEquals("inputs (length: " + len1 + ") should be equal in length to (length: " + len2 + ")", len1, len2);
	}

}
