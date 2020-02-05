package utilities;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestHelper {
    public static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        String browserName = System.getenv("browserName");
        switch(browserName.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    public void loadPage(String page) {
        String url = ReadPropertiesFile.getUrl(page);
        driver.get(url);
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public static String getFutureDate(int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date futureDate = new DateTime(date).plusDays(days).toDate();
        String formattedDate = dateFormat.format(futureDate);
        System.out.println(date);

        return formattedDate;
    }

    public static Integer getRandomNumber() {
        Random rand = new Random();

        return rand.nextInt(1000);
    }

    public Integer getNumberOfBookings() {
        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='bookings']/div"));
        java.util.Iterator<WebElement> i = rows.iterator();
        int numberOfRows = rows.size();

        return numberOfRows;
    }

    public static String createBookingToDelete() {
        String firstName = "Rick" + TestHelper.getRandomNumber();
        String lastName = "Sanchez" + TestHelper.getRandomNumber();
        String price = "150";
        Boolean deposit = true;
        String checkInDate = TestHelper.getCurrentDate();
        String checkOutDate = TestHelper.getFutureDate(7);

        String url = ReadPropertiesFile.getUrl("booking");
        String requestBody = "{\"firstname\":\"" + firstName +
                "\",\"lastname\":\"" + lastName +
                "\",\"totalprice\":\"" + price +
                "\",\"depositpaid\":\"" + deposit +
                "\",\"bookingdates\":{\"checkin\":\"" + checkInDate +
                "\",\"checkout\":\"" + checkOutDate + "\"}}";
        Header contentType = new Header("Content-Type", "application/json; charset=UTF-8");
        Header accept = new Header("Accept", "application/json");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header(contentType);
        httpRequest.header(accept);
        httpRequest.body(requestBody);

        Response response = httpRequest.post(url);
        String bookingId = response.jsonPath().getString("bookingid");

        return bookingId;
    }


}




