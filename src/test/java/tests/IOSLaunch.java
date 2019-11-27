package tests;

import Framework.CommonDriver;
import Framework.LaunchManager;
import Framework.MobileBuilder;
import PageObjects.IosHomeFinder;
import PageObjects.LaunchPageFinderApp;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IOSLaunch {

    CommonDriver commonDriver;
    AppiumDriver driver;
    IosHomeFinder homeFinder;


    @BeforeTest()
    @Parameters( {"appPackage","appActivity","deviceID","deviceName", "port" , "platform","app","platformVersion"})
    public void setUpTest(String appPackage , @Optional  String appActivity, String deviceId , String deviceName, String port , String platform , String app , String platformVersion ){
        System.out.printf(" Executing before test ");

        commonDriver = new MobileBuilder( appActivity , appPackage, app , platform )
                .withDeviceID( deviceId )
                .withDeviceName( deviceName)
                .withPort( port )
                .withPlatformVersion( platformVersion)
                .build();
        LaunchManager.setAppiumDriver((AppiumDriver) commonDriver.getDriver());
        driver = LaunchManager.getAppiumDriver();
        fetchClass();

    }


    public void fetchClass(){

        homeFinder = new IosHomeFinder(driver);


    }

    @Test(priority = 1 , description = "appStart")
    public void startApp(){
        Assert.assertTrue( homeFinder.launchChecker() );
        homeFinder.searchLocation( "Bellandur" );

    }
}
