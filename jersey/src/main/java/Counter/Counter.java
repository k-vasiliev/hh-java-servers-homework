package Counter;

import java.util.concurrent.atomic.LongAdder;

public class Counter {
    private static final LongAdder value = new LongAdder();

    public static Long getValue() {
        return value.longValue();
    }

    public static Long incrementValue(){
        value.increment();
        return value.longValue();
    }

    public static Long subtractValue(Long subtractionValue){
        value.add(-subtractionValue);
        return value.longValue();
    }

    public static void clearValue(){
        value.reset();
    }
}
