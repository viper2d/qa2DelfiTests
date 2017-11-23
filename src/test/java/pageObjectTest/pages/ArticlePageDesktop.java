package pageObjectTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArticlePageDesktop {
    BaseFunctions baseFunctions;
    private static final Logger LOGGER = LogManager.getLogger(BaseFunctions.class);

    private static final By ARTICLE_ELEMENT = (By.xpath("//div[@class='article-title']"));
    private static final By ARTICLE_TITLE = (By.xpath("//h1[@class='article-title']"));
    private static final By COMMENT_COUNT = (By.xpath("//div[@class='article-title']//a[@class='comment-count']"));

    public ArticlePageDesktop(BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public WebElement getArticleInfo() {
        LOGGER.info("DESKTOP: Getting article attributes");
        return baseFunctions.getElement(ARTICLE_ELEMENT);
    }

    public String getTitle(WebElement article) {
        LOGGER.info("DESKTOP: Getting article title");
        return article.findElement(ARTICLE_TITLE).getText();
    }

    public int getCommentCount(WebElement article) {
        LOGGER.info("DESKTOP: Getting comment count");
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

}
