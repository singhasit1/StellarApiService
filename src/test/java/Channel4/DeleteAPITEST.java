package Channel4;

import baseST.TestBase;
import client.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.util.JSON;
import data.AreaBO;
import data.AreasPojo;
import data.DeleteArea;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
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
    public void DeleteAPITest() throws IOException, InterruptedException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        //AreaBO areabo =  new AreaBO(161,1,"North England","North England","Asingh1");
        AreasPojo areaspojo = new AreasPojo(105, "delete");
        mapper.writeValue(new File("D:\\StellarServiceAPI\\src\\main\\java\\data\\DeleateArea.json"), areaspojo);
        String usersJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(areaspojo);
        System.out.println(usersJsonString);
        JSONObject json = new JSONObject(usersJsonString);
        closebaleHttpResponse = restClient.delete(url,json.toString(), headerMap); //call the API
        Thread.sleep(5000);
        //validate response from API:
        //1. status code:
        int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_500);
        //2. JsonString:
        String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("The response from API is:" + responseJson);
        //json to java object:
        AreasPojo Arearesponse = mapper.readValue(responseString, AreasPojo.class); //actual users object
        System.out.println(Arearesponse);
//        Assert.assertTrue(areaspojo.getAreaBO().getAreaName().equals(Arearesponse.getAreaBO().getAreaName()));
        /*Assert.assertTrue(areaspojo.getAreaCode().equals(Arearesponse.getAreaCode()));
        Assert.assertTrue(areaspojo.getAudienceSetKey().equals(Arearesponse.getAudienceSetKey()));
        Assert.assertTrue(areaspojo.getAreaDesc().equals(Arearesponse.getAreaDesc()));
        System.out.println(Arearesponse.getAreaName());
        System.out.println(Arearesponse.getAreaCode());*/
    }
}