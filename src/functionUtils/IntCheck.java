package functionUtils;

import java.util.function.Function;

/** A condition {@link Function} for checking an int value
 * @author TeamworkGuy2
 * @since 2014-8-30
 */
@FunctionalInterface
public interface IntCheck {

	/** Check if the int meets some condition
	 * @param i the integer to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean check(int i);

}
