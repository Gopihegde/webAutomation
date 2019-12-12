package Framework;

import com.google.common.base.Preconditions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MobileDriver implements CommonDriver<AppiumDriver>  {
    private Map<String, Object> baseCapabilities;
    private AppiumDriver driver;
    String platformName;
    private final Properties properties = new Properties();
    static String resourceName;
    String platformVersion;
    String deviceID;
    String deviceName;
    String systemPort;
    String appActivity;
    String appPackage;
    String app;
    String chromeDriverPort;
    String wdaLocalPort;
    String webkitDebugProxyPort;
    DesiredCapabilities capabilities;
    String port;
    GsonHelper helper = GsonHelper.getHelper();


    /**
     * Default constructor for appium start.
     * Properties are read from maven.
     */

     MobileDriver() throws MalformedURLException {

        this.appActivity = properties.getProperty( FrameworkConstants.APP_ACTIVITY );
        this.appPackage = properties.getProperty( FrameworkConstants.PACKAGE_NAME );
        setUpDriver(buildConfig(platformName));
    }

    /**
     * @param appActivity Launchable app activity for Android. In case of iOS it might be null value.
     * @param packageName package name / bundle of the iOS / Android test app.
     */
    MobileDriver(String appActivity, String packageName , String app, String platformName ) {

        this.appPackage = packageName;
        this.app = app;
        this.appActivity = appActivity;
        this.platformName = platformName;


    }

     protected void setUpDriver(DesiredCapabilities capabilities) throws MalformedURLException {

        switch (platformName.toLowerCase()) {

            case "android":
              //  startServer( FrameworkConstants.BASE_IP , Integer.valueOf(port) , capabilities );
                this.driver = new AndroidDriver<AndroidElement>(new URL(FrameworkConstants.BASE_URL + port + FrameworkConstants.WD_PATH), capabilities);
                driver.manage().timeouts().implicitlyWait( 20 , TimeUnit.SECONDS);
                break;
            case "ios":
                //startServer( FrameworkConstants.BASE_IP , Integer.valueOf(port) , capabilities );
                this.driver = new IOSDriver<IOSElement>(new URL(FrameworkConstants.BASE_URL + port + FrameworkConstants.WD_PATH), capabilities);
        }

    }


    private Map<String, Object> capabilities(String platform) {

        switch (platform.toLowerCase()) {

            case "android":
                this.baseCapabilities = helper.deSerialise(FrameworkConstants.ANDROID_FILE);
                break;
            case "ios":
                this.baseCapabilities = helper.deSerialise(FrameworkConstants.IOS_FILE);
                break;
            default:
        }

        return this.baseCapabilities;
    }


    /**
     * @param platform platform for which capabilities to be built.
     * @return Desired Capabilities for the given @platform.
     */
    DesiredCapabilities buildConfig(String platform) {
        this.capabilities = new DesiredCapabilities(capabilities(platform));
        capabilities.setCapability(FrameworkConstants.UDID, deviceID = deviceID != null ? this.deviceID : ""); // It will be used in local mapping of udid.

        switch (platform) {

            case "android":
                capabilities.setCapability(FrameworkConstants.APP_PACKAGE, this.appPackage);
                capabilities.setCapability(FrameworkConstants.DEVICE_NAME, deviceName = deviceName != null ? this.deviceName : FrameworkConstants.DEFAULT_DEVICENAME);
                capabilities.setCapability(FrameworkConstants.SYSTEM_PORT, systemPort = systemPort != null ? this.systemPort : FrameworkConstants.DEFAULT_SYSTEM_PORT);
                capabilities.setCapability(FrameworkConstants.APP_ACTIVITY, this.appActivity);
                //capabilities.setCapability(FrameworkConstants.APP, app);
                capabilities.setCapability(FrameworkConstants.CHROME_DRIVER_PORT, chromeDriverPort = chromeDriverPort != null ? this.chromeDriverPort : FrameworkConstants.DEFAULT_CHROMEDRIVER_PORT);
                break;
            case "ios":
                capabilities.setCapability(FrameworkConstants.OS_VERSION, this.platformVersion = this.platformVersion != null ? this.platformVersion : new String());
                capabilities.setCapability(FrameworkConstants.BUNDLE_ID, this.appPackage);
                capabilities.setCapability(FrameworkConstants.WDPP, webkitDebugProxyPort = webkitDebugProxyPort != null ? this.webkitDebugProxyPort : FrameworkConstants.DEFAULT_WEBKITLOCALPROXY);
                capabilities.setCapability(FrameworkConstants.WDA_LOCAL_PORT, wdaLocalPort = wdaLocalPort != null ? this.wdaLocalPort : FrameworkConstants.DEFAULT_WDALOCALPORT);
                capabilities.setCapability(FrameworkConstants.DEVICE_NAME, this.deviceName);
                capabilities.setCapability(FrameworkConstants.APP, app);
                break;
        }

        return capabilities;

    }


    public void setCapabilities(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public String getAppPackage() {
        return appPackage;
    }

    private void startServer( String ipAddress , int port, DesiredCapabilities capabilities) {

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .withIPAddress( ipAddress )
                .usingPort( port )
                .withCapabilities( capabilities  );
        AppiumDriverLocalService driverLocalService = AppiumDriverLocalService.buildService( serviceBuilder );
        driverLocalService.start();

    }


    @Override
    public AppiumDriver getDriver() {
        return this.driver;
    }
}
