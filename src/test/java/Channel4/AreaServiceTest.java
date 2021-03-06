package Channel4;

import baseST.TestBase;
import client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.AreaBO;
import data.AreasPojo;
import helper.LoggerHelper;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import util.TestUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Asit.Singh on 01-04-2019.
 */
public class AreaServiceTest extends TestBase {

    TestBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse closebaleHttpResponse;

    static final Logger log = LoggerHelper.getLogger(AreaServiceTest.class);

    @Given("^The Apis \\\"([^\\\"]*)\\\" up and running for area service$")
    public void the_Apis_up_and_running_for_area_service(String apiUrl ) throws Throwable {
        testBase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        url = serviceUrl + apiUrl;
        log.info("Application Service URL IS:"+url );   }

    @When("^Perform the GET request$")
    public void perform_the_GET_request() throws Throwable {
            restClient = new RestClient();
            closebaleHttpResponse = restClient.get(url);
            log.info(" Perform the GET request ");
        }

    @Then("^The response code should be (\\d+)$")
    public void the_response_code_should_be(int ActualStatusCode) throws Throwable {
            int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
            log.info("Status Code Is : " +statusCode);
            Assert.assertEquals(statusCode, ActualStatusCode, "Status code is not 200");
            log.info("Status Code Matched");  }

        @Then("^User should see the following response$")
        public void user_should_see_the_following_response(DataTable JSONResponse) throws Throwable {
            List<List<String>> data = JSONResponse.raw();
            String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
            JSONObject responseJson = new JSONObject(responseString);
            log.info("Response JSON from API ");
            System.out.println("=======================================================================================================");
            System.out.println(responseJson);
            System.out.println("========================================================================================================");
            String Area_Name = TestUtil.getValueByJPath(responseJson, "/data[3]/area_name");
            log.info("Area Name Is "+Area_Name);
            String Area_Code = TestUtil.getValueByJPath(responseJson, "/data[3]/area_code");
            log.info("Area Code Is "+Area_Code);
            String Area_Key = TestUtil.getValueByJPath(responseJson, "/data[3]/area_key");
            log.info("Area Key Is "+Area_Key);
            String Audience_Set_Key = TestUtil.getValueByJPath(responseJson, "/data[3]/audience_set_key");
            log.info("Audience Set Key Is "+Audience_Set_Key);
            Assert.assertEquals(Area_Name, data.get(1).get(1), "Area_name not match");
            log.info("Area Name Matched");
            Assert.assertEquals(Area_Code, data.get(2).get(1), "Area Code not Match");
            log.info("Area Code Matched");
            Assert.assertEquals(Audience_Set_Key, data.get(3).get(1), "audience_set_key Not Match");
            log.info("Audience Set Key Matched");
            }
    @Then("^Verfiy That Header Content Type Is \"([^\"]*)\"$")
    public void verfiy_That_Header_Content_Type_Is(String HeaderContentType) throws Throwable {
        Header[] headersArray =  closebaleHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for(Header header : headersArray){
        allHeaders.put(header.getName(), header.getValue());
        String ContentType=allHeaders.get("Content-Type");
        //log.info("Headers Content Type is : \"+allHeaders.get(\"Content-Type\")");
        Assert.assertEquals(ContentType,HeaderContentType);
        }
       }


    @When("^Perform the POST request$")
    public void perform_the_POST_request() throws Throwable {

       // log.info("POST API calling");
    }

    @Then("^Verfiy That POST Response Code Should Be (\\d+)$")
    public void verfiy_That_POST_Response_Code_Should_Be(int POSTSTATUSCODE) throws Throwable {
        int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,POSTSTATUSCODE);
       // log.info("POST Response Code Matching");
    }

    @When("^User Create A New Area Named As \"([^\"]*)\" and Area Code As \"([^\"]*)\"$")
    public void user_Create_A_New_Area_Named_As_and_Area_Code_As(String CreateAreaName, String CreateAreaCode) throws Throwable {

        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        AreaBO areabO = new AreaBO(1, CreateAreaCode, CreateAreaName, CreateAreaName, "Asingh");
        AreasPojo areaspojo = new AreasPojo(105, "insert", areabO);
        mapper.writeValue(new File("D:\\StellarServiceAPI\\src\\main\\java\\data\\Area.json"), areaspojo);
        String usersJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(areaspojo);
        JSONObject json = new JSONObject(usersJsonString);
        closebaleHttpResponse = restClient.post(url, json.toString(), headerMap);
    }

    @Then("^Verify That A New Data Created As \"([^\"]*)\"$")
    public void verify_That_A_New_Data_Created_As(String CreatedArea) throws Throwable {

    }

    @When("^User Specify The Area Key (\\d+) For Change The Area Name \"([^\"]*)\" To \"([^\"]*)\"$")
    public void user_Specify_The_Area_Key_For_Change_The_Area_Name_To(int AreaKey, String AreaNm, String ChngAreaNm) throws Throwable {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        AreaBO areabo =  new AreaBO(AreaKey,1,ChngAreaNm,ChngAreaNm,"Asingh1");
        AreasPojo areaspojo = new AreasPojo(105, "update", areabo);
        mapper.writeValue(new File("D:\\StellarServiceAPI\\src\\main\\java\\data\\AmendArea.json"), areaspojo);
        String usersJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(areaspojo);
        System.out.println(usersJsonString);
        JSONObject json = new JSONObject(usersJsonString);
        closebaleHttpResponse = restClient.put(url,json.toString(), headerMap); //call the API
        Thread.sleep(5000);

    }


    @When("^User Amend An Area \"([^\"]*)\" To \"([^\"]*)\"$")
    public void user_Amend_An_Area_To(String AreaName, String AreaName2) throws Throwable {


    }
    @When("^User Specify The Area Key \"([^\"]*)\" For Change$")
    public void user_Specify_The_Area_Key_For_Change(String arg1) throws Throwable {

    }

    @When("^Perform the PUT request$")
    public void perform_the_PUT_request() throws Throwable {


    }
    @Then("^Verfiy That PUT Response Code Should Be (\\d+)$")public void verfiy_That_PUT_Response_Code_Should_Be(int arg1) throws Throwable {
        int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
    }


    @Then("^Verify That Area Name \"([^\"]*)\" Should Replace To \"([^\"]*)\"$")
    public void verify_That_Area_Name_Should_Replace_To(String arg1, String arg2) throws Throwable {

    }



    @When("^User Delete An Area As \"([^\"]*)\"$")
    public void user_Delete_An_Area_As(String arg1) throws Throwable {
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

    @When("^Perform the DELETE request$")
    public void perform_the_DELETE_request() throws Throwable {

    }

    @Then("^Verify That Area Name \"([^\"]*)\" Deleted From Area List$")
    public void verify_That_Area_Name_Deleted_From_Area_List(String arg1) throws Throwable {

    }



}
