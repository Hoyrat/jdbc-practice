package HomeWork;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Q2 {
    @BeforeClass
    public void setUpClass(){
        baseURI = ConfigurationReader.get("hrapi.uri");
    }
    /*
        - Given accept type is Json
        - Query param value - q={"department_id":80}
        - When users sends request to /employees
        - Then status code is 200
        - And Content - Type is Json
        - And all job_ids start with 'SA'
        - And all department_ids are 80
        - Count is 25 */

    @Test

    public void departmentIdTest(){
        Response response=given().accept(ContentType.JSON).and()
                .queryParam("q","{\"department_id\":80}")
                .get("/employees");

       assertEquals(response.statusCode(),200);
       assertEquals(response.contentType(),"application/json");

        //assert that all job_id s starts with 'SA'
        List<String> jobIDs = response.path("items.job_id");

        for (String jobID : jobIDs) {
            System.out.println("jobID = " + jobID);
            assertTrue(jobID.contains("SA"));
        }
        //- And all department_ids are 80
        List<Integer>allDepIDs=response.path("items.department_id");
        for (int allDepID : allDepIDs) {
            System.out.println("DepId: "+allDepID);
            assertEquals(allDepID,80);
             }
        //- Count is 25 */
        System.out.println(response.path("count").toString());

    }



}
