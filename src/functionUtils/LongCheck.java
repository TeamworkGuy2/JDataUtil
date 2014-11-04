package functionUtils;

import java.util.function.Function;

/** A condition {@link Function} for checking a long value
 * @author TeamworkGuy2
 * @since 2014-8-30
 */
@FunctionalInterface
public interface LongCheck {

	/** Check if the long meets some condition
	 * @param l the long to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean check(long l);

}
