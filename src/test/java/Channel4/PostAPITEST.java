package Channel4;

import baseST.TestBase;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import client.RestClient;
import data.AreaBO;
import data.AreasPojo;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by Asit.Singh on 01-04-2019.
 */
public class PostAPITEST extends TestBase {
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
    }


    @Test
    public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        AreaBO areabO = new AreaBO(1, "SE", "South England", "Test description", "Asingh");
        AreasPojo areaspojo = new AreasPojo(105, "insert", areabO);
        mapper.writeValue(new File("D:\\StellarServiceAPI\\src\\main\\java\\data\\Area.json"), areaspojo);
        String usersJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(areaspojo);
        System.out.println(usersJsonString);
        JSONObject json = new JSONObject(usersJsonString);
        closebaleHttpResponse = restClient.post(url, json.toString(), headerMap); //call the API
        //validate response from API:
        //1. status code:
        int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
        //2. JsonString:
        String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("The response from API is:" + responseJson);
        //json to java object:
        AreasPojo Arearesponse = mapper.readValue(responseString, AreasPojo.class); //actual users object
        System.out.println(Arearesponse);

    }
}

