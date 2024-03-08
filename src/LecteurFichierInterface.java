import java.io.IOException;
import java.io.OutputStream;

public interface LecteurFichierInterface {
    public byte[] getBytes()throws IOException;
    public String fichierComplet();
    public String ligneSuivante();
    public void writeToOutPut(OutputStream output);
}
