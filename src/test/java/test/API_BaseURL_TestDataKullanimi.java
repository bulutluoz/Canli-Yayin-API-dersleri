package test;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class API_BaseURL_TestDataKullanimi extends JsonPlaceHolderBaseURL {
/*

 https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
  request yolladigimizda donen response’in status kodunun 200 ve
  response body’sinin asagida verilen ile ayni oldugunu test ediniz


   Response body :
{
"userId":3,
"id":22,
"title":"dolor sint quo a velit explicabo quia nam",
"body":"eos qui et ipsum ipsam suscipit aut
sed omnis non odio
expedita earum mollitia molestiae aut atque rem suscipit
nam impedit esse"
}

     */


    @Test
    public void test01(){
        // 1 - URL ve Request Body olustur

        specJsonPlace.pathParam("pp1", 22);

       // 2 - Expected Data olustur
        JsonPlaceHolderTestData jsonPlaceHolder = new JsonPlaceHolderTestData();

        JSONObject expBody = jsonPlaceHolder.expectedDataOlustur();

        // 3 - Response kaydet

        Response response = given().spec(specJsonPlace).when().get("{pp1}");

        // 4 - Assertion

        JsonPath respJSPath = response.jsonPath();

        assertEquals(jsonPlaceHolder.basariliStatusKod,response.getStatusCode());

        assertEquals(expBody.getInt("userId"),respJSPath.getInt("userId"));
        assertEquals(expBody.getInt("id"),respJSPath.getInt("id"));
        assertEquals(expBody.getString("title"),respJSPath.getString("title"));
        assertEquals(expBody.getString("body"),respJSPath.getString("body"));










    }








}
