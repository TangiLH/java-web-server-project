import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

/**
 * permet de lire un fichier et de récupérer les lignes une par une
 */
public class LecteurFichierExiste implements LecteurFichierInterface{
    private File fichier;
    private Scanner lecteur;
    /**
     * constructeur privé
     * retourne un nouveau lecteur fichier
     * @param nomFichier chaine contenant le nom du fichier
     */
    private LecteurFichierExiste(String nomFichier)throws FileNotFoundException{
            this.fichier=new File(nomFichier);
            this.lecteur=new Scanner(this.fichier);
    }

    /**
     * constructeur privé
     * retourne un nouveau lecteur fichier
     * @param fichier le fichier
     */
    private LecteurFichierExiste(File fichier) throws FileNotFoundException{
        this.fichier=fichier;
        this.lecteur=new Scanner(this.fichier);
    }

    /**
     * retourne un nouveau lecteurFichier pour le fichier passé en paramètre
     * @param nomFichier le nom du fichier
     * @return un lecteur fichier.
     */
    public static LecteurFichierInterface debutLecture(String nomFichier)throws FileNotFoundException{
        return new LecteurFichierExiste(nomFichier);
    }

    /**
     * retourne un nouveau lecteurFichier pour le fichier passé en paramètre
     * @param fichier le fichier
     * @return un lecteur fichier.
     */
    public LecteurFichierInterface debutLecture(File fichier)throws FileNotFoundException{
        return new LecteurFichierExiste(fichier);
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
        try{
        this.lecteur=new Scanner(this.fichier);;
        StringBuilder retour = new StringBuilder();
        while(lecteur.hasNextLine()){
            retour.append(lecteur.nextLine());
            retour.append(System.lineSeparator());
        }
        return retour.toString();
        }
        catch(FileNotFoundException e){
            return "";
        }
    }
    /**
     * ferme le lecteur
     */
    public void fermerLecteur(){
        lecteur.close();
    }

    /**
     * retourne le tableau d'octets représentant le fichier
     * @return
     * @throws IOException
     */
    public byte[] getBytes()throws IOException{
        return Files.readAllBytes(fichier.toPath());
    }

   

    

    public byte[] generateCookieBytes(String cookieName, String cookieValue, int durationSeconds) {
        Instant expirationTime = Instant.now().plusSeconds(durationSeconds);
        String cookieExpiration = DateTimeFormatter.RFC_1123_DATE_TIME
                .withLocale(Locale.FRANCE)  // Using French locale
                .withZone(ZoneId.of("GMT"))
                .format(expirationTime);
        String cookieHeader = String.format("Set-Cookie: %s=%s; Expires=%s; Path=/\r\n",
                cookieName, cookieValue, cookieExpiration);
        return cookieHeader.getBytes();
    }

     /**
     * ecrit sur le flux de sortie
     * @param OutputStream output le flux de sortie
     */   
    @Override
    public void writeToOutPut(OutputStream output) {
        try {
            output.write("HTTP/1.1 200 OK\r\n".getBytes());
            output.write(generateCookieBytes("test", "valeur_du_cookie", 900)); // Set your desired cookie value here
            output.write("\r\n".getBytes());
            output.write(getBytes()); // Assuming getBytes() returns the response body
            output.write("\r\n\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    

    
}