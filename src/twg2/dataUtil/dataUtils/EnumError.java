package twg2.dataUtil.dataUtils;

/**
 * @author TeamworkGuy2
 * @since 2015-9-0
 */
public final class EnumError {

	private EnumError() { throw new AssertionError("cannot instantiate static class EnumUtil"); }


	public static final <E extends Enum<E>> IllegalArgumentException unknownValue(E value) {
		return unknownValue(value, null);
	}

	public static final <E extends Enum<E>> IllegalArgumentException unknownValue(E value, Class<E> cls) {
		return new IllegalArgumentException("unknown enum value '" + value + "' of type " + (value != null ? value.getClass() : cls));
	}


	public static final <E extends Enum<E>> IllegalArgumentException unsupportedValue(E value) {
		return unsupportedValue(value, null);
	}

	public static final <E extends Enum<E>> IllegalArgumentException unsupportedValue(E value, Class<E> cls) {
		return new IllegalArgumentException("unsupported enum value '" + value + "' of type " + (value != null ? value.getClass() : cls));
	}


	public static final <E extends Enum<E>> AssertionError unexpectedValue(E value) {
		return unexpectedValue(value, null);
	}

	public static final <E extends Enum<E>> AssertionError unexpectedValue(E value, Class<E> cls) {
		return new AssertionError("unexpected enum value '" + value + "' of type " + (value != null ? value.getClass() : cls));
	}


	public static final <_SRC_ENUM extends Enum<_SRC_ENUM>, _DST_ENUM extends Enum<_DST_ENUM>> IllegalArgumentException noEquivalent(_SRC_ENUM srcValueWithoutEquivalent, Class<_DST_ENUM> dstType) {
		return noEquivalent(srcValueWithoutEquivalent, null, dstType);
	}

	public static final <_SRC_ENUM extends Enum<_SRC_ENUM>, _DST_ENUM extends Enum<_DST_ENUM>> IllegalArgumentException noEquivalent(_SRC_ENUM srcValueWithoutEquivalent,
			Class<_SRC_ENUM> srcType, Class<_DST_ENUM> dstType) {
		return new IllegalArgumentException("cannot convert " + (srcValueWithoutEquivalent != null ? srcValueWithoutEquivalent.getClass() : srcType) +
				" enum '" + srcValueWithoutEquivalent + "', has no " + dstType.getSimpleName() + " equivalent");
	}

}
