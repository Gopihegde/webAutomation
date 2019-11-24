package Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class BrowserDriver {
    private WebDriver driver;
    String browserName;
    GsonHelper helper = GsonHelper.getHelper();

    public BrowserDriver(String browserName){
        this.browserName = browserName;

    }


    public WebDriver getDriver() {
        driver = null;

        switch (browserName.toLowerCase()) {

            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty( "user.dir") + "/src/main/resources/chromedriver");
                driver = new ChromeDriver( new ChromeOptions().merge(capabilities( browserName ) ));
                driver.manage().timeouts().pageLoadTimeout( 30 , TimeUnit.SECONDS );
                driver.manage().timeouts().implicitlyWait(  20 , TimeUnit.SECONDS );
                driver.manage().window().maximize();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            default: driver = new ChromeDriver();
        }
        return driver;
    }


    public String getBrowserName() {
        return browserName;
    }


    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    private DesiredCapabilities capabilities( String platform  ){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch ( platform.toLowerCase() ){

            case "chrome" :
                capabilities = capabilities.merge(  new DesiredCapabilities(  helper.deSerialise( "ChromeCaps.json" ) )  );

        }
        return capabilities;

    }


}
