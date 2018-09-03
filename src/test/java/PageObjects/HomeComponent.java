package PageObjects;

/* Page objects and Home page functionalities are implemented here.*/
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

    @FindBy(id = "link-create")
    public WebElement Home;

   //Clicks on create app page.
    public void clickOnCreateApp() {
          wait.until(ExpectedConditions.visibilityOf(Home));
          Home.click();

    }

}