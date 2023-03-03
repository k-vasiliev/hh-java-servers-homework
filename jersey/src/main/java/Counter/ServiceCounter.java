package Counter;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceCounter {

	private static final AtomicInteger counter = new AtomicInteger();


	public static int getCounter() {
		return counter.get();
	}
	public static int addCounter() {
		counter.incrementAndGet();
		return counter.get();
	}
	public static int decreaseCounter(int val) {
	    counter.addAndGet(-val);

		return counter.get();
	}
	public static int resetCounter() {
		counter.set(0);
		return 0;
	}
	
	
}
