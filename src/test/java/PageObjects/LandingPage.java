package PageObjects;

import Framework.ClassUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LandingPage {

    private AppiumDriver driver;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[2]")
    protected AndroidElement carousel1;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView[1]")
    protected AndroidElement carousel2;

    @AndroidFindBy( uiAutomator = "new UiSelector().textContains(\"Next\")")
    protected AndroidElement next;

    @AndroidFindBy( uiAutomator = "new UiSelector().textContains(\"Finish\")")
    protected AndroidElement finish;

    @AndroidFindBys( {@AndroidBy( uiAutomator = "new UiSelector().textContains(\"Follow\")")} )
    protected List<AndroidElement> followers;

    public LandingPage( AppiumDriver driver ){

        this.driver = driver;
        PageFactory.initElements( driver , this );
    }


    public void appLander() {
        ClassUtils.getWait( driver , 20 ).until(ExpectedConditions.visibilityOf( next )).click();
        followers.get( 0 ).click();
        followers.get( 1 ).click();
        finish.click();
    }

    public void clickCarousel(){

        carousel1.click();
    }
}
