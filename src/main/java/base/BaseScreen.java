package base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseScreen {
    private AndroidDriver driver;
    private final WebDriverWait wait;

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 50);
    }

    protected MobileElement waitUntilElementAppear(MobileElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException | TimeoutException e) {
            throw new WebDriverException("The Element: " + element + " is not Appear");
        }
        return element;
    }

    protected MobileElement waitElementToBeClickable(MobileElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException | TimeoutException e) {
            throw new WebDriverException("The Element: " + element + " is not clickable");
        }
        return element;
    }

    protected String getText(MobileElement element) {
        waitUntilElementAppear(element);
        return element.getText();
    }

    protected String getText(List<MobileElement> elements, int index) {
        waitUntilElementAppear(elements.get(index));
        return getText(elements.get(index));
    }

    protected boolean isElementDisplayed(MobileElement element) {
        try {
            waitUntilElementAppear(element);
            return element.isDisplayed();
        } catch (Exception ignored) {
            return false;
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public void swipeScreen() {
        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 200;
        int edgeBorder = 10;
        PointOption pointOptionStart, pointOptionEnd;

        Dimension dims = driver.manage().window().getSize();

        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);

        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void waitForTextToAppear(String textToAppear, MobileElement element) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
    }

}
