package service;

import dto.CounterDto;

import java.util.concurrent.atomic.AtomicLong;


public class CounterService {

    private final AtomicLong counter;


    public CounterService(){
        this.counter = new AtomicLong();
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

    public CounterDto get(){
        return CounterMapper.map(counter.get());
    }
}
