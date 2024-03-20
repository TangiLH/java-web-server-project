import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Class pour recupérer les requetes de client
 */
public class RequestLineClient {
    private String method;
    private String url;
    private DataInscription params;


    /**
     * Constructor que prendre une chaine et le découpé pour avoir la methode demandé par le client 
     * et le url & les params.
     * @param clientRequest
     * @throws UnsupportedEncodingException 
     */
    public RequestLineClient(String clientRequest) throws UnsupportedEncodingException {
        System.err.println(clientRequest);
        String[] requestParts = clientRequest.split("\\s+");
        this.method = requestParts[0];
        this.params = new DataInscription();
        String[] parts = requestParts[1].split("\\?");
        this.url = parts[0];
        if (parts.length >= 2) {
            
            String[] keyValuePairs = parts[1].split("&");
            if(keyValuePairs.length>=1){
                for (String pair : keyValuePairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue.length == 2) {
                        String key = URLDecoder.decode(keyValue[0], "UTF-8");
                        String value=URLDecoder.decode(keyValue[1], "UTF-8");
                        params.addData(key, value);
                    }else{
                        params.addData(pair, "");
                    }
                }   
            }
        }
        if(params != null ){
            for (Map.Entry<String, Object> entry : params.getData().entrySet()) {
                System.err.println("Param: "+entry.getKey()+" "+entry.getValue());
            }
        }
        
    }
    
    /**
     * Fonction pour verifier si le client a autorise les images ou pas! d'aprés les paramétre 
     * de la requete de client
     * @return
     */
    public boolean verifieImageProxy(){
        for (Map.Entry<String, Object> entry : params.getData().entrySet()) {
            if(entry.getKey().equals("noimage")){
                return true;
            }
        }
        return false;
    }
    


    /**
     * Getter Methode
     * @return
     */
    public String getMethod() {
        return method;
    }


    /**
     * Getter Url
     * @return
     */
    public String getUrl() {
        return url;
    }

    
    /**
     * To stirng pour la requete du client
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Requested Method :").append(method).append(" Requested URL: ").append(url).append(System.lineSeparator());
        return sb.toString();
    }

    


    
    
}
