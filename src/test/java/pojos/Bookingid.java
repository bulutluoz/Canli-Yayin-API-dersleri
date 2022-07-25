package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Bookingid {

    /*
    {
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

    private int bookingid;
    private Booking booking;


}
