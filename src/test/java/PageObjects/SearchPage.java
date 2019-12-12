package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    private  AppiumDriver driver ;


    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Search ONE for...\")")
    protected AndroidElement searchFor;

    @AndroidFindBy( uiAutomator = "new UiSelector().textContains(\"Athletes\")" )
    protected AndroidElement athletes;

    @AndroidFindBy( xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView[1]")
    protected AndroidElement topSearchResult;

    @AndroidFindBy( xpath = "")
    protected AndroidElement nickName;

    public void SearchPage( AppiumDriver driver  ){

        this.driver = driver;
        PageFactory.initElements( driver , this );
    }

}
