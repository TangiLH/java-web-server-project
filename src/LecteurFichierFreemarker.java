import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.crypto.Data;

import freemarker.template.Template;

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
        // TODO Auto-generated method stub
        Template temp=FreemarkerConfig.instanceOf("fich").getTemplate(nomFich);
        Writer wr=new OutputStreamWriter(output);
        // temp.process(data.getData(),wr);
    }
    
}
