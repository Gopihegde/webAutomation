package PageObjects;

/* Page objects and Home page functionalities are implemented here.*/
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class HomeComponent {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public HomeComponent(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
    }
    private final static Logger logger = Logger.getLogger(HomeComponent.class);

    @FindBy(id = "link-create")
    public WebElement Home;

   //Clicks on create app page.
    public void clickOnCreateApp() {
         try {
             wait.until(ExpectedConditions.visibilityOf(Home));
         } catch (Exception e){ logger.info(e.toString());}
          Home.click();

    }

}