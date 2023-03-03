package Counter;

import java.time.Instant;

public class JsonObj {
	private final Integer value;
	private final String date;
	
	public JsonObj(int value) {
	    this.value = value;	    
	    this.date = Instant.now().toString();
	}
	public Integer getValue() {
	    return value;
	}
	public String getDate() {
	    return date;
	}

}
