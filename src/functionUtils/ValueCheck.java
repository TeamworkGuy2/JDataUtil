package functionUtils;

import java.util.function.Function;

/** A condition {@link Function} for checking an object's value
 * @param <T> the type of value to check
 * @author TeamworkGuy2
 * @since 2014-8-30
 */
@FunctionalInterface
public interface ValueCheck<T> {

	/** Check if the objects meets some condition
	 * @param obj the object to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean check(T obj);

}
