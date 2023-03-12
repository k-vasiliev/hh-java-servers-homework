package service;

public class CounterServiceFactory {

    private static final CounterService INSTANCE = new CounterService();

    public CounterServiceFactory(){
    }

    public static CounterService getInstance(){
        return INSTANCE;
    }
}
