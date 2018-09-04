package PageObjects;

/*  Source code for Email field and actions on email field. */
import Selenium.CommonMethods;
import Selenium.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.InputFeeder;

public class EmailComponentPage implements InputFeeder{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public EmailComponentPage(WebDriver driver){
       this.driver = driver;
       wait = new WebDriverWait(driver,30);
    }
    @FindBy(css = "input[name='smtp_url']")
        public WebElement smtpHost;
    @FindBy(css = "input[name='port']")
        public WebElement port;
    @FindBy(css = "input[name='username']")
        public WebElement username;
    @FindBy(css = "input[name='password']")
        public WebElement password;
    @FindBy(css = "textarea[name='from_constant']")
        public WebElement sender;
    @FindBy(css = "textarea[name='to_constant']")
        public WebElement receiver;
    @FindBy(css = "textarea[name='subject_constant']")
        public WebElement subject;
    //@FindBy(css = "textarea[name='message_phrase[]']")
   @FindBy(xpath = "//*[@id=\"module-3\"]/div[1]/div[3]/div/div[3]/div/div/table/tbody/tr/td[1]/div/textarea")
        public WebElement messageText;
   @FindBy(css = "#module-2")
        public WebElement email1;

    private final static Logger logger = Logger.getLogger(EmailComponentPage.class);

     //Fill the send Email panel.
     @Override
        public void inputProvider() {
         smtpHost.sendKeys(Constants.SMTPHOST);
         port.sendKeys(Constants.PORT);
         username.sendKeys(Constants.USERNAME);
         password.sendKeys(Constants.PASSWORD);
         sender.sendKeys(Constants.SENDER);
         receiver.sendKeys(Constants.RECEIVER);
         subject.sendKeys(Constants.SUBJECT);
         wait.until(ExpectedConditions.visibilityOfAllElements(messageText));
         messageText.click();
         messageText.clear();
         try {
             messageText.sendKeys(Constants.MAILBODY);
         }catch (Exception e) {logger.error("Element not visible" + e.toString());}
         }
         //Drags email to its right
      public void dragEmail(){
          WebElement email = driver.findElement(By.xpath("//*[@id=\"module-3\"]/div[1]/div[3]/div/div[2]/div[1]/div[1]"));
          wait.until(ExpectedConditions.visibilityOfAllElements(email));
          CommonMethods.sleep(5000);
          Actions actions = new Actions(driver);
          actions.moveToElement(email).perform();
          CommonMethods.drandAndrDropByLocation(email,300,70,driver,wait);
      }

}
