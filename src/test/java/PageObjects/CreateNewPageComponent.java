package PageObjects;

import Selenium.CommonMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


public class CreateNewPageComponent {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public CreateNewPageComponent(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
    }

    @FindBy(id = "add-page")
    public WebElement addPage;
    @FindBy(xpath = "//*[@id=\"accordion\"]/h3[4]/a")
    public WebElement Messaging;
    @FindBy(id = "ui-dialog-title-create-dialog")
    public WebElement alertPageText;
    @FindBy(xpath = "//*[@id=\"create-dialog\"]/form/p/input")
    public WebElement inputName;
    @FindBy(xpath = "//button[@class='ui-state-default ui-corner-all ui-button']")
    public WebElement createButton;
    @FindBy(xpath = "//a[@class='tab-label']")
    public WebElement pageNames;
    @FindBy(tagName = "a")
    public List<WebElement> Menu;
    @FindBy(xpath = "//*[@id=\"accordion\"]/div[4]/ul/li[3]/a/span")
    public WebElement addSendSMS;
    @FindBy(css = "#module-1")
    public WebElement dragSMS;
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][2]//div[@class='mod-rail mod-north']//div")
    public WebElement dragEndToSMS;
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][1]//div[@class='mod-rail mod-south']//div")
    public WebElement dragStartPointfromStart;
    @FindBy(xpath = "//*[@id=\"accordion\"]/div[4]/ul/li[2]/a/span")
    public WebElement sendEmailfromMenu;
    @FindBy(xpath = "//*[@id=\"module-2\"]/div[1]/div[2]/div/div[2]")
    public WebElement MoveEmail;
    @FindBy(css = "#module-2")
    public WebElement mailModule;
    @FindBy(css = "textarea[name='phone_constant']")
    public WebElement inputPhoneNumber;

    final static Logger logger = Logger.getLogger(CreateNewPageComponent.class);

    // Create New page from here.
    public void addNewPage()  {
        wait.until(ExpectedConditions.visibilityOf(addPage));
        addPage.click();
        logger.info("Clicked on Add new Page");
        wait.until(ExpectedConditions.visibilityOf(alertPageText));
        if (alertPageText.getText().contains("New Page")) {
            wait.until(ExpectedConditions.visibilityOf(inputName));
            inputName.sendKeys("Gopalkrishna");
            if (createButton.isEnabled())
                createButton.click();
        }
    }

    // CLick on Message button is implemented here.
    public void clickMessaging() {
        for (WebElement webElement : Menu) {
            if (webElement.getText().contains("Messaging"))
                webElement.click();
        }
    }

    //This fundtion checks if newly created page/tab found or not. return boolean.
    public boolean tabCheck(String name) {
        if (pageNames.getText().contains(name))
            return true;
        return false;
    }
    // This function drags SendSMS menu to panel.
    public void dragSendSmsToConsole() {
        wait.until(ExpectedConditions.visibilityOf(addSendSMS));
        addSendSMS.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("#module-1"))));
    }

    //method to drag mail from menu to panel.
    public void dragMailToConsole() {
        wait.until(ExpectedConditions.visibilityOf(sendEmailfromMenu));
        try{sendEmailfromMenu.click();}
        catch (NoSuchElementException e){ logger.info("Exception Occured" + e.toString());}
        wait.until(ExpectedConditions.visibilityOf(sendEmailfromMenu));

    }

}
