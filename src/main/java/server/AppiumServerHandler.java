package server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.net.UrlChecker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumServerHandler {
    private int appiumPort;
    private String appiumHost;
    private AppiumDriverLocalService localService;
    private AppiumServiceBuilder serviceBuilder;
    protected URL appiumUrl;

    protected URL startAppiumServer() {
        URL appiumUrl = startServer();

        appiumHost = appiumUrl.getHost();
        appiumPort = appiumUrl.getPort();

        if (!serverCheckStatusRequest(getAppiumHost(), String.valueOf(getAppiumPort()))) {
            throw new RuntimeException("it is not possible to run Appium server with host:" + getAppiumHost()
                    + " and port " + getAppiumPort());
        }
        return appiumUrl;
    }

    private URL startServer() {
        //Build the Appium service
        serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.withIPAddress("127.0.0.1");
        serviceBuilder.usingAnyFreePort();
        serviceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        serviceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
        serviceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        //Start the server with the serviceBuilder
        localService = AppiumDriverLocalService.buildService(serviceBuilder);
        localService.start();
        return localService.getUrl();
    }

    protected void stopAppiumLocalServer() {
        localService.stop();
    }

    private int getAppiumPort() {
        return appiumPort;
    }

    private String getAppiumHost() {
        return appiumHost;
    }

    private boolean serverCheckStatusRequest(String host, String port) {
        final String SERVER_URL = String.format("http://%s:%s/wd/hub", host, port);

        URL status = null;
        try {
            status = new URL(SERVER_URL + "/sessions");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            new UrlChecker().waitUntilAvailable(20, TimeUnit.SECONDS, status);
            return true;
        } catch (UrlChecker.TimeoutException e) {
            return false;
        }
    }
}
