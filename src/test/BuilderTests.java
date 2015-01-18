package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import checks.Check;
import dataType.AddCondition;
import dataUtils.ListAdd;
import dataUtils.ListUtil;


/**
 * @author TeamworkGuy2
 * @since 2014-12-16
 */
public final class BuilderTests {


	@Test
	public void testListAddUtil() {
		ArrayList<String> strs = new ArrayList<>();

		// add non-null items
		ListAdd.addArrayToList(new String[] { "a", "ab", "abc", null }, strs, AddCondition.NO_NULL);
		Assert.assertEquals("invalid number of strings", strs.size(), 3);

		// add a subset of an array, also throw errors if null
		Check.assertException(() -> {
			ListAdd.addArrayToList(new String[] { "b", null, "c" }, 1, 1, strs, true, false, false, true);
		});

		// add items with a duplicate at the end and throw errors if contains, and catch error
		Check.assertException(() -> {
			ListAdd.addListToList(Arrays.asList("c", "d", "e", "a"), strs, AddCondition.ERROR_CONTAINS);
		});
		Assert.assertTrue("lists not equal", Arrays.asList("a", "ab", "abc", "c", "d", "e").equals(strs));

		// add items with duplicates, but don't allow duplicates and check for duplicates
		ListAdd.addCollectionToList(new HashSet<>(Arrays.asList("e", "f")), strs, AddCondition.NO_NULL_OR_CONTAINS);
		Assert.assertTrue("list should be unique", ListUtil.isUnique(strs));

		// add a duplicate item and check for duplicates
		ListAdd.addCollectionToList(new HashSet<>(Arrays.asList("f")), strs, AddCondition.ADD_ALL);
		Assert.assertTrue("list should have duplicate", !ListUtil.isUnique(strs));
	}

}
