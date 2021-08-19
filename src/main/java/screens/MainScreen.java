package screens;

import base.BaseScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainScreen extends BaseScreen {
    @AndroidFindBy(id = "menu_search")
    private MobileElement searchButton;

    @AndroidFindBy(className = "android.widget.ImageButton")
    private MobileElement navigationButton;

    @AndroidFindBy(id = "title")
    private List<MobileElement> movieTitles;

    @AndroidFindBy(id = "md_text_message")
    private MobileElement popUpMessage;

    public MainScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickSearchButton() {
        waitUntilElementAppear(searchButton);
        searchButton.click();
    }

    public void clickOnNavBar() {
        waitUntilElementAppear(searchButton);
        waitUntilElementAppear(navigationButton);
        navigationButton.click();
    }

    public void waitForPopUpAndClose() {
        if (isElementDisplayed(popUpMessage))
            getDriver().pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    public void closeNavbar() {
        swipeScreen();
    }

}
