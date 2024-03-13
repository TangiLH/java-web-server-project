import java.io.IOException;
import java.io.OutputStream;

public class MethodePost implements Methode{
    private static MethodePost postMethode;

    private MethodePost(){
        // singleton
    }

    public static MethodePost instanceOf(){
        if(postMethode == null){
            postMethode= new MethodePost();
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
