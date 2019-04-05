package helper;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import resource.ResourceHelper;

/**
 * Created by Asit.Singh on 02-04-2019.
 */
public class LoggerHelper {
    private static boolean root=false;

    public static Logger getLogger(Class cls){
        if(root){
            return Logger.getLogger(cls);
        }
        PropertyConfigurator.configure("D:\\StellarServiceAPI\\src\\main\\java\\helper\\log4j.properties");
        root = true;
        return Logger.getLogger(cls);
    }
}

