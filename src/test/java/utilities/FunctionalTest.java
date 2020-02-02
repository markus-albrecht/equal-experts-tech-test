package utilities;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FunctionalTest {
    public static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
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

    //If there where multiple pages this method should be refactored to take an argument.
    public void loadPage() {
        String url = ReadPropertiesFile.getUrl("url");
        driver.get(url);
    }


    public String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public String getFutureDate(int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date futureDate = new DateTime(date).plusDays(days).toDate();
        String formattedDate = dateFormat.format(futureDate);
        System.out.println(date);

        return formattedDate;
    }

    public Integer getRandomNumber() {
        Random rand = new Random();

        return rand.nextInt(1000);
    }

}




