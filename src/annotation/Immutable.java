package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Indicates that a class implements an immutable design pattern.
 * That is, neither the object's properties nor state may be modified after it is created.
 * @author TeamworkGuy2
 * @since 2014-2-20
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Immutable {
}
