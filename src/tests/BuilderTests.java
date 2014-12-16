package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import checks.Check;
import dataType.AddCondition;
import dataUtility.ListAdd;
import dataUtility.ListUtil;


/**
 * @author TeamworkGuy2
 * @since 2014-12-16
 */
public final class BuilderTests {


	public static final void testListAddUtil() {
		ArrayList<String> strs = new ArrayList<>();

		// add non-null items
		ListAdd.addArrayToList(new String[] { "a", "ab", "abc", null }, strs, AddCondition.NO_NULL);
		if(strs.size() != 3) {
			throw new Error("invalid number of strings");
		}

		// add a subset of an array, also throw errors if null
		Check.handleException(true, () -> {
			ListAdd.addArrayToList(new String[] { "b", null, "c" }, 1, 1, strs, true, false, false, true);
		});

		// add items with a duplicate at the end and throw errors if contains, and catch error
		Check.handleException(true, () -> {
			ListAdd.addListToList(Arrays.asList("c", "d", "e", "a"), strs, AddCondition.ERROR_CONTAINS);
		});
		if(!Arrays.asList("a", "ab", "abc", "c", "d", "e").equals(strs)) {
			throw new Error("lists not equal");
		}

		// add items with duplicates, but don't allow duplicates and check for duplicates
		ListAdd.addCollectionToList(new HashSet<>(Arrays.asList("e", "f")), strs, AddCondition.NO_NULL_OR_CONTAINS);
		if(!ListUtil.isUnique(strs)) {
			throw new Error("list should be unique");
		}

		// add a duplicate item and check for duplicates
		ListAdd.addCollectionToList(new HashSet<>(Arrays.asList("f")), strs, AddCondition.ADD_ALL);
		if(ListUtil.isUnique(strs)) {
			throw new Error("list should have duplicate");
		}
	}

}
