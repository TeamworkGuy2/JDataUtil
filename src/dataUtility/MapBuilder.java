package dataUtility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** A utility class for {@link Map Maps}
 * @author TeamworkGuy2
 * @since 2014-10-31
 */
public final class MapBuilder {

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
		Map<K, V> entryMap = new HashMap<>();
		for(Map.Entry<K, V> entry : entries) {
			entryMap.put(entry.getKey(), entry.getValue());
		}
		return Collections.unmodifiableMap(entryMap);
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

}
