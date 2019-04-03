package Channel4;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Asit.Singh on 03-04-2019.
 */
public class DeleteteAPITest {
    @Test
    public void deleteArea() throws IOException {
        int urlParameters = 137;
        String request = "http://cal-espace-65a:8888/areas";
        URL url = new URL(request + "?" + urlParameters);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setUseCaches (false);
        System.out.println(connection.getResponseCode());
    }
}
