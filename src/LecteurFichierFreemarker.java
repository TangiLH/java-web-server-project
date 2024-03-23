import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;


import freemarker.template.Template;
import freemarker.template.TemplateException;

public class LecteurFichierFreemarker implements LecteurFichierInterface{
    private String nomFich;
    private DataInscription data;
    private File fichier;
    private LecteurFichierFreemarker(String nomFich, DataInscription data)throws FileNotFoundException{
        fichier=new File(nomFich);//permet de s'assurer de l'existence du fichier
        this.nomFich=nomFich;
        this.data=data;
    }

    public static LecteurFichierFreemarker debutLecture(String nomFich, DataInscription data)throws FileNotFoundException{
        return new LecteurFichierFreemarker(nomFich,data);
    }
    @Override
    public byte[] getBytes() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBytes'");
    }

    @Override
    public String fichierComplet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fichierComplet'");
    }

    @Override
    public String ligneSuivante() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ligneSuivante'");
    }

    @Override
    public void writeToOutPut(MonServer server,OutputStream output,RequestLineClient rlc) {
        data.addData("param", rlc.getParams().getData());
        StringWriter sw=new StringWriter();
        try{
            System.out.println(fichier.getAbsolutePath());
            Template temp=FreemarkerConfig.instanceOf(fichier.getParent()).getTemplate(fichier.getName());
            temp.process(data.getData(),sw);
            System.out.println("Test :"+sw.toString());
            output.write("HTTP/1.1 200 OK\r\n".getBytes());
            if(rlc.getCookie()==""||rlc.getCookie()==null||!server.verifieCookie(rlc.getCookie())){
                output.write(Cookie.generateCookieBytes(server.generateCookie(rlc)));
            }
            output.write("\r\n".getBytes());
            output.write(sw.toString().getBytes());
            output.write("\r\n\r\n".getBytes());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(TemplateException e){
            e.printStackTrace();
        }
        
    }
    
}
