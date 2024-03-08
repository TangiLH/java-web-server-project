import java.util.List;

public class Route {
    private String nomRoute;
    private String routePath;

    private static Route nonRoute;

    public Route(String nomRoute, String routePath) {
        this.nomRoute = nomRoute;
        this.routePath = routePath;
    }

    public static boolean  contains(List<Route> liste,String nomRoute){
        for (Route route : liste) {
            if(route.nomRoute.equals(nomRoute)){
                return true;
            }
        }
        return false;
    }

    public static Route getRoute(List<Route> liste,String nomRoute){
        for (Route route : liste) {
            if(route.nomRoute.equals(nomRoute)){
                return route;
            }
        }
        return getNonRoute();
    }

    private  static Route getNonRoute(){
        if(nonRoute== null){
            nonRoute = new Route("","");
        }
        return nonRoute;
    }

    public static String remplacerNonHtml(List<Route> liste,String s){
        String key="",value="";
        boolean check=false;
        for (Route route : liste) {
            if(!route.getNomRoute().equals("/")){
                key = route.getNomRoute().substring(1);
            }
            value = route.getRoutePath();
            if(s.contains(key)){
                check=true;
                break;
            }
        }
        if(check){
            s=s.replace(key, value);
        }
        
        return s;
    }

    public String getNomRoute() {
        return nomRoute;
    }

    public String getRoutePath() {
        return routePath;
    }

    @Override
    public String toString() {
        return "Route [nomRoute=" + nomRoute + ", routePath=" + routePath + "]";
    }

    

    
}
