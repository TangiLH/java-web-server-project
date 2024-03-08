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

    // public void urlAvoir(OutputStream clientOutput,String url) throws IOException{
        
    //     if(Route.contains(server.getRoutes(), url)){
    //         LecteurFichier lf=LecteurFichier.debutLecture(Route.getRoute(server.getRoutes(),url).getRoutePath());
    //         clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
    //         clientOutput.write("\r\n".getBytes());
    //        /*  while((sx=lf.ligneSuivante())!=""){
    //             //clientOutput.write(Route.remplacerNonHtml(server.getRoutes(), sx).getBytes());
    //             clientOutput.write(sx.getBytes());
    //         }*/
    //         clientOutput.write(lf.getBytes());
    //         clientOutput.write("\r\n\r\n".getBytes());
    //         clientOutput.flush();
    //         System.err.println("Client connection closed!");
    //         clientOutput.close();
    //     }else{
    //         clientOutput.write("HTTP/1.1 404 NON\r\n".getBytes());
    //         clientOutput.write("\r\n".getBytes());
    //         clientOutput.write("<b>Page introuvable!</b>".getBytes());
    //         clientOutput.write("\r\n\r\n".getBytes());
    //         clientOutput.flush();
    //         System.err.println("Client connection closed!");
    //         clientOutput.close();
    //     }
    // }
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
            RequestLineClient rlc = new RequestLineClient(in.readLine());
            System.out.println(rlc);
            OutputStream clientOutput = clientSocket.getOutputStream();
            MethodeFabrique.executeM(rlc, server, clientOutput);
            // if(!rlc.getMethod().equals("GET")){
            //     clientOutput.write("HTTP/1.1 405 NON\r\n".getBytes());
            //     clientOutput.write("\r\n".getBytes());
            //     clientOutput.write("<b>Methode  non supporte!</b>".getBytes());
            //     clientOutput.write("\r\n\r\n".getBytes());
            //     clientOutput.flush();
            //     System.err.println("Client connection closed!");
            //     clientOutput.close();
            // }else{
            //     urlAvoir(clientOutput,rlc.getUrl());
            // }
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