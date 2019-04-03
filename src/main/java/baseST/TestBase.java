package baseST;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Asit.Singh on 01-04-2019.
 */
public class TestBase {
    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_500 = 500;
    public int RESPONSE_STATUS_CODE_400 = 400;
    public int RESPONSE_STATUS_CODE_401 = 401;
    public int RESPONSE_STATUS_CODE_201 = 201;

    public Properties prop;


    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("D:\\StellarServiceAPI\\src\\resource\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
