public class Count {

    private int value = 0;

    public Count() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        this.value = v;
    }

    public void inc() {
        this.value++;
    }

    public void dec() {
        this.value--;
    }
}
