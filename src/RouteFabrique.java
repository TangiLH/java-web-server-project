import java.util.List;

/**
 * Fabrique abstract
 */
public abstract class RouteFabrique {

    /**
     * Ajouter les tout les chemine du serveur dans la liste de l'objet serveur
     * @param filename
     * @param filePath
     * @param routes
     */
    public static void AddRoute(String filename,String filePath,List<RouteInterface> routes){
        switch (filename) {
            case "index.html":
                routes.add(new Route("/",filePath));
                break;
            
            default:
                routes.add(new Route("/"+filename,filePath));
                break;
        }
    }
}
