package PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAppComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public CreateAppComponent(WebDriver driver)  {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,60);
    }

    private final static Logger logger = Logger.getLogger(CreateAppComponent.class);

    @FindBy(xpath = "//button[@class='ui-state-default ui-corner-all done ui-button']")
      public WebElement LetsGetStarted;

    public void letsStart(){
        if(driver.getCurrentUrl().contains("edit")){
            wait.until(ExpectedConditions.visibilityOf(LetsGetStarted));
            LetsGetStarted.click();
            logger.info("Clicked On GetStarted");
        }
    }


}
