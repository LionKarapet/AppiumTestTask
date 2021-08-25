package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import server.AppiumServerHandler;


public class TestBase extends AppiumServerHandler {
    protected AndroidDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        appiumUrl = startAppiumServer();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        stopAppiumLocalServer();
    }

    @BeforeMethod
    public void setUp() {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appPackage", "dkc.video.hdbox");
        capabilities.setCapability("appActivity", "com.dkc.fs.ui.activities.HomeActivity");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, "src/main/resources/hd-videobox_2.31.apk");
        capabilities.setCapability("avd", "Pixel_2_API_29");//avd name on local machine
        driver = new AndroidDriver(appiumUrl, capabilities);
    }

    @AfterMethod
    public void removeApp() {
        driver.removeApp("dkc.video.hdbox");
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}
