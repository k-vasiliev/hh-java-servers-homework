package Counter;

import java.time.Instant;

public class JsonObj {
	private final Integer value;
	private final Instant date;

	public JsonObj(int value) {
		this.value = value;
		this.date = Instant.now();
	}

	public Integer getValue() {
		return value;
	}

	public Instant getDate() {
		return date;
	}

}
//Instant.now().toString();