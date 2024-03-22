import java.io.IOException;
import java.io.OutputStream;

/**
 * implementation de Methode pour Get
 */
public class MethodeGet implements Methode{

    /**
     * executer la page demandée pour le client
     * @param lfi la page demandeé par le client daprés le serveur
     * @param clientOutput la socket du client
     * @throws IOExceptio
     */
    @Override
    public void execute(MonServer server,LecteurFichierInterface lfi,OutputStream clientOutput,RequestLineClient rlc) throws IOException {
        
        lfi.writeToOutPut(server,clientOutput,rlc);
        clientOutput.flush();
        System.err.println("Client connection closed!");
        clientOutput.close();
    }

    /**
     * recupére la page demandée par le client d'aprés le serveur
     * @param server le serveur
     * @param url le chemin de la ressource demandée par le client
     * @return LecteurFichierInterface
     */
    @Override
    public LecteurFichierInterface getLFI(MonServer server, String url) {
        RouteInterface r=RouteSearch.getRoute(server.getRoutes(), url);
        return LecteurFichierFabrique.creerLecteur(r.getRoutePath());
    }
    
}
