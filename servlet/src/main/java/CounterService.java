public class CounterService {
    private static CounterService instance;
    private int state;

    private CounterService() {
    }

    public static CounterService getInstance() {
        if (instance == null) {
            instance = new CounterService();
        }
        return instance;
    }

    public synchronized int get() {
        return this.state;
    }

    public synchronized void increment() {
        this.state = this.state + 1;
    }

    public synchronized void decrementBy(int by) {
        this.state = this.state - by;
    }

    public synchronized void reset() {
        this.state = 0;
    }
}
