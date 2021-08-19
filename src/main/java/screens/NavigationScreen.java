package screens;

import base.BaseScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NavigationScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "text(\"Настройки\")")
    private MobileElement settingsInRussian;

    @AndroidFindBy(id = "menu_title")
    public List<MobileElement> navItems;

    @AndroidFindBy(uiAutomator = "text(\"Закладки\")")
    private MobileElement favorite;

    public NavigationScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickOnSettingsButton() {
        waitElementToBeClickable(settingsInRussian);
        settingsInRussian.click();
    }

    public void clickOnTitle(int index) {
        navItems.get(index).click();
    }

    public String getTitlesText(int index) {
        return getText(navItems, index);
    }

    public void openFavorites() {
        favorite.click();
    }
}
