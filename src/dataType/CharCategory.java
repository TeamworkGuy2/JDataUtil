package dataType;

import ranges.CharSearcher;
import ranges.CharSearcherMutable;
import ranges.IntRangeSearcherMutableImpl;

/** A set of enums to represent various common character categories
 * @author TeamworkGuy2
 * @since 2014-10-9
 */
public enum CharCategory {
	DIGIT(new IntRangeSearcherMutableImpl('0', '9').toImmutableCharRangeSearcher()),
	ALPHA_UPPER(new IntRangeSearcherMutableImpl('A', 'Z').toImmutableCharRangeSearcher()),
	ALPHA_LOWER(new IntRangeSearcherMutableImpl('a', 'z').toImmutableCharRangeSearcher()),
	ALPHA_UPPER_OR_LOWER(new IntRangeSearcherMutableImpl('A', 'Z', 'a', 'z').toImmutableCharRangeSearcher()),
	WHITESPACE(new CharSearcherMutable((char)32/*space (' ')*/, (char)9/*horizontal tab*/, (char)12/*form feed*/,
			(char)10/*line feed*/, (char)13/*carriage return*/ /*TODO handle CR LF combinations*/).toImmutable()),
	LINE_TERMINATOR(new CharSearcherMutable((char)10/*line feed*/,
			(char)13/*carriage return*/ /*TODO handle CR LF combinations*/).toImmutable());

	private CharSearcher charSearcher;


	private CharCategory(CharSearcher charSearcher) {
		this.charSearcher = charSearcher;
	}


	public CharSearcher getCharCondition() {
		return charSearcher;
	}

}
