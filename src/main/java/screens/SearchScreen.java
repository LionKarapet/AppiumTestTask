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

import static io.appium.java_client.android.nativekey.AndroidKey.*;

public class SearchScreen extends BaseScreen {
    @AndroidFindBy(id = "searchEditText_input")
    private MobileElement searchField;

    @AndroidFindBy(id = "textView_item_text")
    private List<MobileElement> searchResults;

    @AndroidFindBy(className = "android.widget.ProgressBar")
    private MobileElement loadingProgressIcon;


    public SearchScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void searchForMovie(String value) {
        waitUntilElementAppear(searchField);
        searchField.click();
        searchField.clear();
        searchField.sendKeys(value);
        waitForTextToAppear(value, searchResults.get(0));
    }

    public void clickOnSearchResult(int index) {
        waitUntilElementAppear(searchResults.get(index));
        searchResults.get(index).click();
    }

    public boolean isProgressLoadingIconAppeared() {
        return isElementDisplayed(loadingProgressIcon);
    }

    public void clickKeyboardSearchButton() {
        getDriver().pressKey(new KeyEvent().withKey(ENTER));
    }

    public boolean searchForMovieAndCheckLoadingIcon(String value) {
        waitUntilElementAppear(searchField);
        searchField.click();
        searchField.clear();
        searchField.sendKeys(value);
        clickKeyboardSearchButton();
        return isProgressLoadingIconAppeared();
    }
}
