/**
 * classe pour tester
 */
public class test {
    public static void main(String[] args) {
        LecteurFichier lf=LecteurFichier.debutLecture("../../tests/etape1.html");
        System.out.println(lf.fichierComplet());
    }
}
