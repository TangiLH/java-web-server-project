import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // String s;
            // while ((s = in.readLine()) != null) {
            //     System.out.println(s);
            //     if (s.isEmpty()) {
            //         break;
            //     }
            // }
            OutputStream clientOutput = clientSocket.getOutputStream();
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<b>Welcome!</b>".getBytes());
            clientOutput.write("\r\n\r\n".getBytes());
            clientOutput.flush();
            System.err.println("Client connection closed!");
            clientOutput.close();
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