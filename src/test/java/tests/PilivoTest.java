package tests;
import PageObjects.*;
import Selenium.Constants;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.BaseTest;

public class PilivoTest extends BaseTest {
    HomeComponent homeComponent = PageFactory.initElements(getDriver(), HomeComponent.class);
    WebDriverWait wait = new WebDriverWait(getDriver(),30);
    CreateAppComponent createAppComponent = PageFactory.initElements(getDriver(), CreateAppComponent.class);
    CreateNewPageComponent createNewPageComponent = PageFactory.initElements(getDriver(), CreateNewPageComponent.class);
    EmailComponentPage emailComponentPage = PageFactory.initElements(getDriver(),EmailComponentPage.class);
    ExitAppComponent exitAppComponent = PageFactory.initElements(getDriver(), ExitAppComponent.class);
    ConnectorsComponent connectorsComponent = PageFactory.initElements(getDriver(), ConnectorsComponent.class);
    MessageComponent messageComponent = PageFactory.initElements(getDriver(),MessageComponent.class);

  @Test(priority = 1,description = "Open Webpage")
      public void startPage(){
      getDriver().get(Constants.BASE_URL);
      getDriver().manage().window().maximize();
      getDriver().manage().window().fullscreen();
  }

  @Test(priority = 2,description = "Click on Create App",dependsOnMethods = {"startPage"})
    public void clickOnCreateAppMenu(){
      homeComponent.clickOnCreateApp();
  }

  @Test(priority = 3,description = "click on Lets Get started",dependsOnMethods = {"clickOnCreateAppMenu"})
    public void clickOnGetStarted(){
      createAppComponent.letsStart();
  }

  @Test(priority = 4,description = "Creating new Page")
    public void createNewPage() throws Exception {
      createNewPageComponent.addNewPage();
  }
  @Test(priority = 6,description = "check whether created page is present")
     public void checkTabCreated(){
      createNewPageComponent.tabCheck("Gopalkrishna");
  }
  @Test(priority = 5,description = "Click On messaging button")
     public void clickOnMessaging(){
      createNewPageComponent.clickMessaging();
  }
   @Test(priority = 6,description = "Drag Send SMS")
    public void dragSMS(){
      createNewPageComponent.dragSendSmsToConsole();
      messageComponent.dragMessagetoTop();
      messageComponent.inputProvider();
      connectorsComponent.startToSendMessageconnector();
  }
  @Test(priority = 7,description = "Drag Send email to panel")
    public void dragMail(){
      createNewPageComponent.dragMailToConsole();
      emailComponentPage.dragEmail();
      emailComponentPage.inputProvider();
      connectorsComponent.smsToEmailConnector();
  }

  @Test(priority = 8,description = "click on Basic")
  public void clickBasic(){
      exitAppComponent.clickOnBasic();
      exitAppComponent.hangUpToConsole();
      exitAppComponent.dragHangUp(-200,50,"module-4");
      connectorsComponent.smsToExitAppConnector();
      exitAppComponent.hangUpToConsole();
      exitAppComponent.dragHangUp(-100,150,"module-5");
      connectorsComponent.emailToExitAppConnectorPort1();
      exitAppComponent.hangUpToConsole();
      exitAppComponent.dragHangUp(650,100,"module-6");
      connectorsComponent.emailToExitAppConnectorPort2();

  }


}
