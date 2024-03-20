import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TimeZone;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

/**
 * classe configurant freemarker. utilise le singleton
 */
public class FreemarkerConfig {
    private static FreemarkerConfig cache;
    private static Configuration cfg=new Configuration(Configuration.VERSION_2_3_32);

    private FreemarkerConfig(String path){
        try{
            cfg.setDirectoryForTemplateLoading(new File(path));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
        try{
            FileTemplateLoader ctl=new FileTemplateLoader(new File("../tests"));
            cfg.setTemplateLoader(ctl);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        
        //cfg.setClassForTemplateLoading(this.getClass(), path);
    }

    public static Configuration instanceOf(String path){
        if(cache==null){
            cache= new FreemarkerConfig(path);
        }
        return cache.cfg;
    }

    /**
     * retourne le template associé à la configuration
     */
    public Template getTemplate(String str) {
        Template t=null;
        try{
            t=cfg.getTemplate(str);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return t;

    }
}
