package dataUtility;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/** A utility for working with {@link java.util.Map.Entry Map.Entry}
 * @author TeamworkGuy2
 * @since 2014-4-5
 */
public final class Entries {

	private Entries() { throw new AssertionError("Cannot instantiate Entries"); }


	/** Create an immutable {@link java.util.Map.Entry Map.Entry}
	 * @param k the entry's key
	 * @param v the entry's value
	 * @return the new entry containing {@code k} and {@code v}
	 * @see #newImmutable(Object, Object)
	 */
	public static final <K, V> Map.Entry<K, V> of(K k, V v) {
		Map.Entry<K, V> entry = new AbstractMap.SimpleImmutableEntry<K, V>(k, v);
		return entry;
	}


	/** Create an immutable {@link java.util.Map.Entry Map.Entry}
	 * @param k the entry's key
	 * @param v the entry's value
	 * @return the new entry containing {@code k} and {@code v}
	 * @see #of(Object, Object)
	 */
	public static final <K, V> Map.Entry<K, V> newImmutable(K k, V v) {
		Map.Entry<K, V> entry = new AbstractMap.SimpleImmutableEntry<K, V>(k, v);
		return entry;
	}


	/** Create a mutable {@link java.util.Map.Entry Map.Entry}
	 * @param k the entry's key
	 * @param v the entry's value
	 * @return the new entry containing {@code k} and {@code v}
	 */
	public static final <K, V> Map.Entry<K, V> newMutable(K k, V v) {
		Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<K, V>(k, v);
		return entry;
	}


	/** Ensure that {@code entry} is immutable.  If it is not, then create
	 * a new immutable {@link java.util.Map.Entry Map.Entry} containing the same key and value
	 * @param entry the entry to ensure the immutability of
	 * @return {@code entry} or a new immutable {@link java.util.Map.Entry Map.Entry}
	 * containing {@code entry's} key and value
	 */
	public static final <K, V> Map.Entry<K, V> toImmutable(Map.Entry<K, V> entry) {
		if(entry instanceof AbstractMap.SimpleImmutableEntry) {
			return entry;
		}
		return new AbstractMap.SimpleImmutableEntry<K, V>(entry);
	}


	/** Ensure that {@code entry} is mutable.  If it is not, then create
	 * a new mutable {@link java.util.Map.Entry Map.Entry} containing the same key and value
	 * @param entry the entry to ensure the mutability of
	 * @return {@code entry} or a new mutable {@link java.util.Map.Entry Map.Entry}
	 * containing {@code entry's} key and value
	 */
	public static final <K, V> Map.Entry<K, V> toMutable(Map.Entry<K, V> entry) {
		if(entry instanceof AbstractMap.SimpleEntry) {
			return entry;
		}
		return new AbstractMap.SimpleEntry<K, V>(entry);
	}


	/** Carry out a binary search on the keys in an array of {@link java.util.Map.Entry Map.Entries}
	 * @param a the map entry array to search
	 * @param key the key to search for
	 * @return the index of the matching key or {@code -(expected index) - 1} if the key
	 * was not found in the array
	 * @see Arrays#binarySearch(Object[], Object, Comparator)
	 */
	public static final <K extends Comparable<K>, V> int binarySearch(Map.Entry<K, V>[] a, K key) {
		int low = 0;
		int high = a.length-1;

		while(low <= high) {
			int mid = (low + high) >>> 1;
			Map.Entry<K, V> midVal = a[mid];
			int compare = midVal.getKey().compareTo(key);

			if(compare < 0) {
				low = mid + 1;
			}
			else if(compare > 0) {
				high = mid - 1;
			}
			else {
				return mid;
			}
		}
		return -(low + 1);
	}


	/** Carry out a binary search on the keys in an array of {@link java.util.Map.Entry Map.Entries}
	 * @param a the map entry array to search
	 * @param key the key to search for
	 * @param c the comparator to compare keys
	 * @return the index of the matching key or {@code -(expected index) - 1} if the key
	 * was not found in the array
	 * @see Arrays#binarySearch(Object[], Object, Comparator)
	 */
	public static final <K, V> int binarySearch(Map.Entry<K, V>[] a, K key, Comparator<K> c) {
		int low = 0;
		int high = a.length-1;

		while(low <= high) {
			int mid = (low + high) >>> 1;
			Map.Entry<K, V> midVal = a[mid];
			int compare = c.compare(midVal.getKey(), key);

			if(compare < 0) {
				low = mid + 1;
			}
			else if(compare > 0) {
				high = mid - 1;
			}
			else {
				return mid;
			}
		}
		return -(low + 1);
	}


	/** Carry out a binary search on the values in an array of {@link java.util.Map.Entry Map.Entries}
	 * @param a the map entry array to search
	 * @param value the value to search for
	 * @return the index of the matching values or {@code -(expected index) - 1} if the value
	 * was not found in the array
	 * @see Arrays#binarySearch(Object[], Object, Comparator)
	 */
	public static final <K, V extends Comparable<V>> int binarySearchValue(Map.Entry<K, V>[] a, V value) {
		int low = 0;
		int high = a.length-1;

		while(low <= high) {
			int mid = (low + high) >>> 1;
			Map.Entry<K, V> midVal = a[mid];
			int compare = midVal.getValue().compareTo(value);

			if(compare < 0) {
				low = mid + 1;
			}
			else if(compare > 0) {
				high = mid - 1;
			}
			else {
				return mid;
			}
		}
		return -(low + 1);
	}


	/** Carry out a binary search on the values in an array of {@link java.util.Map.Entry Map.Entries}
	 * @param a the map entry array to search
	 * @param value the value to search for
	 * @param c the comparator to compare values
	 * @return the index of the matching values or {@code -(expected index) - 1} if the value
	 * was not found in the array
	 * @see Arrays#binarySearch(Object[], Object, Comparator)
	 */
	public static final <K, V> int binarySearchValue(Map.Entry<K, V>[] a, V value, Comparator<V> c) {
		int low = 0;
		int high = a.length-1;

		while(low <= high) {
			int mid = (low + high) >>> 1;
			Map.Entry<K, V> midVal = a[mid];
			int compare = c.compare(midVal.getValue(), value);

			if(compare < 0) {
				low = mid + 1;
			}
			else if(compare > 0) {
				high = mid - 1;
			}
			else {
				return mid;
			}
		}
		return -(low + 1);
	}

}
