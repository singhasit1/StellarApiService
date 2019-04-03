package Channel4;

import baseST.TestBase;
import client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.AreaBO;
import data.AreasPojo;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
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
   // public static final Logger log = Logger.getLogger(AreaServiceTest.class.getName());

    @Given("^The Apis \\\"([^\\\"]*)\\\" up and running for area service$")
    public void the_Apis_up_and_running_for_area_service(String apiUrl ) throws Throwable {
        testBase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        url = serviceUrl + apiUrl;
       // log.info("API Service URL"+url);
    }
        @When("^Perform the GET request$")
        public void perform_the_GET_request() throws Throwable {
            restClient = new RestClient();
            closebaleHttpResponse = restClient.get(url);
         //   log.info("Perform the GET request");
        }

    @Then("^The response code should be (\\d+)$")
    public void the_response_code_should_be(int ActualStatusCode) throws Throwable {
            int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
         //   log.info("Status Code IS"+statusCode);
            Assert.assertEquals(statusCode, ActualStatusCode, "Status code is not 200");
        }

        @Then("^User should see the following response$")
        public void user_should_see_the_following_response(DataTable JSONResponse) throws Throwable {
            List<List<String>> data = JSONResponse.raw();
            String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
            JSONObject responseJson = new JSONObject(responseString);
            //log.info("Response JSON from API"+responseJson);
            String Area_Name = TestUtil.getValueByJPath(responseJson, "/data[0]/area_name");
           // log.info("Area Name Is "+Area_Name);
            String Area_Code = TestUtil.getValueByJPath(responseJson, "/data[0]/area_code");
           // log.info("Area Code Is"+Area_Code);
            String Area_Key = TestUtil.getValueByJPath(responseJson, "/data[0]/area_key");
            //log.info("Area Key Is "+Area_Key);
            String Audience_Set_Key = TestUtil.getValueByJPath(responseJson, "/data[0]/audience_set_key");
         //   log.info("Audience Set Key Is "+Audience_Set_Key);
            Assert.assertEquals(Area_Name, data.get(1).get(1), "Area_name not match");
           // log.info("Area Name Matched");
            Assert.assertEquals(Area_Code, data.get(2).get(1), "Area Code not Match");
           // log.info("Area Code Matched");
            Assert.assertEquals(Audience_Set_Key, data.get(3).get(1), "audience_set_key Not Match");
            //log.info("Audience Set Key Matched");
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
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        AreaBO areabO = new AreaBO(1, "SE", "South England", "Test description", "Asingh");
        AreasPojo areaspojo = new AreasPojo(105, "insert", areabO);
        mapper.writeValue(new File("D:\\StellarServiceAPI\\src\\main\\java\\data\\Area.json"), areaspojo);
        String usersJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(areaspojo);
        JSONObject json = new JSONObject(usersJsonString);
        closebaleHttpResponse = restClient.post(url, json.toString(), headerMap);
       // log.info("POST API calling");
    }

    @Then("^Verfiy That POST Response Code Should Be (\\d+)$")
    public void verfiy_That_POST_Response_Code_Should_Be(int POSTSTATUSCODE) throws Throwable {
        int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,POSTSTATUSCODE);
       // log.info("POST Response Code Matching");
    }

    @When("^User Create A New Area As \"([^\"]*)\"$")
    public void user_Create_A_New_Area_As(String CreatedArea) throws Throwable {

       // log.info("Created a new area as"+CreatedArea);
    }

    @Then("^Verify That A New Data Created As \"([^\"]*)\"$")
    public void verify_That_A_New_Data_Created_As(String CreatedArea) throws Throwable {

       // log.info("Created area Verified");
    }

    @When("^User Delete An Area As \"([^\"]*)\"$")
    public void user_Delete_An_Area_As(String arg1) throws Throwable {

    }

    @When("^Perform the DELETE request$")
    public void perform_the_DELETE_request() throws Throwable {

    }

    @Then("^Verify That Area Name \"([^\"]*)\" Deleted From Area List$")
    public void verify_That_Area_Name_Deleted_From_Area_List(String arg1) throws Throwable {

    }



}
