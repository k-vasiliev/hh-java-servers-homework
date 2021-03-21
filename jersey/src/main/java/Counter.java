public class Counter {
    private int count = 0;

    public int get() {
        return count;
    }

    public int increment() {
        return ++count;
    }

    public int decrement(int val) {
        count -= val;
        return count;
    }

    public int clear() {
        count = 0;
        return count;
    }
}
