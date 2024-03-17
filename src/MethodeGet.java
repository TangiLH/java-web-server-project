import java.io.IOException;
import java.io.OutputStream;

/**
 * implementation de Methode pour Get
 */
public class MethodeGet implements Methode{

    @Override
    public void execute(LecteurFichierInterface lfi,OutputStream clientOutput) throws IOException {
        
        lfi.writeToOutPut(clientOutput);
        clientOutput.flush();
        System.err.println("Client connection closed!");
        clientOutput.close();
    }

    
    @Override
    public LecteurFichierInterface getLFI(MonServer server, String url) {
        RouteInterface r=RouteSearch.getRoute(server.getRoutes(), url);
        LecteurFichierInterface lfi =  LecteurFichierFabrique.creerLecteur(r.getRoutePath());
        return lfi;
    }
    
}
