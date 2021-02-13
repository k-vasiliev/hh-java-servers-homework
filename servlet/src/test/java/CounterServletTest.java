import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CounterServletTest extends BaseTest {

    @Test
    @Order(1)
    public void testInitialGetRequest() {
        HttpResponse<String> response = getCurrentCounterResponse();
        Assertions.assertEquals(200, response.statusCode());
        long counterValue = Long.parseLong(response.body());
        Assertions.assertEquals(0L, counterValue);
    }

    @Test
    @Order(2)
    public void testInitialPostRequest() {
        long initialCounterValue = getCurrentCounterValue();
        Assertions.assertEquals(0L, initialCounterValue);

        HttpRequest request = createPostRequest("http://localhost:8081/counter");
        HttpResponse<String> response = getResponse(request);
        Assertions.assertEquals(200, response.statusCode());

        long postIncrementCounterValue = getCurrentCounterValue();
        Assertions.assertEquals(1, postIncrementCounterValue);
    }

    @Test
    @Order(3)
    public void testClearCounterRequest() {
        long initialCounterValue = getCurrentCounterValue();
        Assertions.assertEquals(1, initialCounterValue);

        HttpRequest clearCounterRequest = createPostRequest("http://localhost:8081/counter/clear");
        HttpResponse<String> clearResponse = getResponse(clearCounterRequest);
        Assertions.assertEquals(400, clearResponse.statusCode());

        long postClearCounterValue = getCurrentCounterValue();
        Assertions.assertEquals(0, postClearCounterValue);
    }

    @Test
    @Order(4)
    public void testRepeatedPostRequest() {
        long initialCounterValue = getCurrentCounterValue();
        Assertions.assertEquals(0, initialCounterValue);

        int repeats = 10;
        for (int i = 0; i < repeats; i++) {
            incrementCounterWithPostRequest();
        }

        long postIncrementCounterValue = getCurrentCounterValue();
        Assertions.assertEquals(repeats, postIncrementCounterValue);
    }



}
