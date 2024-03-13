import java.util.HashMap;
import java.util.Map;

/**
 * modele de données pour Freemarker
 */
public class DataInscription {
    private Map<String,Object> attributs;

    DataInscription(){
        attributs=new HashMap<>();
    }

    public void addData(String cle, Object obj){
        attributs.put(cle,obj);
    }
    public Map<String,Object> getData(){
        return this.attributs;
    }
}
