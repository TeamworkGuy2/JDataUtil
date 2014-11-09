package dataType;

/** An enum of the conditions for adding an item to a collection or array
 * @author TeamworkGuy2
 * @since 2014-9-14
 */
public enum AddCondition {
	ADD_ALL(true, false, true, false),
	NO_NULL(true, false, false, false),
	NO_CONTAINS(false, false, true, false),
	NO_NULL_OR_CONTAINS(false, false, false, false),
	ERROR_NULL(true, false, false, true),
	ERROR_CONTAINS(false, true, true, false),
	ERROR_NULL_OR_CONTAINS(false, true, false, true);

	private final boolean addIfContains;
	private final boolean errorIfContains;
	private final boolean addIfNull;
	private final boolean errorIfNull;


	/**
	 * @param addIfContains
	 * @param errorIfContains
	 * @param addIfNull
	 * @param errorIfNull
	 */
	private AddCondition(boolean addIfContains, boolean errorIfContains, boolean addIfNull, boolean errorIfNull) {
		this.addIfContains = addIfContains;
		this.errorIfContains = errorIfContains;
		this.addIfNull = addIfNull;
		this.errorIfNull = errorIfNull;
	}


	public boolean doAddIfContains() {
		return addIfContains;
	}


	public boolean doErrorIfContains() {
		return errorIfContains;
	}


	public boolean doAddIfNull() {
		return addIfNull;
	}


	public boolean doErrorIfNull() {
		return errorIfNull;
	}

}
