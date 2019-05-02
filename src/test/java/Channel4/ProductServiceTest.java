package Channel4;

import ServiceAPI.ServiceURL;
import baseST.TestBase;
import client.RestClient;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import util.TestUtil;

import java.util.List;

/**
 * Created by Asit.Singh on 29-04-2019.
 */
public class ProductServiceTest extends TestBase {
    CloseableHttpResponse closeablehttpResponse;
    ServiceURL serviceurl = new ServiceURL();
    @Given("^The Apis1 \\\"([^\\\"]*)\\\" up and running for area service$")
    public void the_Apis1_up_and_running_for_area_service(String apiUrl ) throws Throwable {
        serviceurl.getService(apiUrl);
    }

    @When("^Perform1 the GET request$")
    public void perform1_the_GET_request() throws Throwable {
        serviceurl.getRequest();
    }

    @Then("^The response1 code should be (\\d+)$")
    public void the_response1_code_should_be(int ActualStatusCode) throws Throwable {
        Assert.assertEquals(ActualStatusCode, serviceurl.responseCode());
     }

    @Then("^User should1 see the following response$")
    public void user_should_see_the_following_response(DataTable JSONResponse) throws Throwable {
        serviceurl.verifyResponseData(JSONResponse);
      }
    @When("^User Create1 A New Area Named As \"([^\"]*)\" and Area Code As \"([^\"]*)\"$")
    public void user_Create1_A_New_Area_Named_As_and_Area_Code_As(String AreaCD, String AreaNM) throws Throwable {
        serviceurl.CreateAreaService(AreaCD,AreaNM);
         }

    @When("^Perform1 the POST request$")
    public void perform1_the_POST_request() throws Throwable {
        serviceurl.postRequest();
    }

    @Then("^Verfiy1 That POST Response Code Should Be (\\d+)$")
    public void verfiy1_That_POST_Response_Code_Should_Be(int ResponseCD) throws Throwable {
        Assert.assertEquals(ResponseCD,serviceurl.VerifyPostResponseCode());
    }

    @Then("^Verify(\\d+) That A New Data Created As \"([^\"]*)\"$")
    public void verify_That_A_New_Data_Created_As(int arg1, String arg2) throws Throwable {

    }

    @Then("^Verfiy(\\d+) That Header Content Type Is \"([^\"]*)\"$")
    public void verfiy_That_Header_Content_Type_Is(int arg1, String arg2) throws Throwable {

    }


}
