package Framework;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface CommonDriver< T  extends WebDriver> {

    public T getDriver();



}
