package ServiceAPI;

import baseST.TestBase;
import client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import data.AreaBO;
import data.AreasPojo;
import helper.LoggerHelper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import util.TestUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Asit.Singh on 29-04-2019.
 */
public class ServiceURL extends TestBase {
    static final Logger log = LoggerHelper.getLogger(ServiceURL.class);
    CloseableHttpResponse closeablehttpResponse;
    TestBase testbase;
    String serviceUrl;
    RestClient restclient;
    JSONObject responseJson;
    List<List<String>> data;
    String strGetUrl;
    String strAreaKey;
    String strAreaName;
    String strAreaCode;
    String strAudienceSetKey;
    String strAreaDescription;
    HashMap<String, String> headerMap;
    ObjectMapper mapper;
    AreaBO areabO;
    AreasPojo areaspojo;
    String usersJsonString;
    JSONObject json;


    public String getService(String getAreaService){
        serviceUrl = prop.getProperty("URL");
        strGetUrl = serviceUrl + getAreaService;
        log.info("Application Service URL IS:"+strGetUrl );
        return strGetUrl;
    }

  public void getRequest() throws IOException {
      testbase = new TestBase();
      closeablehttpResponse = testbase.get(strGetUrl);
      log.info(" Perform the GET request ");
  }


  public int responseCode(){
      int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
      log.info("Status Code Is : " +statusCode);
      return statusCode;
  }
  public void getAreaResponse() throws IOException {
      String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
      responseJson = new JSONObject(responseString);
      log.info("Response JSON from API ");
      System.out.println("=======================================================================================================");
      System.out.println(responseJson);
      System.out.println("========================================================================================================");
       }

       public String GetAreaName(){
           strAreaName = TestUtil.getValueByJPath(responseJson, "/data[3]/area_name");
           log.info("Area Name Is "+strAreaName);
           return strAreaName;
           }
    public String GetAreaCode(){
        strAreaCode = TestUtil.getValueByJPath(responseJson, "/data[3]/area_code");
        log.info("Area Code Is "+strAreaCode);
        return strAreaCode;
    }
    public String GetAreaKey(){
        strAreaKey = TestUtil.getValueByJPath(responseJson, "/data[3]/area_key");
        log.info("Area Key Is "+strAreaKey);
        return strAreaKey;
    }
    public String AudienceSet(){
        strAudienceSetKey = TestUtil.getValueByJPath(responseJson, "/data[3]/audience_set_key");
        log.info("Area Name Is "+strAudienceSetKey);
        return strAudienceSetKey;
    }
    public String AreaDescription(){
        strAreaDescription = TestUtil.getValueByJPath(responseJson, "/data[3]/area_desc");
        log.info("Area Description Is "+strAreaDescription);
        return strAreaDescription;
    }

    public void verifyResponseData(DataTable JSONResponse) throws IOException {
        String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        ServiceURL serviceurl = new ServiceURL();
        data = JSONResponse.raw();
        strAreaName= TestUtil.getValueByJPath(responseJson, "/data[3]/area_name");
        Assert.assertEquals(strAreaName, data.get(1).get(1), "Area Name not match");
        log.info("Area Name Matched");
        strAreaCode= TestUtil.getValueByJPath(responseJson, "/data[3]/area_code");
        Assert.assertEquals(strAreaCode, data.get(2).get(1), "Area Code not Match");
        log.info("Area Code Matched");
        strAudienceSetKey= TestUtil.getValueByJPath(responseJson, "/data[3]/audience_set_key");
        Assert.assertEquals(strAudienceSetKey, data.get(3).get(1), "audience set key not Match");
        log.info("Audience Set Matched");
        strAreaDescription= TestUtil.getValueByJPath(responseJson, "/data[3]/area_desc");
        Assert.assertEquals(strAreaDescription, data.get(4).get(1), "Area_desc not Match");
        log.info("Area desc Matched");
    }

    public void CreateAreaService(String AreaName,String AreaCode) throws IOException {
        restclient = new RestClient();
        headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        mapper = new ObjectMapper();
        AreaBO areabO = new AreaBO(1, AreaName, AreaCode, AreaName, "Asingh");
        areaspojo = new AreasPojo(105, "insert", areabO);
        mapper.writeValue(new File("D:\\StellarServiceAPI\\src\\main\\java\\data\\Area.json"), areaspojo);
        usersJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(areaspojo);
        JSONObject json = new JSONObject(usersJsonString);


    }
    public void postRequest() throws IOException {
        closeablehttpResponse = restclient.post(strGetUrl, json.toString(), headerMap);
        log.info(" Perform the GET request ");
    }

    public int VerifyPostResponseCode(){
        int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
       return statusCode;
    }



  }



