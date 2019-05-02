package baseST;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
            FileInputStream ip = new FileInputStream("D:\\StellarServiceAPI\\src\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url); //http get request
        CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
        return closebaleHttpResponse;
    }

    //2. GET Method with Headers:
    public org.apache.http.client.methods.CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url); //http get request
        for(Map.Entry<String,String> entry : headerMap.entrySet()){
            httpget.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
        return closebaleHttpResponse;    }

    //3. POST Method:
    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url); //http post request
        httppost.setEntity(new StringEntity(entityString)); //for payload
        //for headers:
        for(Map.Entry<String,String> entry : headerMap.entrySet()){
            httppost.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
        return closebaleHttpResponse;

    }

    public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpput = new HttpPut(url);
        httpput.setEntity(new StringEntity(entityString));
        for(Map.Entry<String,String> entry : headerMap.entrySet()) {
            httpput.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpput);
        return closebaleHttpResponse;

    }

    public CloseableHttpResponse delete(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url); //http post request
        httppost.setEntity(new StringEntity(entityString)); //for payload
        //for headers:
        for(Map.Entry<String,String> entry : headerMap.entrySet()){
            httppost.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
        return closebaleHttpResponse;

    }
}
