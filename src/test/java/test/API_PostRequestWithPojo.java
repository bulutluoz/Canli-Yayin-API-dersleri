package test;

import baseURLDeposu.HerokuappBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Booking;
import pojos.Bookingdates;
import pojos.Bookingid;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_PostRequestWithPojo extends HerokuappBaseURL {

    /*
    https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "Ahmet",
                   "lastname": "Bulut",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2022-09-09",
                       "checkout": "2022-09-21"
                    }
                 }
   Status code is 200
    response body  {
                            "bookingid": 11,
                            "booking": {
                                "firstname": "Ahmet",
                                "lastname": "Bulut",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2022-09-09",
                                    "checkout": "2022-09-21"
                                }
                            }
                         }
     */

    @Test
    public void test01(){

        specHerokuapp.pathParam("pp1","booking");

        Bookingdates bookingdates = new Bookingdates("2022-09-09","2022-09-21");

        Booking booking = new Booking("Ahmet","Bulut",15000,true,bookingdates);

        System.out.println(booking);

        Response response = given().
                contentType(ContentType.JSON).
                spec(specHerokuapp).
                body(booking).
                when().
                post("{pp1}");
        response.prettyPrint();

        Bookingid responseBody = response.as(Bookingid.class);

        assertEquals(200,response.getStatusCode());

        assertEquals(booking.getFirstname(),responseBody.getBooking().getFirstname());
        assertEquals(booking.getLastname(),responseBody.getBooking().getLastname());
        assertEquals(booking.getTotalprice(),responseBody.getBooking().getTotalprice());
        assertEquals(booking.isDepositpaid(),responseBody.getBooking().isDepositpaid());
        assertEquals(booking.getBookingdates().getCheckin(),responseBody.getBooking().getBookingdates().getCheckin());
        assertEquals(booking.getBookingdates().getCheckout(),responseBody.getBooking().getBookingdates().getCheckout());




    }










}
