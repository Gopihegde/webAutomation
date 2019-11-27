package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class IosHomeFinder {

    private IOSDriver iosDriver;

    @iOSXCUITFindBy( className = "XCUIElementTypeTextField" )
    public IOSElement searchField;

    @iOSXCUITFindBy( accessibility = "Search")
    public IOSElement enterSearch;

    public IosHomeFinder(AppiumDriver driver){

        this.iosDriver = (IOSDriver) driver;
        PageFactory.initElements( new AppiumFieldDecorator(  driver) , this);
    }

    public boolean launchChecker(){

        return searchField.isDisplayed();
    }

    public void searchLocation( String location ){

        searchField.sendKeys( location );
        enterSearch.click();
    }
}
