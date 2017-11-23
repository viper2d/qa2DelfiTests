package pageObjectTest;

import org.junit.Test;
import pageObjectTest.pages.ArticlePageDesktop;
import pageObjectTest.pages.BaseFunctions;
import pageObjectTest.pages.HomePageDesktop;

public class WrappersTest {
    BaseFunctions baseFunctions = new BaseFunctions();
    private static final String DELFI_DESK_HOME_URL = "http://rus.delfi.lv";
    private static final String ARTICLE_NAME = "Riga vs Riga";

    @Test
    public void wrapperExampleTests() {
        baseFunctions.goToUrl(DELFI_DESK_HOME_URL);
        HomePageDesktop homePageDesktop = new HomePageDesktop(baseFunctions);

        ArticlePageDesktop articlePageDesktop = homePageDesktop.openArticleByTitle(ARTICLE_NAME);
    }


}
