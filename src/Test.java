

/**
 * classe pour tester
 */
public class test {
    public static void main(String[] args) {
        LecteurFichier lf=LecteurFichier.debutLecture("/mnt/c/Users/Momo/work/COO/projet-serveur-web-java/src/../tests/etape1.html");
       String sx="aa";
        while((sx=lf.ligneSuivante())!=""){
            System.out.println(sx);

        }
    }
}
