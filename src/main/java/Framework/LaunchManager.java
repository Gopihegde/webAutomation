package Framework;

import io.appium.java_client.AppiumDriver;

public class LaunchManager {

    public static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();


    public static void setAppiumDriver(AppiumDriver driver) {
        appiumDriver.set( driver );
    }


    public static AppiumDriver getAppiumDriver() {
        return appiumDriver.get();
    }
}
