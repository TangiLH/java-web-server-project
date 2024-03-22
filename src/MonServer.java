import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
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

    public List<Cookie> getSessions() {
        return sessions;
    }

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
    
        
    }

    
    /**
     * fonction pour executer le serveur
     */
    public void start() {
        addRoutes();
        while (true) {
            try {
                removeExpiredCookie();
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connected");
                // Handle each client in a separate thread
                threadPool.execute(new ClientHandler(clientSocket,this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


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
    

    /**
     * fonction pour verifier l'existance d'un cookie dans le serveur
     * @param uuid
     * @return
     */
    public boolean verifieCookie(String uuid){
        for(Cookie c : sessions){
            if(c.getUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }
    /**
     * Fonction qui genere une cookie s'elle n'existe pas
     * @param rlc
     */
    public String generateCookie(RequestLineClient rlc){
        Cookie c=null;
        if(!verifieCookie(rlc.getCookie())){
            c = new Cookie();
            sessions.add(c);
        }
        return c.getUUID();
    }

    /**
     * fonction qui retourne une cookie d'aprés la session
     * @param cookieUUID
     * @return
     */
    public Cookie getCookie(String cookieUUID){
        for(Cookie cc : sessions){
            if(cc.getUUID().equals(cookieUUID)){
                return cc;
            }
        }
        return null;
    }


    public void setProxy(boolean proxy) {
        this.proxy = proxy;
    }

    /**
     * fonction qui supprime les cookies expirer
     */
    private void removeExpiredCookie(){
        for(Cookie c : sessions){
            if((c.getExpireTime())<=LocalTime.now().toSecondOfDay()){
                sessions.remove(c);
            }
        }
    } 




    
}

