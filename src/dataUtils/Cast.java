package dataUtils;

import java.io.StringReader;

/**
 * @author TeamworkGuy2
 * @since 2015-7-4
 */
public class Cast {

	/** Unchecked cast from type A to type B
	 */
	public static <A, B> B cast(A obj) {
		@SuppressWarnings("unchecked")
		B res = (B)((Object)obj);
		return res;
	}


	/** useful trick to cast a checked exception to an unchecked runtime exception.<br>
	 * For example, useful when writing to {@link Appendable} or reading from {@link java.io.Reader}
	 * when the source is an implementation that won't throw an error,
	 * such as {@link StringBuilder} or {@link StringReader}.
	 */
	public static RuntimeException toRuntimeException(Throwable err) {
		return cast(err);
	}

}
