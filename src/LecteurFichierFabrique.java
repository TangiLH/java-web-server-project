import java.io.FileNotFoundException;

/**
 * fabrique abstraite de lecteurs fichiers
 */
public abstract class LecteurFichierFabrique implements LecteurFichierInterface{
    /**
     * constructeur vide de la classe abstraite
     */
    private LecteurFichierFabrique(){
        //constructeur vide pour classe abstraite
    }

    /**
     * cree le lecteurFichier approprié
     * @param nomFichier le nom du fichier
     * @return
     */
    public static LecteurFichierInterface creerLecteur(String nomFichier){
        LecteurFichierInterface lfi;
        try{
            lfi=LecteurFichierExiste.debutLecture(nomFichier);
        }
        catch(FileNotFoundException e){
            lfi=LecteurFichierNul.debutLecture();//en cas d'erreur on retourne le lecteurFichierNul
        }
        return lfi;
    }
}
