package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import utilities.TestHelper;
import utilities.ReadPropertiesFile;

import static org.junit.Assert.*;
import static utilities.TestHelper.createBookingToDelete;

public class DeleteAPIBooking {

    private String url;
    private Header contentType;
    private Header accept;
    private String bookingId;
    private RequestSpecification httpRequest;
    private Response response;
    @Test
    public void deleteLatestBooking() {
        this.url = ReadPropertiesFile.getUrl("booking");
        this.bookingId = createBookingToDelete();

        //Delete the booking
        this.contentType = new Header("authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
        this.accept = new Header("Accept", "*/*");
        this.httpRequest = RestAssured.given();
        httpRequest.header(contentType);
        httpRequest.header(accept);
        this.response = httpRequest.delete(url+ "/" + bookingId);

        assertEquals(201, response.statusCode());

        //Confirm the booking is deleted
        this.url = ReadPropertiesFile.getUrl("booking");
        this.contentType = new Header("Content-Type", "application/json; charset=utf-8");
        this.httpRequest = RestAssured.given();
        httpRequest.header(contentType);
        httpRequest.header(accept);

        this.response = httpRequest.get(url+ "/" + bookingId);
        String responseBody = this.response.asString();
        assertEquals(404, response.statusCode());
        assertTrue(responseBody.contains("Not Found"));
        System.out.println("Booking successfully deleted.");

    }

}
