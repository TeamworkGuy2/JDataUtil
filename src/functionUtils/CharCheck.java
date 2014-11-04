package functionUtils;

import java.util.function.Function;

/** A condition {@link Function} for checking a char value
 * @author TeamworkGuy2
 * @since 2014-8-30
 */
@FunctionalInterface
public interface CharCheck {

	/** Check if the char meets some condition
	 * @param c the char to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean check(char c);

}
