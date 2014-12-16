package dataUtility;

import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

import dataType.AddCondition;

/** A set of methods to add {@link Collection collections}, {@link List lists}, or
 * {@code arrays} to a list
 * @author TeamworkGuy2
 * @since 2014-11-8
 */
public final class ListAdd {

	private ListAdd() { throw new AssertionError("cannot instantiate static class ListAdd"); }


	/**
	 * @param ary
	 * @param dst
	 * @param condition
	 * @return true if the value was added successfully, false otherwise
	 * @see #addArrayToList(Object[], List, boolean, boolean, boolean, boolean)
	 */
	public static final <T> boolean addArrayToList(T[] ary, List<? super T> dst, AddCondition condition) {
		return addArrayToList(ary, dst, condition.doAddIfContains(), condition.doErrorIfContains(),
				condition.doAddIfNull(), condition.doErrorIfNull());
	}


	/**
	 * @param list
	 * @param dst
	 * @param condition
	 * @return true if the value was added successfully, false otherwise
	 * @see #addListToList(List, List, boolean, boolean, boolean, boolean)
	 */
	public static final <T> boolean addListToList(List<T> list, List<? super T> dst, AddCondition condition) {
		return addListToList(list, dst, condition.doAddIfContains(), condition.doErrorIfContains(),
				condition.doAddIfNull(), condition.doErrorIfNull());
	}


	/**
	 * @param collection
	 * @param dst
	 * @param condition
	 * @return true if the value was added successfully, false otherwise
	 * @see #addCollectionToList(Collection, List, boolean, boolean, boolean, boolean)
	 */
	public static final <T> boolean addCollectionToList(Collection<T> collection, List<? super T> dst,
			AddCondition condition) {
		return addCollectionToList(collection, dst, condition.doAddIfContains(), condition.doErrorIfContains(),
				condition.doAddIfNull(), condition.doErrorIfNull());
	}


	/**
	 * @param ary the array of values to add to the specified list
	 * @param dst list the list to add the items to
	 * @param addIfContains true to add all of {@code ary} items, even if they exist in the list,
	 * false to only add items that do not exist in the list as defined by {@link List#contains(Object)}
	 * @param addIfNull true to add any item, false to not add null items
	 * @return true if the value was added successfully, false otherwise
	 */
	public static final <T> boolean addArrayToList(T[] ary, List<? super T> dst, boolean addIfContains,
			boolean errorIfContains, boolean addIfNull, boolean errorIfNull) {
		return addArrayToList(ary, 0, ary.length, dst, addIfContains, errorIfContains, addIfNull, errorIfNull);
	}


	/**
	 * @param ary the array of values to add to the specified list
	 * @param off the offset into {@code ary} at which to start adding items to the {@code dst} list
	 * @param len the number of items to add to {@code dst} from {@code ary}
	 * @param dst list the list to add the items to
	 * @param addIfContains true to add all of {@code ary} items, even if they exist in the list,
	 * false to only add items that do not exist in the list as defined by {@link List#contains(Object)}
	 * @param addIfNull true to add any item, false to not add null items
	 * @return true if the value was added successfully, false otherwise
	 */
	public static final <T> boolean addArrayToList(T[] ary, int off, int len, List<? super T> dst,
			boolean addIfContains, boolean errorIfContains, boolean addIfNull, boolean errorIfNull) {
		if(ary == null) {
			return false;
		}
		boolean result = true;
		for(int i = off, size = off + len; i < size; i++) {
			T item = ary[i];
			if(!addIfNull && item == null) {
				if(errorIfNull) {
					throw new IllegalStateException("tried to add null item to list");
				}
				result = false;
				continue;
			}
			if(!addIfContains && dst.contains(item)) {
				if(errorIfContains) {
					throw new IllegalStateException("tried to add existing item to list");
				}
				result = false;
				continue;
			}
			result &= dst.add(item);
		}
		return result;
	}


	/**
	 * @param list the list of values to add to the specified list
	 * @param dst list the list to add the items to
	 * @param addIfContains true to add all of {@code list} items, even if they exist in the list,
	 * false to only add items that do not exist in the list as defined by {@link List#contains(Object)}
	 * @param addIfNull true to add any item, false to not add null items
	 * @return true if the value was added successfully, false otherwise
	 */
	public static final <T> boolean addListToList(List<? extends T> list, List<? super T> dst, boolean addIfContains,
			boolean errorIfContains, boolean addIfNull, boolean errorIfNull) {
		if(list == null) {
			return false;
		}
		boolean result = true;
		if(list instanceof RandomAccess) {
			for(int i = 0, size = list.size(); i < size; i++) {
				T item = list.get(i);
				if(!addIfNull && item == null) {
					if(errorIfNull) {
						throw new IllegalStateException("tried to add null item to list");
					}
					result = false;
					continue;
				}
				if(!addIfContains && dst.contains(item)) {
					if(errorIfContains) {
						throw new IllegalStateException("tried to add existing item to list");
					}
					result = false;
					continue;
				}
				result &= dst.add(item);
			}
		}
		else {
			for(T item : list) {
				if(!addIfNull && item == null) {
					if(errorIfNull) {
						throw new IllegalStateException("tried to add null item to list");
					}
					result = false;
					continue;
				}
				if(!addIfContains && dst.contains(item)) {
					if(errorIfContains) {
						throw new IllegalStateException("tried to add existing item to list");
					}
					result = false;
					continue;
				}
				result &= dst.add(item);
			}
		}
		return result;
	}


	/**
	 * @param collection the collection of items to add to the list
	 * @param dst list the list to add the items to
	 * @param addIfContains true to add all of {@code collection} items, even if they exist in the list,
	 * false to only add items that do not exist in the list as defined by {@link List#contains(Object)}
	 * @param addIfNull true to add any item, false to not add null items
	 * @return true if the value was added successfully, false otherwise
	 */
	public static final <T> boolean addCollectionToList(Collection<? extends T> collection, List<? super T> dst,
			boolean addIfContains, boolean errorIfContains, boolean addIfNull, boolean errorIfNull) {
		if(collection == null) {
			return false;
		}
		boolean result = true;
		if(collection instanceof List) {
			return addListToList((List<? extends T>)collection, dst, addIfContains, errorIfContains, addIfNull, errorIfNull);
		}
		else {
			for(T item : collection) {
				if(!addIfNull && item == null) {
					if(errorIfNull) {
						throw new IllegalStateException("tried to add null item to list");
					}
					result = false;
					continue;
				}
				if(!addIfContains && dst.contains(item)) {
					if(errorIfContains) {
						throw new IllegalStateException("tried to add existing item to list");
					}
					result = false;
					continue;
				}
				result &= dst.add(item);
			}
		}
		return result;
	}

}
