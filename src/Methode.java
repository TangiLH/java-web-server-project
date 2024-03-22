import java.io.IOException;
import java.io.OutputStream;

/**
 * interface pour les methodes
 */
public interface Methode {

    /**
     * excecute la requete du client
     * @param lfi la page demandée par le client daprés le serveur
     * @param clientOutput le flux de sortie
     * @throws IOException
     */
    public void execute(MonServer server,LecteurFichierInterface lfi, OutputStream clientOutput,RequestLineClient rlc) throws IOException;

    /**
     * recupére la page demandée par le client daprés le serveur
     * @param server le serveur
     * @param url le chemin de la ressource demandée par le client
     * @return LecteurFichierInterface
     */
    public LecteurFichierInterface getLFI(MonServer server,String url);
}
