package functionUtils;

import java.util.function.Function;

/** A condition {@link Function} for checking a float value
 * @author TeamworkGuy2
 * @since 2014-8-30
 */
@FunctionalInterface
public interface FloatCheck {

	/** Check if the float meets some condition
	 * @param f the float to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean check(float f);

}
