package checks;

/** 
 * @param <I> the input data type
 * @param <E> the expected data type
 * @author TeamworkGuy2
 * @since 2014-12-19
 */
public class TestDataBoolean<I, E> {
	private I input;
	private E expected;
	private boolean result;


	/**
	 * @param input
	 * @param output
	 * @param result
	 */
	public TestDataBoolean(I input, E expected, boolean result) {
		this.input = input;
		this.expected = expected;
		this.result = result;
	}


	public I getInput() {
		return input;
	}


	public void setInput(I input) {
		this.input = input;
	}


	public E getExpected() {
		return expected;
	}


	public void setExpected(E expected) {
		this.expected = expected;
	}


	public boolean getResult() {
		return result;
	}


	public void setResult(boolean result) {
		this.result = result;
	}


	/**
	 * @param input
	 * @param output
	 */
	public static final <I, E> TestDataBoolean<I, E> of(I input, E expected, boolean result) {
		TestDataBoolean<I, E> inst = new TestDataBoolean<>(input, expected, result);
		return inst;
	}

}
