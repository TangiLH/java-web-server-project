public class RequestLineClient {
    private String method;
    private String url;


    
    public RequestLineClient(String clientRequest) {
        String[] requestParts = clientRequest.split("\\s+");
        this.method = requestParts[0];
        this.url = requestParts[1];
    }



    public String getMethod() {
        return method;
    }


    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Requested Method :").append(method).append(" Requested URL: ").append(url).append(System.lineSeparator());
        return sb.toString();
    }

    


    
    
}
