import java.io.IOException;
import java.io.OutputStream;

/**
 * implementation de Methode pour Get
 */
public class MethodeGet implements Methode{

    @Override
    public void execute(MonServer server,String url,OutputStream clientOutput) throws IOException {
        
        RouteInterface r=RouteSearch.getRoute(server.getRoutes(), url);
        LecteurFichierInterface lfi =  LecteurFichierFabrique.creerLecteur(r.getRoutePath());
        lfi.writeToOutPut(clientOutput);
        clientOutput.flush();
        System.err.println("Client connection closed!");
        clientOutput.close();
    }
    
}
