import java.io.IOException;
import java.io.OutputStream;

public class MethodeGet implements Methode{

    @Override
    public void execute(MonServer server,String url,OutputStream clientOutput) throws IOException {
        
        Route r=Route.getRoute(server.getRoutes(), url);
        LecteurFichierInterface lfi =  LecteurFichierFabrique.creerLecteur(r.getRoutePath());
        lfi.writeToOutPut(clientOutput);
        clientOutput.flush();
        System.err.println("Client connection closed!");
        clientOutput.close();
    }
    
}
