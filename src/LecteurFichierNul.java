import java.io.IOException;
import java.io.OutputStream;

/**
 * est appelé pour lire un fichier qui n'existe pas
 */
public class LecteurFichierNul implements LecteurFichierInterface{
    private static LecteurFichierInterface cache;
    private LecteurFichierNul(){

    }
    public static LecteurFichierInterface debutLecture(){
        if(cache==null){
            cache=new LecteurFichierNul();
        }
        return cache;
    }
    public String fichierComplet(){
        return "";
    }
    public String ligneSuivante(){
        return "";
    }
    public byte[] getBytes()throws IOException{
        return new byte[0];
    }

    public void writeToOutPut(MonServer server,OutputStream output,RequestLineClient rlc){
        try{
            output.write("HTTP/1.1 200 OK\r\n".getBytes());
            output.write("\r\n".getBytes());
            output.write("404 not found\r\n".getBytes());
            output.write("\r\n\r\n".getBytes());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
