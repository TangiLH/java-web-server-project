public class RouteProxy implements RouteInterface {

    private String nomRoute;
    private String routePath;

   

    public RouteProxy(String nomRoute, String routePath) {
        this.nomRoute = nomRoute;
        this.routePath = routePath;
    }

    @Override
    public String getNomRoute() {
        return nomRoute;
    }

    @Override
    public String getRoutePath() {
        return routePath;
    }
    
}
