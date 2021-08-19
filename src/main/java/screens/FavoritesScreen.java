package screens;

import base.BaseScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FavoritesScreen extends BaseScreen {

    @AndroidFindBy(id = "card_layout")
    private List<MobileElement> cards;

    public FavoritesScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public int getMovieCounts() {
        return cards.size();
    }
}
