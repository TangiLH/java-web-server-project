import java.io.IOException;
import java.io.OutputStream;

/**
 * fabrique abstraite pour les methodes
 */
public abstract class MethodeFabrique {

    private MethodeFabrique(){}
    
    /**
     * traite la méthode demandée par le client
     * @param rlc la requete du client
     * @param server le serveur
     * @param clientOutput le flux de sortie
     * @throws IOException
     */
    public static void executeM(RequestLineClient rlc,MonServer server,OutputStream clientOutput) throws IOException{
        switch (rlc.getMethod()) {
            case "GET":
                Methode m = new MethodeGet();
                m.execute(server,rlc.getUrl(), clientOutput);
                break;
        
            default:
                Methode mp = MethodePost.instanceOf();
                mp.execute(server,rlc.getUrl(), clientOutput);
                break;
        }
    }

   
    
}
