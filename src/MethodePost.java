import java.io.IOException;
import java.io.OutputStream;

/**
 * implementation de Methode pour Post (non supportée, renvoie erreur 405)
 */
public class MethodePost implements Methode{
    private static MethodePost postMethode;

    /**
     * constructeur privé de la MethodePost pour le singleton
     */
    private MethodePost(){
        // singleton
    }

    /**
     * gère le singleton pour la classe MethodePost
     * @return MethodePost le singleton de la classe MethodePost
     */
    public static MethodePost instanceOf(){
        if(postMethode == null){
            postMethode= new MethodePost();
        }
        return postMethode;
    }

    /**
     * Retourner un message que les Methode different que Get sont insupportable!
     * @param lfi la page demandeé par le client daprés le serveur
     * @param clientOutput la socket du client
     * @throws IOExceptio
     */
    @Override
    public void execute(LecteurFichierInterface lfi, OutputStream clientOutput) throws IOException {
        clientOutput.write("HTTP/1.1 405 NON\r\n".getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write("<b>Methode  non supporte!</b>".getBytes());
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
        System.err.println("Client connection closed!");
        clientOutput.close();
    }


    /**
     * Que pour l'implementation
     */
    @Override
    public LecteurFichierInterface getLFI(MonServer server, String url) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLFI'");
    }
    
}
