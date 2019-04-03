package Channel4;

import baseST.TestBase;
import client.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.util.JSON;
import data.DeleteArea;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Asit.Singh on 02-04-2019.
 */
public class DeleteAPITEST extends TestBase {
    TestBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse closebaleHttpResponse;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException {
        testBase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        url = serviceUrl + apiUrl;
        System.out.println(url);

    }

    @Test
    public void DeleteAPITest() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        DeleteArea deletearea = new DeleteArea(105, "delete");
        //mapper.writeValue(new File("D:\\StellarServiceAPI\\src\\main\\java\\data\\DeleteArea.json"),deletearea);
        String usersJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(deletearea);
        System.out.println(usersJsonString);
        JSONObject JSONObj = new JSONObject(usersJsonString);
        System.out.println(JSONObj);
        closebaleHttpResponse = restClient.delete(url,JSONObj.toString(),headerMap);
        int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Response Code Is " +statusCode);
    }
}