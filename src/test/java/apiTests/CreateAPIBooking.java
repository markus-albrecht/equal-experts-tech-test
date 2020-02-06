package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Test;
import utilities.TestHelper;
import utilities.ReadPropertiesFile;

import static org.junit.Assert.*;
import static utilities.TestHelper.getRandomNumber;

public class CreateAPIBooking {
    private String url;
    private Header contentType;
    private Header accept;
    private String bookingId;
    private RequestSpecification httpRequest;
    private Response response;

    @Test
    public void createBookingForToday() {
        String firstName = "Rick" + getRandomNumber();
        String lastName = "Sanchez" + getRandomNumber();
        String price = "150";
        Boolean deposit = true;
        String checkInDate = TestHelper.getCurrentDate();
        String checkOutDate = TestHelper.getFutureDate(7);

        //Create the booking.
        this.url = ReadPropertiesFile.getUrl("booking");
        String requestBody = "{\"firstname\":\"" + firstName +
                            "\",\"lastname\":\"" + lastName +
                            "\",\"totalprice\":\"" + price +
                            "\",\"depositpaid\":\"" + deposit +
                            "\",\"bookingdates\":{\"checkin\":\"" + checkInDate +
                            "\",\"checkout\":\"" + checkOutDate + "\"}}";
        this.contentType = new Header("Content-Type", "application/json; charset=UTF-8");
        this.accept = new Header("Accept", "application/json");
        this.httpRequest = RestAssured.given();
        httpRequest.header(contentType);
        httpRequest.header(accept);
        httpRequest.body(requestBody);
        this.response = httpRequest.post(url);

        String id = this.response.jsonPath().getString("bookingid");
        String fn = this.response.jsonPath().getString("booking.firstname");
        String ln = this.response.jsonPath().getString("booking.lastname");
        String tp = this.response.jsonPath().getString("booking.totalprice");
        String dp = this.response.jsonPath().getString("booking.depositpaid");
        String ci = this.response.jsonPath().getString("booking.bookingdates.checkin");
        String co = this.response.jsonPath().getString("booking.bookingdates.checkout");

        assertEquals(200, response.statusCode());
        assertFalse(id.isEmpty());
        assertTrue(fn.equals(firstName));
        assertTrue(ln.equals(lastName));
        assertTrue(tp.equals(price));
        assertTrue(dp.equals(deposit.toString()));
        assertTrue(ci.equals(checkInDate));
        assertTrue(co.equals(checkOutDate));
        System.out.println("BookingId is " + id);

        //Confirm the booking is created
        this.url = ReadPropertiesFile.getUrl("booking");
        this.contentType = new Header("Content-Type", "application/json; charset=utf-8");
        this.httpRequest = RestAssured.given();
        httpRequest.header(contentType);
        httpRequest.header(accept);

        this.response = httpRequest.get(url+ "/" + id);
        String responseBody = this.response.asString();
        assertEquals(200, response.statusCode());
        assertTrue(responseBody.contains(firstName));
        System.out.println("Booking successfully created.");

    }

}
