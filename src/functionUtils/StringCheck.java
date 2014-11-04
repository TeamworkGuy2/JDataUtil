package functionUtils;

import java.util.function.Function;

/** A condition {@link Function} for checking a string's value
 * @author TeamworkGuy2
 * @since 2014-8-30
 */
@FunctionalInterface
public interface StringCheck {

	/** Check if the string meets some condition
	 * @param str the string to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean check(String str);

}
