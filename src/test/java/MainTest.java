import base.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static org.testng.Assert.*;

public class MainTest extends TestBase {

    @BeforeMethod
    public void popupDismiss() {
        new MainScreen(getDriver()).waitForPopUpAndClose();
    }

    //Open the app -> Go to settings/View and Appearance -> Change or set the language to “Ukrainian” -> Check that the language of the app changed.
    @Test
    public void languageChangeTest() {
        SettingsScreen settingsScreen = new SettingsScreen(getDriver());
        NavigationScreen navigationScreen = new NavigationScreen(getDriver());
        MainScreen mainScreen = new MainScreen(getDriver());

        String navBarFirstItemTextBeforeChange = navigationScreen.getTitlesText(0);
        navigationScreen.clickOnSettingsButton();
        settingsScreen.clickOnSettingsTitleByIndex(3);
        settingsScreen.clickLanguageButton();
        settingsScreen.chooseUkraineLanguage();
        settingsScreen.clickBackButton();
        mainScreen.waitForPopUpAndClose();
        mainScreen.clickOnNavBar();
        String navBarFirstItemTextAfterChange = navigationScreen.getTitlesText(0);
        assertNotEquals(navBarFirstItemTextBeforeChange, navBarFirstItemTextAfterChange, "Language is not changed to Ukraine");
    }

    //Open the app -> Search for the “Shrek” movie -> Check that “Эдди Мёрфи” actor are in the casts list.
    @Test
    public void movieSearchTest() {
        MainScreen mainScreen = new MainScreen(getDriver());
        SearchScreen searchScreen = new SearchScreen(getDriver());
        MovieScreen movieScreen = new MovieScreen(getDriver());
        String actor = "Эдди Мёрфи";

        mainScreen.closeNavbar();
        mainScreen.clickSearchButton();
        searchScreen.searchForMovie("Shrek");
        searchScreen.clickOnSearchResult(0);
        assertTrue(movieScreen.isActorPresentInStarringList(actor), actor + " is not in the starring list");
    }

    //Open the app -> Search for the “Kung Fu Panda” movie -> Add the movie to the “Избранные” category(flag image icon in the top right corner)
    // -> Check that “Избранные” list contains added movie
    @Test
    public void addMovieToFavoriteTest() {
        MainScreen mainScreen = new MainScreen(getDriver());
        SearchScreen searchScreen = new SearchScreen(getDriver());
        MovieScreen movieScreen = new MovieScreen(getDriver());
        NavigationScreen navigationScreen = new NavigationScreen(getDriver());
        FavoritesScreen favoritesScreen = new FavoritesScreen(getDriver());
        String movie = "Kung Fu Panda";

        mainScreen.closeNavbar();
        mainScreen.clickSearchButton();
        searchScreen.searchForMovie(movie);
        searchScreen.clickOnSearchResult(0);
        movieScreen.addMovieToFavorite();
        movieScreen.clickBackButton();
        mainScreen.clickOnNavBar();
        navigationScreen.openFavorites();
        assertEquals(favoritesScreen.getMovieCounts(), 1, "movie is not added to favorites");
    }

    //    Find a way to get the locator for the loading spinner(image below) in this app and
//    create a test that checks if this spinner is shown in the application when we open any category.
    @Test
    public void loadingIconTest() {
        MainScreen mainScreen = new MainScreen(getDriver());
        SearchScreen searchScreen = new SearchScreen(getDriver());
        String movie = "Ediie";

        mainScreen.closeNavbar();
        mainScreen.clickSearchButton();
        assertTrue(searchScreen.searchForMovieAndCheckLoadingIcon(movie), "progress loading icon did not appear");
    }
}
