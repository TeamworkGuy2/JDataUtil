package dataUtility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** A utility class for {@link Map Maps}
 * @author TeamworkGuy2
 * @since 2014-10-31
 */
public final class MapBuilder {

	@SafeVarargs
	public static final <K, V> Map<K, V> of(Map.Entry<K, V>... entries) {
		return newImmutable(entries);
	}


	@SafeVarargs
	public static final <K, V> Map<K, V> newImmutable(Map.Entry<K, V>... entries) {
		Map<K, V> entryMap = new HashMap<>();
		for(Map.Entry<K, V> entry : entries) {
			entryMap.put(entry.getKey(), entry.getValue());
		}
		return Collections.unmodifiableMap(entryMap);
	}


	@SafeVarargs
	public static final <K, V> Map<K, V> newMutable(Map.Entry<K, V>... entries) {
		Map<K, V> entryMap = new HashMap<>();
		for(Map.Entry<K, V> entry : entries) {
			entryMap.put(entry.getKey(), entry.getValue());
		}
		return entryMap;
	}

}
