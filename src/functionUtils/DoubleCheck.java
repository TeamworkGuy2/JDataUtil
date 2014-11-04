package functionUtils;

import java.util.function.Function;

/** A condition {@link Function} for checking a double value
 * @author TeamworkGuy2
 * @since 2014-8-30
 */
@FunctionalInterface
public interface DoubleCheck {

	/** Check if the double meets some condition
	 * @param d the double to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean check(double d);

}
