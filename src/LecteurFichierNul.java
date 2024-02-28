import java.io.IOException;
import java.io.OutputStream;

public class LecteurFichierNul implements LecteurFichierInterface{
    private LecteurFichierNul(){

    }
    public static LecteurFichierInterface debutLecture(){
        return new LecteurFichierNul();
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

    public void writeToOutPut(OutputStream output){
        try{
            output.write("HTTP/1.1 404 not found\r\n".getBytes());
            output.write("\r\n".getBytes());
            output.write("\r\n\r\n".getBytes());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
