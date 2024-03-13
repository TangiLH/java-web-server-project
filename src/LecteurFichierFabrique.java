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
        //temporaire,test statique
        DataInscription data=new DataInscription();
        data.addData("nom", "An Habask");
        data.addData("prenom", "Padrig");
        data.addData("sport_prefere", "football");
        data.addData("niveau", "debutant");

        LecteurFichierInterface lfi;
        String extension="";
        int i = nomFichier.lastIndexOf('.');
        if (i > 0) {
            extension = nomFichier.substring(i+1);
        }
        try{
            if(extension.equals("dlb")){
                lfi=LecteurFichierFreemarker.debutLecture(nomFichier,data);
            }
            else{
                lfi=LecteurFichierExiste.debutLecture(nomFichier);
            } 
        }
        catch(FileNotFoundException e){
            lfi=LecteurFichierNul.debutLecture();//en cas d'erreur on retourne le lecteurFichierNul
        }
        return lfi;
    }
}
