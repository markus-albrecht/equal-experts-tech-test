package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.FunctionalTest;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class DeleteBooking extends FunctionalTest {
    CreateBooking createBooking  = new CreateBooking();

    @Test
    public void DeleteLatestBooking() throws InterruptedException {
        String firstName = createBooking.createBookingForToday();
        loadPage();
        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='bookings']/div"));
        java.util.Iterator<WebElement> i = rows.iterator();
        int numberOfRows;
        while (i.hasNext()) {
            WebElement row = i.next();
            numberOfRows = rows.size() + 1;
            String deleteButtonXpath = "/html/body/div[1]/div[2]/div[" + numberOfRows + "]/div[7]/input";
            driver.findElement(By.xpath(deleteButtonXpath)).click();
            loadPage();
        }

        List<WebElement> fn = driver.findElements(By.xpath("//*[contains(text(),'" + firstName + "')]"));
        assertTrue("Booking deleted", !(fn.size() > 0));
    }
}


