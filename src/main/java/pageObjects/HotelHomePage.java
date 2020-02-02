package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.List;


public class HotelHomePage extends BasePage {
    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(id="firstname")
    private WebElement firstName;

    @FindBy(id="lastname")
    private WebElement lastName;

    @FindBy(id="totalprice")
    private WebElement price ;

    @FindBy(id="depositpaid")
    private WebElement depositPaid;

    @FindBy(id="checkin")
    private WebElement checkIn;

    @FindBy(id="checkout")
    private WebElement checkOut;

    @FindBy(xpath="//*[@id=\"form\"]/div/div[7]/input")
    private WebElement saveButton;



    public HotelHomePage(WebDriver driver) {
        super(driver);

    }

    public void enterName(String firstName, String lastName){
        this.firstName.clear();
        this.firstName.sendKeys(firstName);

        this.lastName.clear();
        this.lastName.sendKeys(lastName);
    }

    public void enterPrice(String price){
        this.price.clear();
        this.price.sendKeys(price);
    }

    public void selectDepositPaid(String depositPaid) {
        Select dropdown = new Select(driver.findElement(By.id("depositpaid")));
        switch(depositPaid) {
            case "true":
                dropdown.selectByIndex(0);
                break;
            case "false":
                dropdown.selectByIndex(1);
                break;
        }
    }

    public void enterCheckIn(String checkIn) {
        this.checkIn.clear();
        this.checkIn.sendKeys(checkIn);
    }

    public void enterCheckOut(String checkOut) {
        this.checkOut.clear();
        this.checkOut.sendKeys(checkOut);
    }

    public void saveBooking() {
       saveButton.click();
    }

    public boolean isInitialized() {
        return header.isDisplayed();
    }

}




