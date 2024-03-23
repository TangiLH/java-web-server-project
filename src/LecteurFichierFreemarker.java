import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;


import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * implémentation de LecteurFichierInterface capable d'interpreter des fichiers freemarker (extension .dlb uniquement)
 */
public class LecteurFichierFreemarker implements LecteurFichierInterface{
    private DataInscription data;
    private File fichier;
    private LecteurFichierFreemarker(String nomFich, DataInscription data)throws FileNotFoundException{
        fichier=new File(nomFich);//permet de s'assurer de l'existence du fichier
        if(!fichier.isFile()){
            throw new FileNotFoundException();
        }
        this.data=data;
    }

    public static LecteurFichierFreemarker debutLecture(String nomFich, DataInscription data)throws FileNotFoundException{
        return new LecteurFichierFreemarker(nomFich,data);
    }
    @Override
    public byte[] getBytes() throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'getBytes'");
    }

    @Override
    public String fichierComplet() {
        throw new UnsupportedOperationException("Unimplemented method 'fichierComplet'");
    }

    @Override
    public String ligneSuivante() {
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
            if(rlc.getCookie().equals("")||rlc.getCookie()==null||!server.verifieCookie(rlc.getCookie())){
                output.write(Cookie.generateCookieBytes(server.generateCookie(rlc)));
            }
            output.write("\r\n".getBytes());
            output.write(sw.toString().getBytes());
            output.write("\r\n\r\n".getBytes());
        }
        catch(IOException | TemplateException e){
            e.printStackTrace();
        }
    }
    
}
