import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final MonServer server;

    public ClientHandler(Socket clientSocket,MonServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void urlAvoir(String url) throws IOException{
        OutputStream clientOutput = clientSocket.getOutputStream();
        String urlPath=Route.getRoute(server.getRoutes(), url).getRoutePath()!=null?Route.getRoute(server.getRoutes(), url).getRoutePath():null;
        if(urlPath!=null){
            LecteurFichier lf=LecteurFichier.debutLecture(urlPath);
            String sx="aa";
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            while((sx=lf.ligneSuivante())!=""){
                clientOutput.write(Route.remplacerNonHtml(server.getRoutes(), sx).getBytes());
            }
            clientOutput.write("\r\n\r\n".getBytes());
            clientOutput.flush();
            System.err.println("Client connection closed!");
            clientOutput.close();
        }else{
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<b>Page introuvable!</b>".getBytes());
            clientOutput.write("\r\n\r\n".getBytes());
            clientOutput.flush();
            System.err.println("Client connection closed!");
            clientOutput.close();
        }
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // String s;
            // while ((s = in.readLine()) != null) {
            //     System.out.println(s);
            //     if (s.isEmpty()) {
            //         break;
            //     }
            // }
            String requestLine = in.readLine();
            String url ="";
            if (requestLine != null) {
                // Split the request line into parts (method, URL, protocol)
                String[] requestParts = requestLine.split("\\s+");
                if (requestParts.length >= 2) {
                     url = requestParts[1];
                    String method = requestParts[0];
                    
                System.out.println("Requested Method : "+ method +" Requested URL: " + url);
                
                }
            }
            urlAvoir(url);
            // OutputStream clientOutput = clientSocket.getOutputStream();
            // clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            // clientOutput.write("\r\n".getBytes());
            // clientOutput.write("<b>Welcome!</b>".getBytes());
            // clientOutput.write("\r\n\r\n".getBytes());
            // clientOutput.flush();
            // System.err.println("Client connection closed!");
            // clientOutput.close();
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