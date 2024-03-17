import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class MethodeGetProxy implements Methode {

    private static MethodeGetProxy methodeGetProxy;

    private MethodeGet methodeGet;

    private HashMap<String, LecteurFichierInterface> cache;

    private MethodeGetProxy(){
        // singleton
    }

    public static Methode instanceOf(){
        if(methodeGetProxy == null){
            methodeGetProxy = new MethodeGetProxy();
        }
        return methodeGetProxy;
    }

    @Override
    public void execute(LecteurFichierInterface lfi,OutputStream clientOutput) throws IOException {
        lfi.writeToOutPut(clientOutput);
        clientOutput.flush();
        System.err.println("Client connection closed!");
        clientOutput.close();
    }

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
                LecteurFichierInterface lfi = LecteurFichierFabrique.creerLecteur("");
                return lfi;
            }else{
                LecteurFichierInterface lfi = methodeGet.getLFI(server, url);
                cache.put(url, lfi);
                return lfi;
            }
        }
    }
    
}
