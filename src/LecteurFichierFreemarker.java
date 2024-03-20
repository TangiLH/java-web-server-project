import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.crypto.Data;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class LecteurFichierFreemarker implements LecteurFichierInterface{
    private String nomFich;
    private DataInscription data;

    private LecteurFichierFreemarker(String nomFich, DataInscription data)throws FileNotFoundException{
        File f=new File(nomFich);//permet de s'assurer de l'existence du fichier
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
    public void writeToOutPut(OutputStream output) {
        //DataInscription nestedData=new DataInscription();
        //data.addData("prenom", "Padrig");
        //data.addData("nom", "An Habask");
       // data.addData("sport_prefere", "mell-droad");
       // data.addData("niveau", "debutant");
        data.addData("user", "Padrig");
        Writer wr=new OutputStreamWriter(output);
        try{
            Template temp=FreemarkerConfig.instanceOf(".").getTemplate("test.dlb");
            temp.process(data.getData(),wr);
            wr.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(TemplateException e){
            e.printStackTrace();
        }
    }
    
}
