public class Counter {

    private static Integer counter;

    public Counter() {
        counter = 0;
    }

    public static int getValue() {
        return counter;
    }
    public static void drop(){
        counter=0;
    }
    public static void inc() {
        counter++;
    }
    public static void sub(int value) {
        counter-=value;
    }
}
