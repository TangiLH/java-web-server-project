public class RouteNoProxy implements RouteInterface{

    private static RouteNoProxy routeNoProxy;
    private RouteNoProxy(){
        // Singleton
    }

    public static RouteNoProxy instanceOf(){
        if(routeNoProxy == null){
            routeNoProxy = new RouteNoProxy();
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
