package dataUtility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** A utility class for building {@link Map Maps}
 * @author TeamworkGuy2
 * @since 2014-10-31
 */
public final class MapBuilder {

	private MapBuilder() { throw new AssertionError("cannot instantiate MapBuilder"); }


	/** Creates an immutable map containing the list of entries
	 * @param entries the list entries to include in the map
	 * @return a new, immutable, map containing the list of input entries
	 * @see #newImmutable(java.util.Map.Entry...)
	 */
	@SafeVarargs
	public static final <K, V> Map<K, V> of(Map.Entry<K, V>... entries) {
		return newImmutable(entries);
	}


	/** Creates an immutable map containing the list of entries
	 * @param entries the list entries to include in the map
	 * @return a new, immutable, map containing the list of input entries
	 */
	@SafeVarargs
	public static final <K, V> Map<K, V> newImmutable(Map.Entry<K, V>... entries) {
		return Collections.unmodifiableMap(newMutable(entries));
	}


	/** Creates a mutable map containing the list of entries
	 * @param entries the list entries to include in the map
	 * @return a new, mutable, map containing the list of input entries
	 */
	@SafeVarargs
	public static final <K, V> Map<K, V> newMutable(Map.Entry<K, V>... entries) {
		Map<K, V> entryMap = new HashMap<>();
		for(Map.Entry<K, V> entry : entries) {
			entryMap.put(entry.getKey(), entry.getValue());
		}
		return entryMap;
	}


	/** Creates an immutable mapping of enum constant names to enum constants
	 * @param enumClass an {@link Enum} class
	 * @return a map of the enum constant names to enums constants from the specified enum
	 * @see #newImmutableEnumNames(Class)
	 */
	public static final <E extends Enum<E>> Map<String, E> ofEnumNames(Class<E> enumClass) {
		return newImmutableEnumNames(enumClass);
	}


	/** Creates an immutable mapping of enum constant names to enum constants
	 * @param enumClass an {@link Enum} class
	 * @return a map of the enum constant names to enums constants from the specified enum
	 */
	public static final <E extends Enum<E>> Map<String, E> newImmutableEnumNames(Class<E> enumClass) {
		return Collections.unmodifiableMap(newMutableEnumNames(enumClass));
	}


	/** Creates a mutable mapping of enum constant names to enum constants
	 * @param enumClass an {@link Enum} class
	 * @return a map of the enum constant names to enums constants from the specified enum
	 */
	public static final <E extends Enum<E>> Map<String, E> newMutableEnumNames(Class<E> enumClass) {
		E[] enums = enumClass.getEnumConstants();
		Map<String, E> entryMap = new HashMap<>();
		for(E enumI : enums) {
			entryMap.put(enumI.name(), enumI);
		}
		return entryMap;
	}


	/** Combine a list of maps into one map according to the {@link Map#putAll(Map)} contract.<br>
	 * Duplicate keys are overwritten. Keys from the last map of {@code maps} taking
	 * precedence over keys from the second to last map and keys from the second to last
	 * map taking precedence over keys from the third to last map, etc.
	 * @param maps the list of maps to combine
	 * @return a single map containing all of the non-duplicate key-value pairs from all of the {@code maps}
	 */
	@SafeVarargs
	public static final <K, V> Map<K, V> concat(Map<? extends K, ? extends V>... maps) {
		Map<K, V> combinedMap = new HashMap<K, V>();
		for(Map<? extends K, ? extends V> map : maps) {
			combinedMap.putAll(map);
		}
		return combinedMap;
	}

}
