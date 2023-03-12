package dao;

import java.util.concurrent.atomic.AtomicLong;

public class CounterDao {

    private final AtomicLong counter;

    public CounterDao(AtomicLong counter){
        this.counter = counter;
    }

    public void increment() throws ArithmeticException{
        counter.accumulateAndGet(1L, Math::addExact);
    }

    public void subtract(Long delta) throws ArithmeticException{
        counter.accumulateAndGet(delta, Math::subtractExact);
    }

    public void reset(){
        counter.set(0);
    }

    public Long get(){
        return counter.get();
    }
}
