import java.util.List;

public abstract class RouteSearch {
   

    public static boolean  contains(List<RouteInterface> liste,String nomRoute){
        for (RouteInterface route : liste) {
            if(route.getNomRoute().equals(nomRoute)){
                return true;
            }
        }
        return false;
    }

    public static RouteInterface getRoute(List<RouteInterface> liste,String nomRoute){
        for (RouteInterface route : liste) {
            if(route.getNomRoute().equals(nomRoute)){
                return route;
            }
        }
        return RouteNoProxy.instanceOf();
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
