import java.io.IOException;
import java.io.OutputStream;

/**
 * interface servant pour lire des fichiers
 */
public interface LecteurFichierInterface {
    /**
     * renvoie le fichier sous forme de tableau d'octets
     * @return byte[]
     */
    public byte[] getBytes()throws IOException;

    /**
     * retourne le fichier complet 
     * @return String
     */
    public String fichierComplet();

    /**
     * retourne la prochaine ligne du fichier
     * @return String
     */
    public String ligneSuivante();

    /**
     * ecrit le fichier sur le flux de sortie passé en paramètre
     * @param output le flux de sortie
     */
    public void writeToOutPut(MonServer server,OutputStream output);
}
