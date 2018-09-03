package Selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class InitDriver {
    ChromeDriver chromeDriver;
    FirefoxDriver firefoxDriver;
    String BrowserName;

    public InitDriver(String BrowserName){
        this.BrowserName = BrowserName;

    }


    public WebDriver getDriver() {

        switch (BrowserName) {

            case "chrome":
                if (chromeDriver == null)
                    chromeDriver = new ChromeDriver();
                    chromeDriver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
                    chromeDriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
                return chromeDriver;
            case "firefox":
                if (firefoxDriver == null)
                    firefoxDriver = new FirefoxDriver();
                    firefoxDriver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
                    firefoxDriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
                return firefoxDriver;

        }
        return null;
    }
}
