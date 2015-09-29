package dataUtils;

public final class EnumUtil {

	private EnumUtil() { throw new AssertionError("cannot instantiate static class EnumUtil"); }


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

}
