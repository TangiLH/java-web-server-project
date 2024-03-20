import java.util.List;
/**
 * Une classe abstract qui fait les recherches des routes
 */
public abstract class RouteSearch {
   

    /**
     * Verifier d'apres la liste des route de serveur si un route existe
     * @param liste liste des route d'apres l'objet serveur
     * @param nomRoute nom de route conernée
     * @return boolean
     */
    public static boolean  contains(List<RouteInterface> liste,String nomRoute){
        for (RouteInterface route : liste) {
            if(route.getNomRoute().equals(nomRoute)){
                return true;
            }
        }
        return false;
    }

    /**
     * Routerner le route  d'aprés la liste des routes de serveur sinon un route inexistent
     * @param liste
     * @param nomRoute
     * @return Route
     */
    public static RouteInterface getRoute(List<RouteInterface> liste,String nomRoute){
        for (RouteInterface route : liste) {
            if(route.getNomRoute().equals(nomRoute)){
                return route;
            }
        }
        return RouteNotFound.instanceOf();
    }

   

    // public static String remplacerNonHtml(List<Route> liste,String s){
    //     String key="",value="";
    //     boolean check=false;
    //     for (Route route : liste) {
    //         if(!route.getNomRoute().equals("/")){
    //             key = route.getNomRoute().substring(1);
    //         }
    //         value = route.getRoutePath();
    //         if(s.contains(key)){
    //             check=true;
    //             break;
    //         }
    //     }
    //     if(check){
    //         s=s.replace(key, value);
    //     }
        
    //     return s;
    // }

   
    

    
}
