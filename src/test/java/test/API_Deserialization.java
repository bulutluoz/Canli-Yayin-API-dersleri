package test;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class API_Deserialization extends JsonPlaceHolderBaseURL {

    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
    body’e sahip bir PUT request yolladigimizda donen response’in
    response body’sinin asagida verilen ile ayni oldugunu test ediniz

 Request Body

{
"title":"Ahmet",
"body":"Merhaba",
"userId":10,
"id":70
}

Expected Data :

{
"title":"Ahmet",
"body":"Merhaba",
"userId":10,
"id":70
}
*/


    @Test
    public void test01(){

        // 1 - URL ve request body olustur

        specJsonPlace.pathParam("pp1",70);

        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();

        Map<String,Object> reqBodyMap = jsonPlaceHolderTestData.requestBodyMapOlustur();

        //System.out.println(reqBodyMap);

        // 2 - Expected Data olustur

        Map<String,Object> expDataMap = jsonPlaceHolderTestData.requestBodyMapOlustur();

        // 3 - Response kaydet

        Response response = given().
                contentType(ContentType.JSON).
                spec(specJsonPlace).
                when().
                body(reqBodyMap).
                put("{pp1}");

        //response.prettyPrint();

        // 4 - Assertion

        assertEquals(jsonPlaceHolderTestData.basariliStatusKod,response.getStatusCode());

        Map<String,Object> respBodyMap = response.as(HashMap.class);

        assertEquals(expDataMap.get("title"),respBodyMap.get("title"));
        assertEquals(expDataMap.get("body"),respBodyMap.get("body"));
        assertEquals(expDataMap.get("userId"),respBodyMap.get("userId"));
        assertEquals(expDataMap.get("id"),respBodyMap.get("id"));

    }







}
