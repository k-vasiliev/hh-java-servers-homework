import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
  @Override
  protected void configure() {
    bind(CounterService.class).to(CounterService.class).in(Singleton.class);
  }
}
