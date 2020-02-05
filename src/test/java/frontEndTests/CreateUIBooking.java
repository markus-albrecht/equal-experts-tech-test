package frontEndTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.HotelHomePage;
import utilities.TestHelper;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class CreateUIBooking extends TestHelper {

@Test
    public void createBookingForToday() {
        String firstName = "Rick" + getRandomNumber();
        String lastName = "Sanchez" + getRandomNumber();
        String price = "150.00";
        String checkInDate = getCurrentDate();
        String checkOutDate = getFutureDate(7);
        loadPage("homePage");

        HotelHomePage hotelHomePage = new HotelHomePage(driver);
        assertTrue(hotelHomePage.isInitialized());

        hotelHomePage.enterName(firstName, lastName);
        hotelHomePage.enterPrice(price);
        hotelHomePage.selectDepositPaid("true");
        hotelHomePage.enterCheckIn(checkInDate);
        hotelHomePage.enterCheckOut(checkOutDate);
        hotelHomePage.saveBooking();
        loadPage("homePage");

        List<WebElement> fn = driver.findElements(By.xpath("//*[contains(text(),'" + firstName + "')]"));
        assertTrue("Booking created", fn.size() > 0);
    }

@Test
    public void createBookingWithNoDeposit() {
        String firstName = "Rick" + getRandomNumber();
        String lastName = "Sanchez" + getRandomNumber();
        String price = "150.00";
        String checkInDate = getCurrentDate();
        String checkOutDate = getFutureDate(7);
        loadPage("homePage");

        HotelHomePage hotelHomePage = new HotelHomePage(driver);
        assertTrue(hotelHomePage.isInitialized());

        hotelHomePage.enterName(firstName, lastName);
        hotelHomePage.enterPrice(price);
        hotelHomePage.selectDepositPaid("false");
        hotelHomePage.enterCheckIn(checkInDate);
        hotelHomePage.enterCheckOut(checkOutDate);
        hotelHomePage.saveBooking();
        loadPage("homePage");

        List<WebElement> fn = driver.findElements(By.xpath("//*[contains(text(),'" + firstName + "')]"));
        assertTrue("Booking created", fn.size() > 0);
    }

    @Test
    public void createBookingWithPriceWithDecimal() {
        String firstName = "Rick" + getRandomNumber();
        String lastName = "Sanchez" + getRandomNumber();
        String price = "150.99";
        String checkInDate = getCurrentDate();
        String checkOutDate = getFutureDate(7);
        loadPage("homePage");

        HotelHomePage hotelHomePage = new HotelHomePage(driver);
        assertTrue(hotelHomePage.isInitialized());

        hotelHomePage.enterName(firstName, lastName);
        hotelHomePage.enterPrice(price);
        hotelHomePage.selectDepositPaid("true");
        hotelHomePage.enterCheckIn(checkInDate);
        hotelHomePage.enterCheckOut(checkOutDate);
        hotelHomePage.saveBooking();
        loadPage("homePage");

        List<WebElement> fn = driver.findElements(By.xpath("//*[contains(text(),'" + firstName + "')]"));
        assertTrue("Booking created", fn.size() > 0);
    }

}
