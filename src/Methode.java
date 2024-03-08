import java.io.IOException;
import java.io.OutputStream;

public interface Methode {

    public void execute(MonServer server,String url,OutputStream clientOutput) throws IOException;
}
