package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.groovy.json.FastStringService;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTest {
    String spartanAllUrl = "http://54.243.9.65:8000/api/spartans";

    @Test
    public void viewAllSpartans(){

        Response response=RestAssured.get(spartanAllUrl);

        //print the status code
        System.out.println(response.statusCode());

        //see the response-body-->cok kullanisli degil
        //System.out.println(response.body().asString());

        //pretty print-->bu daha güzel
        System.out.println(response.body().prettyPrint());
    }
    /*When user send GET request to  api/spartans end point
    Then status code must be 200
    And body should contains Orion
      */
    @Test
    public void viewSpartanTest1(){
        Response response = RestAssured.get(spartanAllUrl);

        //verify status code is 200
        Assert.assertEquals(response.statusCode(),200);
        //verify body includes Orion
        Assert.assertTrue(response.body().asString().contains("Orion"));
    }

     /*Given accept type application/json
    When user send GET request to  api/spartans end point
    Then status code must be 200
    And response Content type must be json
    And body should contains Orion
      */

    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanAllUrl);
        //verify status code
        Assert.assertEquals(response.statusCode(),200);
        //verify response content type is json
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
    }

    //  /* Given accept type applicaton/xml
    //        when user send GET request to /api/spartans end point
    //        Then status code must be 200
    //        And Response Content type must be xml
    //        And body should contains Orion
    //     */

    @Test
    public void viewSpartanTest3(){

        //Response response = RestAssured.given().accept(ContentType.XML).when().get(spartanAllUrl);
        //yukaridakinin yerine boyle yazabiliriz
        // hey api get  this url, can you send response in xml format
        Response response=given().accept(ContentType.XML).when().get(spartanAllUrl);

        //verify status code
        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.contentType(),"application/xml;charset=UTF-8");

        //body contains Orion
        assertTrue(response.body().asString().contains("Orion"));

        //Assert.assertEquals(response.statusCode(),200); static Assert import ettigimiz icin
        //yukaridaki gibi sadece assert.Equal ile basladik
        //Assert.assertEquals(response.contentType(),"application/xml,charset=UTF-8");
    }
    @Test
    public void viewSpartanTest4(){
               //yukaridakini böyle de yazabiliriz
                //request starts here
                given().accept(ContentType.XML)
               .when().get(spartanAllUrl)
                //response starts here
               .then().statusCode(200)
               .and().contentType("application/xml;charset=UTF-8");
    }



    @Test
        public void viewSpartanTest5(){
        /*
        Given the accept type Json
        When I send get request to /api/spartans/3
        Then status code must be 200
        And Content type should be json
        and body should contains Fidole
     */
        Response response=given().accept(ContentType.JSON).when().get(spartanAllUrl+"/3");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Fidole"));
     }



    /*TASK2
        Given the accept type XML
        When I send get request to /api/spartans/3
        Then status code must be 406
     */
    @Test
    public void negativeContentType(){
        Response response = given().accept(ContentType.XML)
                .when().get(spartanAllUrl + "/3");
        assertEquals(response.getStatusCode(),406);
    }


}

