package functionUtils;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;


/** Represents a function that accepts three arguments and produces a result.
 * This is a three-arity specialization of {@link Function}.
 * <p>This is a <a href="java.lang.function/package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object, Object)}.
 * @param <T> the type of the first function to the argument
 * @param <U> the type of the second function to the argument
 * @param <V> the type of the third function to the argument
 * @param <R> the type of the result of the function
 * @see Function
 * @author TeamworkGuy2
 * @since 2014-11-1
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {

	/** Applies this function to the given arguments.
	 * @param t the first function argument
	 * @param u the second function argument
	 * @param v the third function argument
	 * @return the function result
	 */
	public R apply(T t, U u, V v);


	/**
	 * @param <R2> type type of the output of the {@code after} function, and of the composite function
	 * @param after the function to apply after this function is applied
	 * @return a composed function that first applies this function and then applies
	 * the {@code after} function
	 * @see BiFunction#andThen(Function)
	 */
	default <R2> TriFunction<T, U, V, R2> andThen(Function<? super R, ? extends R2> after) {
		Objects.requireNonNull(after);
		return (T t, U u, V v) -> after.apply(apply(t, u, v));
	}

}
