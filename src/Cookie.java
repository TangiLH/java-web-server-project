import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe Cookie d'un durée de 15 minutes
 */
public class Cookie {

    private UUID uuid;
    private List<DataInscription> values;

    /**
     * Constructor
     * @param nom
     * @param value
     */
    public Cookie(String uuid) {
        this.uuid =UUID.fromString(uuid);
        this.values=new ArrayList<>();
        
    }

    
    /**
     * To string les détails de cookie
     */
    @Override
    public String toString() {

        return "Cookie: "+uuid.toString();
    }

    

    public String getUUID(){
        return uuid.toString();
    }

    public List<DataInscription> getValues() {
        return values;
    }

    public static String findCookie(String stringCookiesFromClient){

        String[] parts = stringCookiesFromClient.split(":");
        if (parts.length >= 2) {
            // Split the second part based on "="
            String[] cookies = parts[1].split("=");
            
            // Iterate through the cookies to find the value after "a="
            for (int i = 0; i < cookies.length - 1; i++) {
                if (cookies[i].equals("a")) {
                    String value = cookies[i + 1].split("\\s+")[0]; // Extract the value and remove any leading or trailing spaces
                    return value;
                }
            }
        }
        return "";
    
    } 
    
}
