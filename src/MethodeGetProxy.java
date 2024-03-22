import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
/**
 * Proxy Design pattern
 */
public class MethodeGetProxy implements Methode {

    private static MethodeGetProxy methodeGetProxy;

    private MethodeGet methodeGet;

    private HashMap<String, LecteurFichierInterface> cache;

    private MethodeGetProxy(){
        // singleton
    }

    /**
     * gère le singleton pour la classe MethodePost
     * @return MethodePost le singleton de la classe MethodePost
     */
    public static Methode instanceOf(){
        if(methodeGetProxy == null){
            methodeGetProxy = new MethodeGetProxy();
        }
        return methodeGetProxy;
    }

    
    @Override
    public void execute(MonServer server,LecteurFichierInterface lfi,OutputStream clientOutput,RequestLineClient rlc) throws IOException {
        lfi.writeToOutPut(server,clientOutput,rlc);
        clientOutput.flush();
        System.err.println("Client connection closed!");
        clientOutput.close();
    }

    /**
     * recupére la page demandée par le client daprés le cache s'il existe sinon il 
     * le demandé daprés le service et élemine la demande des images
     * Proxy Design pattern
     * @param server le serveur
     * @param url le chemin de la ressource demandée par le client
     * @return LecteurFichierInterface
     */
    @Override
    public LecteurFichierInterface getLFI(MonServer server, String url) {
        if(cache == null ){
            cache = new HashMap<>();
        }
        if( methodeGet == null){
            methodeGet = new MethodeGet();
        }
        if(cache.containsKey(url)){
            System.err.println("Using proxy! cached");
            return cache.get(url);
            
        }else{
            if(url.endsWith(".png")){
                System.err.println("Acces Denied! - no Image");
                LecteurFichierInterface lfi = LecteurFichierFabrique.creerLecteur("rouge.png");
                return lfi;
            }else{
                LecteurFichierInterface lfi = methodeGet.getLFI(server, url);
                cache.put(url, lfi);
                return lfi;
            }
        }
    }
    
}
