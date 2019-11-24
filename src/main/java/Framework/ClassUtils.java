package Framework;
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

    public static  WebElement WaitForElement(WebElement element, WebDriver driver , long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement ele = null;
        try {
            ele = wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            //exception
        }
        return ele;
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
            FileUtils.copyFile(scrFile, new File((Constants.SCREENSHOT_PATH + "/" + methodName)));
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




}
