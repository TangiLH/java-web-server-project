import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * permet de lire un fichier et de récupérer les lignes une par une
 */
public class LecteurFichier{
    private File fichier;
    private Scanner lecteur;
    /**
     * constructeur privé
     * retourne un nouveau lecteur fichier
     * @param nomFichier chaine contenant le nom du fichier
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
     * constructeur privé
     * retourne un nouveau lecteur fichier
     * @param fichier le fichier
     */
    private LecteurFichier(File fichier){
        try{
            this.fichier=fichier;
            this.lecteur=new Scanner(this.fichier);
        }
        catch(FileNotFoundException e){
            System.out.println("Fichier non trouvé ");
            e.printStackTrace();
        }
    }

    /**
     * retourne un nouveau lecteurFichier pour le fichier passé en paramètre
     * @param nomFichier le nom du fichier
     * @return un lecteur fichier.
     */
    public static LecteurFichier debutLecture(String nomFichier){
        return new LecteurFichier(nomFichier);
    }

    /**
     * retourne un nouveau lecteurFichier pour le fichier passé en paramètre
     * @param fichier le fichier
     * @return un lecteur fichier.
     */
    public static LecteurFichier debutLecture(File fichier){
        return new LecteurFichier(fichier);
    }

    /**
     * retourne la prochaine ligne du fichier
     * @return une chaine contenant la prochaine ligne du fichier
     */
    public String ligneSuivante(){
        String retour;
        if(lecteur.hasNextLine()){
            retour=lecteur.nextLine();
        }
        else{
            this.fermerLecteur();
            retour="";
        }
        return retour;
    }

    /**
     * retourne le fichier complet
     * @return une chaine contenant le fichier complet
     */
    public String fichierComplet(){
        this.lecteur=LecteurFichier.debutLecture(this.fichier).lecteur;
        StringBuilder retour = new StringBuilder();
        while(lecteur.hasNextLine()){
            retour.append(lecteur.nextLine());
            retour.append(System.lineSeparator());
        }
        return retour.toString();
    }
    /**
     * ferme le lecteur
     */
    public void fermerLecteur(){
        lecteur.close();
    }
}