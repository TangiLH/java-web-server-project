import java.time.LocalTime;

/**
 * Classe Cookie d'un durée de 15 minutes
 */
public class Cookie {

    private String nom;
    private String value;
    private int expiration;

    /**
     * Constructor
     * @param nom
     * @param value
     */
    public Cookie(String nom, String value) {
        this.nom = nom;
        this.value = value;
        expiration=LocalTime.now().toSecondOfDay() + 15 * 60;
        System.err.println(this);
    }

    /**
     * Verifier si le cookie est encore active ou pas
     */
    public boolean checkActive(){
        if(expiration<=LocalTime.now().toSecondOfDay()){
            return false;
        }
        return true;
    }

    /**
     * To string les détails de cookie
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cookie Name").append(nom)
        .append(" Value: ").append(value).append(" expiration: ").append(LocalTime.ofSecondOfDay(expiration))
        .append(System.lineSeparator());

        return sb.toString();
    }

    /**
     * Getter Nom de cookie
     * @return
     */
    public String getNom() {
        return nom;
    }

   

    
}
