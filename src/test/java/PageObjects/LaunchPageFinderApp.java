package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LaunchPageFinderApp {

    AndroidDriver driver;

    @AndroidFindBy( id = "etSearch" )
    public AndroidElement searchField;

    @AndroidFindBy( uiAutomator = "new UiSelector().text(\"Search\")")
    public AndroidElement enterSearch;




    public LaunchPageFinderApp(AppiumDriver<AndroidElement> driver){

        this.driver = (AndroidDriver) driver;
        PageFactory.initElements( new AppiumFieldDecorator(driver), this);
    }

    public boolean launchChecker(){

        return searchField.isDisplayed();
    }

    public void searchLocation( String location ){

        searchField.sendKeys( location );
        enterSearch.click();
    }
}
