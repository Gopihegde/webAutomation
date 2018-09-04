package PageObjects;

import Selenium.CommonMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/* Join  each modules to form the flow.*/
public class ConnectorsComponent {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public ConnectorsComponent(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,20);
    }

   private final static Logger logger = Logger.getLogger(ConnectorsComponent.class);
   //Start to Send SMS connector
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][2]//div[@class='mod-rail mod-north']//div")
        public WebElement dragEndToSMS;
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][1]//div[@class='mod-rail mod-south']//div")
         public WebElement dragStartPointfromStart;

    // Connect from SMS out to EMAIL
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][2]//div[@class='panel-bd']//div[@class='panel-nodes-attached inner']//div[@class='left node-pointer ui-icon ui-icon-triangle-1-w']/preceding-sibling::div[1]")
        public WebElement smsNotSent;
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][3]//div[@class='mod-rail mod-north']//div")
        public WebElement emailIn;

    //Connent Sent SMS from Exit App.
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][2]//div[@class='panel-bd']//div[@class='panel-nodes-attached inner']//div[@class='left node-pointer ui-icon ui-icon-triangle-1-w']/preceding-sibling::div[2]")
        public WebElement smsSent;
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][4]//div[@class='mod-rail mod-north']//div")
        public WebElement exitApp;

    //Connect emailSent to Exit app
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][5]//div[@class='mod-rail mod-north']//div")
        public WebElement exitApptoEmailSent;
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][3]//div[@class='panel-bd']//div[@class='panel-nodes-attached inner']//div[@class='left node-pointer ui-icon ui-icon-triangle-1-w']/preceding-sibling::div[2]")
        public WebElement emailSent;

    //Connect emailNotSent to Exit App
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][6]//div[@class='mod-rail mod-north']//div")
    public WebElement exitApptoEmailNotSent;
    @FindBy(xpath = "//div[@id='tabs-2']//div[contains(@id,'module')][3]//div[@class='panel-bd']//div[@class='panel-nodes-attached inner']//div[@class='left node-pointer ui-icon ui-icon-triangle-1-w']/preceding-sibling::div[1]")
    public WebElement emailNotSent;


    public void startToSendMessageconnector() {
        wait.until(ExpectedConditions.visibilityOf(dragStartPointfromStart));
        logger.info("Started Connecting " + "startToSendMessage" );
        CommonMethods.dragAndDrop(dragStartPointfromStart,dragEndToSMS,driver);
        logger.info("Successfully Connected.");
        }
    public void smsToEmailConnector(){
        logger.info("started connecting SMS to Email");
        CommonMethods.dragAndDrop(smsNotSent,emailIn,driver);
        logger.info("Successfully Connected");
    }
    public void smsToExitAppConnector()
    {
        logger.info("started connecting SMS to ExitAPP");
        CommonMethods.dragAndDrop(smsSent,exitApp,driver);
        logger.info("Successfully Connected");
    }
    public void emailToExitAppConnectorPort1(){
        logger.info("emailto exit app");
        CommonMethods.dragAndDrop(emailSent,exitApptoEmailSent,driver);
        logger.info("Successfully Connected");
    }

    public void emailToExitAppConnectorPort2(){
        logger.info("Started Connecting emailToExitApp");
        CommonMethods.dragAndDrop(emailNotSent,exitApptoEmailNotSent,driver);
        logger.info("Successfully Connected");
    }
}

