package tests;

import Framework.CommonDriver;
import Framework.LaunchManager;
import Framework.MobileBuilder;
import PageObjects.IosHomeFinder;
import PageObjects.LaunchPageFinderApp;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AndroidLaunch {

    CommonDriver commonDriver;
    AppiumDriver driver;
    LaunchPageFinderApp launchPageFinderApp;


    @BeforeTest()
    @Parameters( {"appPackage","appActivity","deviceID","deviceName", "port" , "platform","app"})
    public void setUpTest( String appPackage , String appActivity, String deviceId , String deviceName,String port , String platform ,String app  ){
        System.out.printf(" Executing before test ");

        commonDriver = new MobileBuilder( appActivity , appPackage, app , platform )
                .withDeviceID( deviceId )
                .withDeviceName( deviceName)
                .withPort( port )
                .build();
        LaunchManager.setAppiumDriver((AppiumDriver) commonDriver.getDriver());
        driver = LaunchManager.getAppiumDriver();
        fetchClass();

    }


    public void fetchClass(){

        launchPageFinderApp = new LaunchPageFinderApp( driver );


    }

    @Test(priority = 1 , description = "appStart")
    public void startApp(){
        Assert.assertTrue( launchPageFinderApp.launchChecker() );
        launchPageFinderApp.searchLocation( "Bellandur" );

    }

}
