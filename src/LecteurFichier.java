import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurFichier{
    private File fichier;
    private Scanner lecteur;
    /**
     * Constructeur privé de la classe lecteurFichier
     * @param nomFichier
     */
    private LecteurFichier(String nomFichier){
        try{
            this.fichier=new File(nomFichier);
            this.lecteur=new Scanner(this.fichier);
        }
        catch(FileNotFoundException e){
            System.out.println("Fichier non trouvé :"+nomFichier);
            e.printStackTrace();
        }
    }

    /**
     * retourne un nouveau lecteurFichier pour le fichier passé en paramètre
     * @param nomFichier le nom du fichier
     * @return un lecteur fichier.
     */
    public LecteurFichier debutLecture(String nomFichier){
        return new LecteurFichier(nomFichier);
    }
}