package Framework;

import com.google.common.base.Preconditions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileBuilder {

    private String deviceID;
    private String deviceName;
    private String systemPort;
    private String appActivity;
    private String appPackage;
    private static String app;
    private String chromeDriverPort;
    private String wdaLocalPort;
    private String webkitDebugProxyPort;
    private DesiredCapabilities capabilities;
    private String platformVersion;
    private String platformName;
    private String port;


    public MobileBuilder(String appActivity, String appPackage , String app , String platformName ) {

        this.appActivity = appActivity;
        this.appPackage = appPackage;
        this.app = app;
        this.platformName = platformName;
    }

    public MobileBuilder withDeviceID(String deviceID){
        Preconditions.checkNotNull(deviceID);
        this.deviceID = deviceID;
        return this;
    }

    public MobileBuilder withDeviceName(String deviceName){
        Preconditions.checkNotNull(deviceName);
        this.deviceName = deviceName;
        return this;
    }

    public MobileBuilder withSystemPort(String systemPort){
        Preconditions.checkNotNull(systemPort);
        this.systemPort = systemPort;
        return this;
    }

    public MobileBuilder withChromeDriverPort(String chromeDriverPort ){
        Preconditions.checkNotNull(chromeDriverPort);
        this.chromeDriverPort = chromeDriverPort;
        return this;
    }

    public MobileBuilder withWdaLocalPort(String wdaLocalPort ){
        Preconditions.checkNotNull(wdaLocalPort);
        this.wdaLocalPort = wdaLocalPort;
        return this;
    }

    public MobileBuilder withWebKitDriverProxy(String webkitDebugProxyPort ){

        Preconditions.checkNotNull(webkitDebugProxyPort);
        this.webkitDebugProxyPort = webkitDebugProxyPort;
        return this;
    }

    public MobileBuilder withCapabilities(DesiredCapabilities capabilities ){

        Preconditions.checkNotNull(capabilities);
        this.capabilities = capabilities;
        return this;

    }

    public MobileBuilder withPlatformVersion(String platformVersion ){
        Preconditions.checkNotNull(platformVersion);
        this.platformVersion = platformVersion;
        return this;
    }

    public MobileBuilder withPlatform(String platformName ){
        Preconditions.checkNotNull( platformName );
        this.platformName = platformName;
        return this;
    }

    public MobileBuilder withPort(String port ){

        this.port = port;
        return  this;
    }


    public MobileDriver build(){
        MobileDriver mobileDriver = new     MobileDriver( this.appActivity , this.appPackage , this.app , this.platformName);
        mobileDriver.deviceName = this.deviceName;
        mobileDriver.webkitDebugProxyPort = this.webkitDebugProxyPort ;
        mobileDriver.systemPort = this.systemPort;
        mobileDriver.wdaLocalPort = this.wdaLocalPort;
        mobileDriver.chromeDriverPort = this.chromeDriverPort ;
        mobileDriver.deviceID = this.deviceID ;
        mobileDriver.platformVersion = this.platformVersion;
        mobileDriver.platformName = platformName;
        mobileDriver.port = port;
        mobileDriver.capabilities = this.capabilities != null ? this.capabilities : mobileDriver.buildConfig( platformName );
        try {
            mobileDriver.setUpDriver( mobileDriver.capabilities );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileDriver;

    }
}



