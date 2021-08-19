package screens;

import base.BaseScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SettingsScreen extends BaseScreen {

    @AndroidFindBy(id = "android:id/title")
    private List<MobileElement> settingsList;

    @AndroidFindBy(uiAutomator = "text(\"Украинский\")")
    private MobileElement ukraineLanguage;

    @AndroidFindBy(uiAutomator = "text(\"Язык\")")
    private MobileElement languageButton;

    @AndroidFindBy(accessibility = "Navigate up")
    private MobileElement backButton;

    public SettingsScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickOnSettingsTitleByIndex(int index) {
        waitElementToBeClickable(settingsList.get(index));
        settingsList.get(index).click();
    }

    public void clickLanguageButton() {
        languageButton.click();
    }

    public void chooseUkraineLanguage() {
        waitUntilElementAppear(ukraineLanguage);
        ukraineLanguage.click();
    }

    public void clickBackButton() {
        backButton.click();
    }
}
