import java.time.LocalTime;
import java.util.UUID;

/**
 * Classe Cookie d'un durée de 15 minutes
 */
public class Cookie {

    private UUID uuid;
    private DataInscription values;
    private int expireTime;

    /**
     * Constructor
     * @param nom
     * @param value
     */
    public Cookie(String uuid) {
        this.uuid =UUID.fromString(uuid);
        this.values=new DataInscription();
        expireTime=LocalTime.now().toSecondOfDay();
    }

    public Cookie() {
        this.uuid =UUID.randomUUID();
        this.values=new DataInscription();
        System.out.println(values.toString());
        expireTime=LocalTime.now().toSecondOfDay()+900;
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

    public DataInscription getValues() {
        return values;
    }

    /**
     * une fonction qui prend un String qui le ligne daprés le in buffer de client
     * qui contient les Cookies: 
     * et qui extrait le valeur de cookie JSMT qui est notre Cookie Name
     * @param stringCookiesFromClient
     * @return
     */
    public static String findCookie(String stringCookiesFromClient){
    String[] st=stringCookiesFromClient.split("=");
    String code="";
    for(int i=0;i<st.length;i++){
        if(st[i].contains("JSMT")){
            for(char c : st[i+1].toCharArray()){
                if(c == ' '){
                    break;
                }else{
                    code+=c;
                }
            }
            break;
        }
    }
        return code;
    
    } 

    public int getExpireTime(){
        return expireTime;
    }

    /**
     * Fonction pour crée le cookie pour le header de client si notre cookie n'existe pas!
     * @param cookieValue
     * @return
     */
    public static byte[] generateCookieBytes(String cookieValue) {
        String cookieHeader = String.format("Set-Cookie: JSMT=%s; max-age=900; Path=/\r\n",
                cookieValue);
        return cookieHeader.getBytes();
    }
    
}
