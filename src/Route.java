
/**
 * Un route existent dans le serveur
 */
public class Route implements RouteInterface {

    private String nomRoute;
    private String routePath;

   

    /**
     * Constructor d'un route
     * @param nomRoute
     * @param routePath
     */
    public Route(String nomRoute, String routePath) {
        this.nomRoute = nomRoute;
        this.routePath = routePath;
    }

    /**
     * Getter nom du route
     */
    @Override
    public String getNomRoute() {
        return nomRoute;
    }

    /**
     * Getter pour le chemine de page
     */
    @Override
    public String getRoutePath() {
        return routePath;
    }
    
}
