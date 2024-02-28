import java.io.FileNotFoundException;

public abstract class LecteurFichierFabrique implements LecteurFichierInterface{
    private LecteurFichierFabrique(){
        //constructeur vide pour classe abstraite
    }
    public static LecteurFichierInterface creerLecteur(String nomFichier){
        LecteurFichierInterface lfi;
        try{
            lfi=LecteurFichierExiste.debutLecture(nomFichier);
        }
        catch(FileNotFoundException e){
            lfi=LecteurFichierNul.debutLecture();
        }
        return lfi;
    }
}
