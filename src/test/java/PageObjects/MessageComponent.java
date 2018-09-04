package PageObjects;

import Selenium.CommonMethods;
import Selenium.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.InputFeeder;

import java.util.List;

public class MessageComponent implements InputFeeder {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public MessageComponent(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,20);
    }

    @FindBy(tagName = "a")
    public List<WebElement> Menu;
    @FindBy(css = "#module-0")
    public WebElement startModule;
    @FindBy(css = "#module-1")
    public WebElement sendMessage;
    @FindBy(css = "textarea[name='message_phrase[]']")
    public WebElement enterMessage;
    @FindBy(css = "textarea[name='phone_constant']")
    public WebElement enterNumber;

    private final static Logger logger = Logger.getLogger(MessageComponent.class);

    @Override
    public void inputProvider() {
        try {
            enterNumber.sendKeys(Constants.NUMBER);
            enterMessage.sendKeys(Constants.TEXT);
        } catch (Exception e) { logger.info(e.toString());}
    }

    public void clickMessaging(){
        for (WebElement webElement:Menu) {
            if(webElement.getText().contains("Messaging"))
                webElement.click();       }
    }
    public void dragMessagetoTop(){
        wait.until(ExpectedConditions.visibilityOf(sendMessage));
        wait.until(ExpectedConditions.visibilityOfAllElements(enterNumber));
        try{
            WebElement element = driver.findElement(By.id("module-2"));
            CommonMethods.sleep(2000);
            CommonMethods.drandAndrDropByLocation(element,0,-50,driver,wait);
        }catch (Exception e) { logger.info(e.toString());}

    }
}
