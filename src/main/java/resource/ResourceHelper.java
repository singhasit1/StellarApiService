package resource;

/**
 * Created by Asit.Singh on 02-04-2019.
 */
public class ResourceHelper {
    public static String getResourcePath(String path) {
        String basePath = System.getProperty("user.dir");
        return basePath +"/"+ path;
    }
}
