import java.util.List;

public abstract class RouteFabrique {

    public static void AddRoute(String filename,String filePath,boolean proxy,List<RouteInterface> routes){
        switch (filename) {
            case "index.html":
                routes.add(new RouteProxy("/",filePath));
                break;
            case ".+\\.png$":
                if(proxy){
                    routes.add(new RouteProxy("/"+filename,filePath));
                }else{
                    
                }
                break;
            default:
                if(proxy){
                    routes.add(new RouteProxy("/"+filename,filePath));
                }else if(filename.endsWith("png")){
                    routes.add(RouteNoProxy.instanceOf());
                }else{
                    routes.add(new RouteProxy("/"+filename,filePath));
                }
                
                break;
        }
    }
}
