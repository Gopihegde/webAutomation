package tests;

import Framework.ClassUtils;
import Framework.CommonDriver;
import Framework.LaunchManager;
import Framework.MobileBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class OneFCAndroid {


    CommonDriver commonDriver;
    AndroidDriver<AndroidElement> driver;


    @Parameters( {"appPackage","appActivity","deviceID","deviceName", "port" , "platform","app"})
    @BeforeTest()
    public void setUpTest( String appPackage , String appActivity, String deviceId , String deviceName,String port , String platform ,String app  ){
        System.out.printf(" Executing before test ");

        commonDriver = new MobileBuilder( appActivity , appPackage, app , platform )
                .withDeviceID( deviceId )
                .withDeviceName( deviceName)
                .withPort( port )
                .build();
        LaunchManager.setAppiumDriver((AppiumDriver) commonDriver.getDriver());
        driver = (AndroidDriver<AndroidElement>) LaunchManager.getAppiumDriver();


    }


    @Test(priority = 1 , description = "appStart")
    @Parameters( { "name"} )
    public void test(String name){
        ClassUtils.sleep( 4000 );
        AndroidElement next =  driver.findElementByXPath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView\n" );
        ClassUtils.getWait( driver , 20 ).until(ExpectedConditions.visibilityOf( next ));
        new TouchAction(driver)
                .press(PointOption.point( next.getLocation().getX() , next.getLocation().y ))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis( 2000 )))
                .release()
                .perform();
        List<AndroidElement> followers =  driver.findElementsByAndroidUIAutomator( "new UiSelector().text(\"FOLLOW\")" );
        followers.get( 0 ).click();
        followers.get( 1  ).click();
        AndroidElement finish =  driver.findElementByAndroidUIAutomator( "new UiSelector().text(\"Finish\")" );
        finish.click();
        AndroidElement carouselMain =  driver.findElement( By.xpath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[1]" ) );
      carouselMain.click();

      AndroidElement boutText =  driver.findElementByXPath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[1]\n" );
        Assert.assertTrue( boutText.isDisplayed() );
      System.out.println( boutText.getText() );
      driver.pressKey( new KeyEvent( AndroidKey.BACK ) );
      AndroidElement carousel2 = driver.findElementByXPath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ImageView[1]" );
      carousel2.click();
      AndroidElement search = driver.findElementByXPath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[2]" );

      search.click();
      ClassUtils.sleep( 3000 );
      AndroidElement searchFor =  driver.findElementByAndroidUIAutomator( "new UiSelector().textContains(\"Search ONE for...\")" );
      searchFor.click();
      searchFor.sendKeys( name );
      AndroidElement athletes =   driver.findElementByAndroidUIAutomator( "new UiSelector().textContains(\"Athletes\")" );
      athletes.click();
      AndroidElement lee = driver.findElementByXPath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView[1]" );
      System.out.println( lee.getText() );
      lee.click();
      System.out.println( driver.getPageSource() );
      AndroidElement nickName = driver.findElementByAndroidUIAutomator( "new UiSelector().text(\"UNSTOPPABLE\")" );
      Assert.assertEquals(  "UNSTOPPABLE".toLowerCase() , nickName.getText().toLowerCase()  );

    }

}
