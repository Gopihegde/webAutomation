package utils;

import Framework.ClassUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestListener implements ITestListener , ISuiteListener {

    WebDriver driver;
    Logger logger = Logger.getLogger( this.getClass().getSimpleName() );
    ExtentReports reports = new ExtentReports(  );
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {

    }


    @Override
    public void onTestStart(ITestResult result) {

    }


    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.log(Status.FAIL , result.getTestName() );
        Class clazz = result.getTestClass().getRealClass().getSuperclass();
        Field field = null;
        try {
            field = clazz.getDeclaredField("driver");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        field.setAccessible(true);

        try {
            driver = (WebDriver) field.get(result.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ClassUtils.getScreenShot(driver, result.getMethod().getMethodName().trim());
        logger.log( Level.INFO , result.getMethod().getDescription() + "  " + " Test is Failed"  );


    }


    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS , result.getTestName() );
    }

    @Override
    public void onFinish(ITestContext context) {

    }


    @Override
    public void onStart(ISuite suite) {

        reports.attachReporter( new ExtentHtmlReporter(  System.getProperty( "user.dir" ) + "/" +suite.getName()+ ".html" ));
        extentTest = reports.createTest(  suite.getName() );
    }


    @Override
    public void onFinish(ISuite suite) {

    }
}
