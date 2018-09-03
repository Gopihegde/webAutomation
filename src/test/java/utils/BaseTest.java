package utils;
import Selenium.CommonMethods;
import Selenium.Constants;
import Selenium.driver.InitDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseTest {
    InitDriver initDriver = new InitDriver("chrome");
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;

    public WebDriver getDriver(){
        return initDriver.getDriver();
    }

    @BeforeTest
    public void setUp(){
    }

    @AfterTest
    public void closeBrowser(){
        getDriver().quit();
    }

    @BeforeSuite
    public void beforeSuite()
    {
         extentReports = new ExtentReports(Constants.REPORT_PATH + "/"+Constants.REPORT_NAME+"."+"html", true); //Provide Desired Report Directory Location and Name
    }

    @BeforeMethod()
    public void beforeMethod(Method method)
    {
        extentTest = extentReports.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
        extentTest.assignAuthor("Gopalkrishna"); //Test Script Author Name
    }

    @AfterMethod
    public void tearDown(ITestResult result, Method method) throws IOException
    {

        if(result.getStatus() == ITestResult.FAILURE)
        {
            String methodName = method.getName();
            CommonMethods.getScreenShot(getDriver(),methodName);

        }
        else extentTest.log(LogStatus.PASS,"test passed");

        extentReports.endTest(extentTest);
        extentReports.flush();
    }
}
