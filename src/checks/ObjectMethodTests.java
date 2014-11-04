package checks;

/** For testing whether a class' {@link Object#hashCode()} and {@link Object#equals(Object)}
 * methods are correctly implemented (i.e. whether the methods are reflexive, symmetric, and transitive)
 * given a group of instances of the class.
 * @author TeamworkGuy2
 * @since 2014-3-29
 */
public class ObjectMethodTests {
	/**
	 * @author TeamworkGuy2
	 * @since 2014-3-29
	 */
	enum EqualityTuple {
		NON_EQUAL,
		A_B_EQUAL,
		A_C_EQUAL,
		B_C_EQUAL,
		A_B_C_EQUAL,
	};


	private static void testPListTypes() {
		testString();
	}


	private static void testString() {
		String a1 = new String("stringplist, etc-" + 1);
		String a2 = new String("stringplist" + ", etc-" + 1);
		String a3 = new String("stringplist" + ", " + "etc-" + 1);
		String b1 = new String("plist" + "-things: " + 2);
		String b2 = new String("plist" + "-" + "things: " + 2);
		String c1 = new String(1 + "plist-string");

		testObjects(a1, a2, a3, b1, b2, c1);
	}


	/** Test the specified objects' object methods.
	 * For example {@link Object#equals(Object)}, and {@link Object#hashCode()}.
	 * Test whether the specified equal objects return the correct hashCode() values
	 * and equals(Object) values.
	 * @param a1 this object should be equal to {@code a2} and {@code a3}, but not the same object
	 * @param a2
	 * @param a3
	 * @param b1 this object should be equal to {@code b2}, but not the same object
	 * @param b2
	 * @param c1 this object should not have an equal objects among the other parameters
	 */
	private static void testObjects(Object a1, Object a2, Object a3, Object b1, Object b2, Object c1) {
		testObjectMethods(EqualityTuple.A_B_EQUAL, b1, b2, a1);
		testObjectMethods(EqualityTuple.A_C_EQUAL, a3, b2, a2);
		testObjectMethods(EqualityTuple.B_C_EQUAL, a1, b2, b1);
		testObjectMethods(EqualityTuple.A_B_C_EQUAL, a1, a2, a3);
		testObjectMethods(EqualityTuple.NON_EQUAL, a3, b2, c1);
	}


	private static void testObjectMethods(EqualityTuple equal, Object a, Object b, Object c) {
		a.toString();
		b.toString();
		c.toString();
		int aH = a.hashCode();
		int bH = b.hashCode();
		int cH = c.hashCode();

		switch(equal) {
		case A_B_C_EQUAL:
			// hashCode() contract
			assert aH == bH && bH == cH && aH == cH;
			// Symmetric
			assert b.equals(a) && c.equals(a) && c.equals(b);
			// Transitive
			assert a.equals(b) && b.equals(c) && a.equals(c);
			break;
		case A_B_EQUAL:
			// hashCode() contract
			assert aH == bH;
			// Symmetric
			assert b.equals(a) && !c.equals(a) && !c.equals(b);
			// Transitive
			assert a.equals(b) && !b.equals(c) && !a.equals(c);
			break;
		case A_C_EQUAL:
			// hashCode() contract
			assert aH == cH;
			// Symmetric
			assert !b.equals(a) && c.equals(a) && !c.equals(b);
			// Transitive
			assert !a.equals(b) && !b.equals(c) && a.equals(c);
			break;
		case B_C_EQUAL:
			// hashCode() contract
			assert bH == cH;
			// Symmetric
			assert !b.equals(a) && !c.equals(a) && c.equals(b);
			// Transitive
			assert !a.equals(b) && b.equals(c) && !a.equals(c);
			break;
		case NON_EQUAL:
			// hashCode() contract
			assert true;
			// Symmetric
			assert !b.equals(a) && !c.equals(a) && !c.equals(b);
			// Transitive
			assert !a.equals(b) && !b.equals(c) && !a.equals(c);
			break;
		default:
			throw new IllegalArgumentException("Unknown equality tuple type: " + equal);
		}

		// Reflexive
		assert a.equals(a) && b.equals(b) && c.equals(c);
		// "non-nullity" (Effective Java, 2nd Ed., Item 8)
		assert !a.equals(null) && !b.equals(null) && !c.equals(null);

	}


	public static void main(String[] args) throws Exception {
		testPListTypes();
		System.out.println("Tests completed successfully");
	}

}
