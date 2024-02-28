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

    public void urlAvoir(OutputStream clientOutput,String url) throws IOException{
        
        if(Route.contains(server.getRoutes(), url)){
            LecteurFichier lf=LecteurFichier.debutLecture(Route.getRoute(server.getRoutes(),url).getRoutePath());
            String sx="aa";
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
           /*  while((sx=lf.ligneSuivante())!=""){
                //clientOutput.write(Route.remplacerNonHtml(server.getRoutes(), sx).getBytes());
                clientOutput.write(sx.getBytes());
            }*/
            clientOutput.write(lf.getBytes());
            clientOutput.write("\r\n\r\n".getBytes());
            clientOutput.flush();
            System.err.println("Client connection closed!");
            clientOutput.close();
        }else{
            clientOutput.write("HTTP/1.1 404 NON\r\n".getBytes());
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
            String url ="",method="";
            if (requestLine != null) {
                // Split the request line into parts (method, URL, protocol)
                String[] requestParts = requestLine.split("\\s+");
                if (requestParts.length >= 2) {
                     url = requestParts[1];
                     method = requestParts[0];
                    
                System.out.println("Requested Method : "+ method +" Requested URL: " + url);
                
                }
            }
            OutputStream clientOutput = clientSocket.getOutputStream();
            if(!method.equals("GET")){
                clientOutput.write("HTTP/1.1 405 NON\r\n".getBytes());
                clientOutput.write("\r\n".getBytes());
                clientOutput.write("<b>Methode  non supporté!</b>".getBytes());
                clientOutput.write("\r\n\r\n".getBytes());
                clientOutput.flush();
                System.err.println("Client connection closed!");
                clientOutput.close();
            }else{
                urlAvoir(clientOutput,url);
            }
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