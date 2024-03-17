/**
 * un route inexistent 
 */
public class RouteNotFound implements RouteInterface{

    private static RouteNotFound routeNoProxy;
    private RouteNotFound(){
        // Singleton
    }

    public static RouteNotFound instanceOf(){
        if(routeNoProxy == null){
            routeNoProxy = new RouteNotFound();
        }
        return routeNoProxy;
    }

    @Override
    public String getNomRoute() {
        return "";
    }

    @Override
    public String getRoutePath() {
        return "";
    }
    
}
