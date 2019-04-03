package client;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.util.JSON;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

/**
 * Created by Asit.Singh on 01-04-2019.
 */
public class RestClient {
    //1. GET Method without Headers:
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

    /*public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpput = new HttpPut(url);
        // httpdelete.setEntity(new StringEntity(entityString)); //for payload
        //for headers:
     *//*   for(Map.Entry<String,String> entry : headerMap.entrySet()) {
            httpdelete.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpdelete);
        return closebaleHttpResponse;
*//*
    }*/



    public CloseableHttpResponse delete(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpdelete = new HttpDelete(url);
       // httpdelete.setEntity(new StringEntity(entityString)); //for payload
        //for headers:
        for(Map.Entry<String,String> entry : headerMap.entrySet()){
            httpdelete.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpdelete);
        return closebaleHttpResponse;

    }

}
