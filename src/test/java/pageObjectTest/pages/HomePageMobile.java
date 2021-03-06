package pageObjectTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePageMobile {
    BaseFunctions baseFunctions;
    private static final Logger LOGGER = LogManager.getLogger(BaseFunctions.class);

    private static final By FIRST_ARTICLE = (By.xpath("//div[@class='md-mosaic-title']"));
    private static final By ARTICLE_TITLE = (By.xpath("//a[@class='md-scrollpos']"));
    private static final By COMMENT_COUNT = (By.xpath("//a[@class='commentCount']"));

    public HomePageMobile(BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public WebElement getFirstArticle() {
        LOGGER.info("Getting first article element");
        return baseFunctions.getElement(FIRST_ARTICLE);
    }

    public String getTitle(WebElement article) {
        LOGGER.info("Getting article title");
        return article.findElement(ARTICLE_TITLE).getText();
    }

    public int getCommentCount(WebElement article) {
        LOGGER.info("Getting comment count");
        if(article.findElements(COMMENT_COUNT).size() != 0) {
            String commentCountText = article.findElement(COMMENT_COUNT).getText();
            String countToParse = commentCountText;
            countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
            return Integer.valueOf(countToParse);
        } else {
            {
                return 0;
            }
        }

    }

    public ArticlePageDesktop openArticle() {
        LOGGER.info("Click element");
        baseFunctions.clickElement(ARTICLE_TITLE);
        return new ArticlePageDesktop(baseFunctions);
    }
}
