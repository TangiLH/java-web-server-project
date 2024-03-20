import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    

   

    
}
