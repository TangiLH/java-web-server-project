
/**
 * Class pour recupérer les requetes de client
 */
public class RequestLineClient {
    private String method;
    private String url;


    /**
     * Constructor que prendre une chaine et le découpé pour avoir la methode demandé par le client 
     * et le url.
     * @param clientRequest
     */
    public RequestLineClient(String clientRequest) {
        String[] requestParts = clientRequest.split("\\s+");
        this.method = requestParts[0];
        this.url = requestParts[1];
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
