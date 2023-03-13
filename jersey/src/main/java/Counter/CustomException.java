package Counter;

public enum CustomException {
	CUSTOM_EXCEPTION_PARSING_JSON(529);

	private final int value;

	CustomException(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
