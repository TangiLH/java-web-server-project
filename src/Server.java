import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpHandler;

public class Server {
    private final Map<String, RequestRunner> routes;
    private final ServerSocket socket;
    private final Executor threadPool;

    public Server(int port) throws IOException {
        routes = new HashMap<>();
        threadPool = Executors.newFixedThreadPool(100);
        socket = new ServerSocket(port);
    }

    public void addRoute(HttpMethod opCode, String route, RequestRunner runner) {
        routes.put(opCode.name().concat(route), runner);
    }

    // public void start() throws IOException {
    // HttpHandler handler = new HttpHandler(routes);

    // while (true) {
    //     Socket clientConnection = socket.accept();
    //     handleConnection(clientConnection);
    // }
}

