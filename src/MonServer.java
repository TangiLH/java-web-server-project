import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;



public class MonServer {
    private final List<String> routes;
    private final ServerSocket serverSocket;
    private final Executor threadPool;

    public MonServer(int port) throws IOException {
        routes = new ArrayList<>();
        threadPool = Executors.newFixedThreadPool(100);
        serverSocket = new ServerSocket(port);
        System.err.println("Server is running on port: "+port);
    }

    public void addRoute(HttpMethod opCode, String route) {
        routes.add(opCode.name().concat(route));
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connected");
                // Handle each client in a separate thread
                threadPool.execute(new ClientHandler(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
}

