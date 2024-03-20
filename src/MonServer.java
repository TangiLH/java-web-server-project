import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;



/**
 * NOTRE SERVEUR
 */
public class MonServer {
    private final List<RouteInterface> routes;
    private final ServerSocket serverSocket;
    private final Executor threadPool;
    private boolean proxy;
    private List<Cookie> sessions;

    /**
     * Constructor pour un serveur
     * @param port le port de serveur
     * @param proxy le proxy
     * @throws IOException
     */
    public MonServer(int port,boolean proxy) throws IOException {
        routes = new ArrayList<>();
        threadPool = Executors.newFixedThreadPool(100);
        serverSocket = new ServerSocket(port);
        this.sessions=new ArrayList<>();
        this.proxy =proxy;
        StringBuilder sb = new StringBuilder();
        sb.append("Server is running on port: ").append(port);
        sb.append(" Proxy is: ").append(proxy?"Enabled":"Disabled");
        System.err.println(sb.toString());
    }

    /**
     * fonction pour ajouter tout les chemines existent dans le serveur
     */
    public void addRoutes() {
    File repertoire = new File("../tests");
        if (repertoire.exists() && repertoire.isDirectory()) {
            File[] files = repertoire.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        RouteFabrique.AddRoute(file.getName(), file.getAbsolutePath(), routes);
                    }
                }
            }
        }else{
            System.out.println("Probléme de lecteur!");
        }
        // for (RouteInterface route : routes) {
        // System.out.println(route.getNomRoute());
        // }
        
    // public void addRoutes() {
    // File repertoire = new File("../tests");
    //     if (repertoire.exists() && repertoire.isDirectory()) {
    //         File[] files = repertoire.listFiles();
    //         if (files != null) {
    //             for (File file : files) {
    //                 if (file.isFile()) {
    //                     if(!Route.contains(routes, file.getName())){
    //                         if(file.getName().equals("index.html")){
    //                             routes.add(new Route("/",file.getAbsolutePath()));
    //                         }else{
    //                             routes.add(new Route("/"+file.getName(),file.getAbsolutePath()));
    //                         }
    //                     } 
    //                 }
    //             }
    //         }
    //     }else{
    //         System.out.println("Probléme de lecteur!");
    //     }
    //     for (Route route : routes) {
    //     System.out.println(route.getNomRoute());
    //     }
        
    }

    
    /**
     * fonction pour executer le serveur
     */
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

    // public void verfiesLesCookies(){
    //     Iterator<Cookie> it = cookies.iterator();
    //     while(it.hasNext()){
    //         Cookie c = it.next();
    //         if(!c.checkActive()){
    //             System.err.println("Cookie: "+c.getNom()+" a expiré "+LocalTime.now()+System.lineSeparator());
    //             it.remove(); 
    //         }
    //     }
    // }

    /**
     * Getter pour les chemines du serveur
     * @return routes
     */
    public List<RouteInterface> getRoutes() {
        return routes;
    }

    /**
     * Getter pour verifier le existence de proxy
     * @return
     */
    public boolean isProxy() {
        return proxy;
    }


    public boolean verifieCookie(String uuid){
        for(Cookie c : sessions){
            if(c.getUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }


    
}

