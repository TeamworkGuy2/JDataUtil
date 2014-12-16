package ranges;

/** A char range set represents multiple sets of contiguous characters.
 * The character ranges support 32-bit characters, and each range
 * is represented by an inclusive lower and inclusive upper bound character.<br>
 * For example a CharRange with {@code size()==1} that returns
 * {@code getLowerBound(0)=='A'} and {@code getUpperBound(0)=='Z'}
 * represents all characters between {@code 'A'} and {@code 'Z'}, including 'A' and 'Z'.<br>
 * A CharRange may contain multiple character ranges, for example, 'A' through 'Z'
 * and '0' through '9'.
 * @author TeamworkGuy2
 * @since 2014-10-29
 * @see CharSearcher
 * @see IntRange
 */
public interface CharRange extends IntRange {

}
