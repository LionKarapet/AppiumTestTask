package screens;

import base.BaseScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class MovieScreen extends BaseScreen {
    @AndroidFindBy(id = "details_starring")
    private MobileElement starringText;

    @AndroidFindBy(id = "menu_addToFavs")
    private MobileElement favIcon;

    @AndroidFindBy(uiAutomator = "text(\"Избранное\")")
    private MobileElement favCheckbox;

    @AndroidFindBy(accessibility = "Navigate up")
    private MobileElement backButton;

    @AndroidFindBy(id = "play_trailer")
    private MobileElement trailerButton;

    public MovieScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isActorPresentInStarringList(String actor) {
        waitUntilElementAppear(starringText);
        return starringText.getText().contains(actor);
    }

    public void addMovieToFavorite() {
        waitUntilElementAppear(trailerButton);
        favIcon.click();
        favCheckbox.click();
    }

    public void clickBackButton() {
        backButton.click();
    }
}
