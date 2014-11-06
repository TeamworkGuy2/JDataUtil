package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/** Indicates that a field, parameter, or variable is not/may not be null
 * @author TeamworkGuy2
 * @since 2014-11-6
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNull {
}
