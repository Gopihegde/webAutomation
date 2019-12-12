package Framework;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClassUtils {
    protected WebDriver driver;
    protected WebDriverWait wait;
    static GregorianCalendar gregorianCalendar;

    public ClassUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
    }

    public boolean isElementExist(WebElement element) {
        try {
            if (element.isDisplayed())
                return true;
        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
        ;
        return false;
    }

    public  static  <T extends WebElement>  T  WaitForElement(T t, WebDriver driver , long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement ele = null;
        try {
            ele = wait.until(ExpectedConditions.visibilityOf(t));
        } catch (Exception e) {
            //exception
        }
        return (T) ele;
    }

    public static void dragAndDrop(WebElement source, WebElement target, WebDriver driver) {
        (new Actions(driver)).clickAndHold(source).moveToElement(target).release().build().perform();
    }

    public static void drandAndrDropByLocation(WebElement source, int x, int y, WebDriver driver,WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfAllElements(source));
        new Actions(driver).dragAndDropBy(source, x, y).pause(Duration.ofSeconds(2)).build().perform();
    }

    public static void mouseHover(WebElement element, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(element));
        new Actions(driver).moveToElement(element).perform();
    }

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        }catch (Exception e){ e.printStackTrace();}
    }
    public static void getScreenShot(WebDriver driver, String methodName) {
        int captureCount = 0;
        gregorianCalendar = new GregorianCalendar();
        int date = gregorianCalendar.get(Calendar.DATE);
        int hour = gregorianCalendar.get(Calendar.HOUR);
        int minute = gregorianCalendar.get(Calendar.MINUTE);
        int second = gregorianCalendar.get(Calendar.SECOND);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File((FrameworkConstants.SCREENSHOT_PATH + "/" + methodName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getWebPage( String url ){

        driver.get(  url );
    }


    public void scroll(  float pixel, WebDriver driver){
        JavascriptExecutor executor = ( JavascriptExecutor ) driver;
        executor.executeScript("(browserX, browserY) => window.scrollTo(browserX, browserY)", 0, pixel);

    }


    public static void scroll(SwipeDirection direction, AppiumDriver driver, double steps) {
        int startX;
        int endX ;
        int startY;
        int endY;
        while (steps > 0) {
            try {
                Dimension size = driver.manage().window().getSize();
                startY = (int) (size.height * 0.80);
                endY = (int) (size.height * 0.20);
                startX = (int) (size.width * 0.50);

                System.out.printf( "%s x is %s y is" , startY , endY );


                switch (direction) {
                    case UP:
                        new TouchAction(driver).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                                .moveTo(PointOption.point(startX, endY)).release().perform();
                        steps--;
                        break;
                    case DOWN:
                        new TouchAction(driver).press(PointOption.point(startX, endY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                                .moveTo(PointOption.point(startX, startY)).release().perform();
                        steps--;
                        break;
                    case LEFT:
                        startY = (int) (size.height / 2);
                        startX = (int) (size.width * 0.05);
                        endX = (int) (size.width * 0.90);
                        new TouchAction(driver).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
                                .moveTo(PointOption.point(endX, startY)).release().perform();
                        steps--;
                    case RIGHT:
                        startY = (int) (size.height / 2);
                        startX = (int) (size.width * 0.90);
                        endX = (int) (size.width * 0.05);
                        new TouchAction(driver).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
                                .moveTo(PointOption.point(endX, startY)).release().perform();
                        steps--;
                    default:

                }
            } catch (Exception e) {

            }
        }

    }


    public static <T extends WebDriver> WebDriverWait getWait( T driver , long time ) {

        return new WebDriverWait( driver , time );
    }


}
