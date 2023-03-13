import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import Counter.CounterController;
import Counter.CounterResetController;
import Counter.StatusController;

public class ServletApplication {

	private static Server createServer(int port) {
		Server server = new Server(port);
		ServletHandler ctx = new ServletHandler();
		ctx.addServletWithMapping(StatusController.class, "/status");
		ctx.addServletWithMapping(CounterController.class, "/counter");
		ctx.addServletWithMapping(CounterResetController.class, "/counter/clear");
		server.setHandler(ctx);
		return server;
	}

	public static void main(String[] args) throws Exception {
		int port = 8081;
		Server server = createServer(port);
		server.start();
		server.join();
	}
}
