package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListingPage  {

    private WebDriver driver;

    @FindBy( className =  "_3wU53n")
    public WebElement topResult;


    public ListingPage(WebDriver driver ){

        this.driver = driver;
        PageFactory.initElements( driver, this );
    }


    public void clickOnTopObject(){

        topResult.click();
    }
}
