import java.util.List;

public abstract class RouteFabrique {

    public static void AddRoute(String filename,String filePath,List<RouteInterface> routes){
        switch (filename) {
            case "index.html":
                routes.add(new RouteProxy("/",filePath));
                break;
            
            default:
                routes.add(new RouteProxy("/"+filename,filePath));
                break;
        }
    }
}
