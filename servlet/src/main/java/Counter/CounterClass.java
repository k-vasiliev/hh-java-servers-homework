package Counter;

import java.util.concurrent.atomic.LongAdder;

public class CounterClass {
    private static final LongAdder counter = new LongAdder();

    public static Long getCounter() {
        return counter.longValue();
    }

    public static Long incrementCounter(){
        counter.increment();
        return counter.longValue();
    }

    public static Long subtractCounter(Long subtractionValue){
        counter.add(-subtractionValue);
        return counter.longValue();
    }

    public static void clearCounter(){
        counter.reset();
    }
}
