package frontEndTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestHelper;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class DeleteUIBooking extends TestHelper {
    CreateUIBooking createUIBooking = new CreateUIBooking();

    @Test
    //This test currently fails when run with CHrome
    public void DeleteLatestBooking() throws InterruptedException {
        createUIBooking.createBookingForToday();
        Integer numberOfRows = getNumberOfBookings();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[" + numberOfRows
                + "]/div[1]/p")));
        String deleteButtonXpath = "/html/body/div[1]/div[2]/div[" + numberOfRows + "]/div[7]/input";
        driver.findElement(By.xpath(deleteButtonXpath)).click();
        loadPage("homePage");
        List<WebElement> fn =  driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[" + numberOfRows
            + "]/div[1]/p"));

        assertTrue("Booking deleted", (fn.size() == 0));
    }


}


