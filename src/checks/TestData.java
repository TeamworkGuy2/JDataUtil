package checks;

/** 
 * @param <I> the input data type
 * @param <E> the expected data type
 * @param <R> the comparison result data type
 * @author TeamworkGuy2
 * @since 2014-12-19
 */
public class TestData<I, E, R> {
	private I input;
	private E expected;
	private R result;


	/**
	 * @param input
	 * @param output
	 * @param result
	 */
	public TestData(I input, E expected, R result) {
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


	public R getResult() {
		return result;
	}


	public void setResult(R result) {
		this.result = result;
	}


	/**
	 * @param input
	 * @param output
	 */
	public static final <I, E, R> TestData<I, E, R> of(I input, E expected, R result) {
		TestData<I, E, R> inst = new TestData<>(input, expected, result);
		return inst;
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
