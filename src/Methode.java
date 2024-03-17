import java.io.IOException;
import java.io.OutputStream;

/**
 * interface pour les methodes
 */
public interface Methode {

    /**
     * excecute la requete du client
     * @param server le serveur
     * @param url le chemin de la ressource demandée par le client
     * @param clientOutput le flux de sortie
     * @throws IOException
     */
    public void execute(LecteurFichierInterface lfi, OutputStream clientOutput) throws IOException;

    public LecteurFichierInterface getLFI(MonServer server,String url);
}
