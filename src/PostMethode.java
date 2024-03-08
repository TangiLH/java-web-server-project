import java.io.IOException;
import java.io.OutputStream;

public class PostMethode implements Methode{
    private static PostMethode postMethode;

    private PostMethode(){
        // singleton
    }

    public static PostMethode instanceOf(){
        if(postMethode == null){
            postMethode= new PostMethode();
        }
        return postMethode;
    }

    @Override
    public void execute(MonServer server,String url,OutputStream clientOutput) throws IOException {
        clientOutput.write("HTTP/1.1 405 NON\r\n".getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write("<b>Methode  non supporte!</b>".getBytes());
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
        System.err.println("Client connection closed!");
        clientOutput.close();
    }
    
}
