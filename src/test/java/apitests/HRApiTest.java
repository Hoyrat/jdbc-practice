package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import  static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class HRApiTest {
    /*
        Create a new class HRApiTests
        createa a @Test getALLRegionsTest
        send a get request to AllRegions API endpoint
        -print status code
        -print content type
        -pretty print response JSON
        verify that status code is 200
        verify that content type is "application/json"
        verify that json response body contains "Americas"
        verify that json response body contains "Europe"
        *try to use static imports for both RestAssured and testng
        *store response inside the Response type variable
     */
    String hrbaseurl="http://54.243.9.65:1000/ords/hr";
    @Test
    public void  getAllRegionsTest(){
        //send a get request to AllRegions API endpoint
        Response response=when().get(hrbaseurl+"/regions");
        //print status code
        System.out.println("Status code: "+response.statusCode());
        //print content type
        System.out.println("Content type: "+response.contentType());
        //pretty print response JSON
        System.out.println("Pretty print: "+response.prettyPrint());

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Europe"));
        assertTrue(response.asString().contains("Americas"));

    }

}
