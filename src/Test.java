/**
 * classe pour tester
 */
public class Test {
    public static void main(String[] args) {
        LecteurFichier lf=LecteurFichier.debutLecture("../../tests/etape1.html");
        System.out.println(lf.fichierComplet());
    }
}
