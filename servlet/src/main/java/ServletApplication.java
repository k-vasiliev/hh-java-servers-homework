import model.Counter;

public class ServletApplication {

  public static void main(String[] args) {
    Counter counter = new Counter();

    System.out.println("counter = " + counter.get());
    counter.increment();
    counter.increment();
    counter.increment();
    counter.increment();
    System.out.println("counter = " + counter.get());
counter.decrement(3L);
    System.out.println("counter = " + counter.get());
counter.clear();
    System.out.println("counter = " + counter.get());
counter.increment();
    counter.decrement(3L);
    System.out.println("counter = " + counter.get());
    // run, Jetty, run!
  }
}
