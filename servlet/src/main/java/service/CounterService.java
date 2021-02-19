package service;

public class CounterService {
    private int count;

    private static CounterService instance;

    private CounterService() {}

    public static CounterService getInstance() {
        if (instance == null) {
            instance = new CounterService();
            instance.setCount(0);
        }
        return instance;
    }

    public void incrementByOne() {
        count += 1;
    }

    public void decrementByN(int n) {
        count -= n;
    }

    public void reset() {
        setCount(0);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
