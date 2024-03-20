import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * classe pour tester
 */
public class test {
    public static void main(String[] args) {
    //     LecteurFichier lf=LecteurFichier.debutLecture("/mnt/c/Users/Momo/work/COO/projet-serveur-web-java/src/../tests/etape1.html");
    //    String sx="aa";
    //     while((sx=lf.ligneSuivante())!=""){
    //         System.out.println(sx);

    //     }
    List<Cookie> cookies= new ArrayList<>();
    cookies.add(new Cookie("Test", "test"));
    while(!cookies.isEmpty()){
        verfiesLesCookies(cookies);
    }

    }

    public static void verfiesLesCookies(List<Cookie> cookies){
        Iterator<Cookie> it = cookies.iterator();
        while(it.hasNext()){
            Cookie c = it.next();
            if(c.checkActive()){
                System.err.println(c);
            }else{
                it.remove();
                
            }
        }
    }
}
