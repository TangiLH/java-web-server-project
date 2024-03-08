import java.io.IOException;
import java.io.OutputStream;

public class MethodeFabrique {

    
    public static void executeM(RequestLineClient rlc,MonServer server,OutputStream clientOutput) throws IOException{
        switch (rlc.getMethod()) {
            case "GET":
                Methode m = new GetMethode();
                m.execute(server,rlc.getUrl(), clientOutput);
                break;
        
            default:
                Methode mp = PostMethode.instanceOf();
                mp.execute(server,rlc.getUrl(), clientOutput);
                break;
        }
    }

   
    
}
