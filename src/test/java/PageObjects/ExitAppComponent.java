package PageObjects;

/*  This module represents Exit feature from Basics Tab.*/
import Selenium.CommonMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ExitAppComponent {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public ExitAppComponent(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
    }
    private final static Logger logger = Logger.getLogger(ExitAppComponent.class);

    @FindBy(xpath = "//*[@id=\"accordion\"]/div[1]/ul/li[1]/a/span")
    public WebElement hangUp;
    @FindBy(tagName = "a")
    public List<WebElement> Menu;

    public void dragHangUp(int x,int y,String Module){
        WebElement el = driver.findElement(By.id(Module));
        wait.until(ExpectedConditions.visibilityOfAllElements(el));
        CommonMethods.sleep(3000);
        CommonMethods.drandAndrDropByLocation(el,x,y,driver,wait);
    }

    // Click on Basic tab for HANG UP option is here. Drag hang up option to console
    public void hangUpToConsole() {
        wait.until(ExpectedConditions.visibilityOf(hangUp));
        try {
            hangUp.click();
        }catch (Exception e){logger.info(e.toString());}
    }

    public void clickOnBasic(){
        try{
            Thread.sleep(5000);
        } catch (Exception e){
           logger.info(e.toString());
        }
        for (WebElement webElement:Menu) {
            if(webElement.getText().contains("Basic"))
                webElement.click();

        }
    }
}

