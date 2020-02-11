package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class SpartanTestWithParameters {
    @BeforeClass
    public void setUpClass() {
        RestAssured.baseURI = "http://54.243.9.65:8000/api";
    }

    @Test
    public void helloTest() {

        Response response = when().get("/hello");
        //verify response
        assertEquals(response.statusCode(), 200);
        //verify content type
        assertEquals(response.contentType(), "text/plain;charset=UTF-8");
        //verify we have header name called Date
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //verify content length is 17, asagida string"17" olarak kullanildi
        assertEquals(response.getHeader("Content-Length"), "17");
        //asagidaki integer content length icin
        assertEquals(Integer.parseInt(response.header("Content-Length")), 17);

        //verify body is Hello from Sparta
        assertEquals(response.body().asString(), "Hello from Sparta");
    }

    /*Given accept type is Json
    And Id parameter value is 5
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
     And "Blythe" should be in response payload
  */

    @Test
    public void getSpartanIdPositiveTest() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 5)
                .when().get("/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Blythe"));
    }
    /*  Given accept type is Json
     And Id parameter value is 500
     When user sends GET request to /api/spartans/{id}
     Then response status code should be 404
     And response content-type: application/json;charset=UTF-8
     And Spartan Not Found" message should be in response payload
     */
    @Test
    public void getSpartanIdNegativeTest(){

        Response response = given().accept(ContentType.JSON).and().pathParam("id",500)
                .when().get("/spartans/{id}");

        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Spartan Not Found"));
    }
}
