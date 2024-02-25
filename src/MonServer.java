import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;




public class MonServer {
    private final List<Route> routes;
    private final ServerSocket serverSocket;
    private final Executor threadPool;

    public MonServer(int port) throws IOException {
        routes = new ArrayList<>();
        threadPool = Executors.newFixedThreadPool(100);
        serverSocket = new ServerSocket(port);
        System.err.println("Server is running on port: "+port);
    }

    public void addRoutes() {
    File repertoire = new File("../tests");
        if (repertoire.exists() && repertoire.isDirectory()) {
            File[] files = repertoire.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if(!Route.contains(routes, file.getName())){
                            if(file.getName().equals("index.html")){
                                routes.add(new Route("/",file.getAbsolutePath()));
                            }else{
                                routes.add(new Route("/"+file.getName(),file.getAbsolutePath()));
                            }
                        } 
                    }
                }
            }
        }else{
            System.out.println("Probléme de lecteur!");
        }
        // for (Route route : routes) {
        // System.out.println(route);
        // }
        
    }

    

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connected");
                // Handle each client in a separate thread
                threadPool.execute(new ClientHandler(clientSocket,this));
                addRoutes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Route> getRoutes() {
        return routes;
    }
}

