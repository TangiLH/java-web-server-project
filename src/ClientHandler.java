import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
/**
 * Les Client implements Runnable pour les threads 
 */
public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final MonServer server;

    /**
     * Constructor 
     * @param clientSocket
     * @param server
     */
    public ClientHandler(Socket clientSocket,MonServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

   


    /**
     * run qui lire la demande du client et l'envoyer au serveur pour l'executé
     */
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String s;
            RequestLineClient rlc = new RequestLineClient(in.readLine());
            while ((s = in.readLine()) != null) {
                if(s.contains("Cookie:")){
                    rlc.addCookie(Cookie.findCookie(s));
                    
                    if(rlc.getCookie()!=null ||rlc.getCookie()!=""){
                        rlc.addCookiee(server.getCookie(rlc.getCookie()));
                    }
                }
                if (s.isEmpty()) {
                    break;
                }
            }
            
            
            System.out.println(rlc);
            OutputStream clientOutput = clientSocket.getOutputStream();
            MethodeFabrique.executeM(rlc, server, clientOutput);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}