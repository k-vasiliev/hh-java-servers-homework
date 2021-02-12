import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CounterServletTest {

    private static Server server;

    @BeforeAll
    public static void setUp() throws Exception {
        server = new Server(8081);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(CounterServlet.class, "/counter");
        server.setHandler(handler);
        server.start();
    }

    @AfterAll
    public static void shutDown() throws Exception {
        server.stop();
    }

    @Test
    public void testInitialGetRequest() {
        HttpRequest request = createGetRequest("http://localhost:8081/counter");
        HttpResponse<String> response = getResponse(request);
        Assertions.assertEquals(200, response.statusCode());
        String body = response.body();
        Assertions.assertEquals("Current counter: 0", body);
    }

    @Test
    public void testPostRequest() {

        HttpRequest getRequest = createGetRequest("http://localhost:8081/counter");

        HttpResponse<String> initialResponse = getResponse(getRequest);
        Long initialCounterValue = getCounterValueFromResponse(initialResponse.body());

        HttpRequest request = createPostRequest("http://localhost:8081/counter");
        HttpResponse<String> response = getResponse(request);
        Assertions.assertEquals(200, response.statusCode());

        HttpResponse<String> secondResponse = getResponse(getRequest);
        Long secondCounterValue = getCounterValueFromResponse(secondResponse.body());

        Assertions.assertEquals(1, secondCounterValue - initialCounterValue);
    }

    @Test
    public void testRepeatedPostRequest() {

        int repeat = 10;

        HttpRequest getRequest = createGetRequest("http://localhost:8081/counter");

        HttpResponse<String> initialResponse = getResponse(getRequest);
        Long initialCounterValue = getCounterValueFromResponse(initialResponse.body());

        for (int i = 0; i < repeat; i++) {
            HttpRequest request = createPostRequest("http://localhost:8081/counter");
            HttpResponse<String> response = getResponse(request);
            Assertions.assertEquals(200, response.statusCode());
        }

        HttpResponse<String> secondResponse = getResponse(getRequest);
        Long secondCounterValue = getCounterValueFromResponse(secondResponse.body());
        System.out.println(secondResponse.body());
        Assertions.assertEquals(repeat, secondCounterValue - initialCounterValue);
    }

    private HttpRequest createGetRequest(String url) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private HttpRequest createPostRequest(String url) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .POST(HttpRequest.BodyPublishers.noBody()).build();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private HttpResponse<String> getResponse(HttpRequest request) {
        try {
            return HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) { return null; }
    }

    private Long getCounterValueFromResponse(String response) {
        String counterValue = response.replace("Current counter: ", "");
        return Long.parseLong(counterValue);
    }


}
