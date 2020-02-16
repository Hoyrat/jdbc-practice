package Day6_gson;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class POJO_deserialize {

    @Test
    public void oneSpartanWithPOJO(){
        Response response = given()
                .accept(ContentType.JSON).pathParam("id",15)
                .when().get("http://54.243.9.65:8000/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        //Json to my custom class (POJO)-> deserielize json to pojo
        //taking response and converting to spartan object
        Spartan spartan15=response.body().as(Spartan.class);


        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        assertEquals(spartan15.getId(),15);
        assertEquals(spartan15.getName(),"Meta");
        assertEquals(spartan15.getGender(),"Female");
        assertEquals(spartan15.getPhone(),new Long(1938695106));

    }

    @Test
    public void regionWithPojo(){
        //request
        Response response = get("http://54.161.128.36:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);
        //BREAK UNTIL 12 :25

        //JSON to Region class
        //Deserizaliton
        Region regions = response.body().as(Region.class);

        System.out.println(regions.getCount());

        List<Item> regionList =  regions.getItems();
        System.out.println(regionList.get(0).getRegionName());
        System.out.println("regionList.get(1).getRegionId() = " + regionList.get(1).getRegionId());

        System.out.println(regions.getItems().get(0).getRegionName());

        for (Item item : regionList) {
            System.out.println(item.getRegionName());
        }


        }
   //asagidaki örnekte API kullanilmadi, sadeec bir file/input kullandik
    //only json to java object örnegi. with API we use as() method-->as(Region.class);
    @Test
    public void GsonExample(){

        //cerating gson object
        //it is like convertor-->java to jason, json to java
        Gson gson = new Gson();
        //De-Serialize and serialize with gson object
        //Deseriailze -->JSON TO Java Object

        //we dont coonect toAPI, no API is involved, we use ony one json input/file
        String myjson = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";
       //converting json to pojo(Spartan.class)
        Spartan spartan15=gson.fromJson(myjson,Spartan.class);

        System.out.println(("spartan15.getName() : "+spartan15.getName()));
        System.out.println(("spartan15.getPhone() : "+spartan15.getPhone()));

        //-----------------------SERIALIZATION-------------
        //Java object to JSON
        Spartan spartanEU = new Spartan(10,"Mike","Male",5714788554L);
        //it will take spartan eu information and convert to json
        String jsonSpartanEU = gson.toJson(spartanEU);

        System.out.println("jsonSpartanEU = " + jsonSpartanEU);


    }

}







