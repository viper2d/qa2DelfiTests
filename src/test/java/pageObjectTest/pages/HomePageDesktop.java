package pageObjectTest.pages;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjectTest.wrappers.ArticleWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePageDesktop {
    BaseFunctions baseFunctions;
    private static final Logger LOGGER = LogManager.getLogger(BaseFunctions.class);

    private static final By ARTICLE_ITEM = (By.xpath("//h3[@class='top2012-title']"));
    private static final By FIRST_ARTICLE = (By.xpath("//h3[@class='top2012-title']"));
    private static final By ARTICLE_TITLE = (By.xpath("//a[@class='top2012-title']"));
    private static final By COMMENT_COUNT = (By.xpath("//h3[@class='top2012-title']//a[@class='comment-count']"));

    public HomePageDesktop(BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public WebElement getFirstArticle() {
        LOGGER.info("DESKTOP: Getting first article element");
        return baseFunctions.getElement(FIRST_ARTICLE);
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

    public ArticlePageDesktop openArticle() {
        LOGGER.info("Click element");
        baseFunctions.clickElement(ARTICLE_TITLE);
        return new ArticlePageDesktop(baseFunctions);
    }

    private List<ArticleWrapper> getAllArticles() {
        List<WebElement> articles = baseFunctions.findElements(ARTICLE_ITEM);
        List<ArticleWrapper> articleWrappers = new ArrayList<>();

        Iterables.addAll(articleWrappers, articles.stream().map(webElement -> new ArticleWrapper(baseFunctions, webElement)).collect(Collectors.toList()));

        return articleWrappers;
    }

    private ArticleWrapper getArticleByTitle(String name) {
        Optional<ArticleWrapper> wrapper = Iterables.tryFind(getAllArticles(), articleWrapper -> articleWrapper.getArticleTitle().contains(name));
        return wrapper.isPresent() ? wrapper.get() : null;
    }

    public ArticlePageDesktop openArticleByTitle(String articleName) {
        getArticleByTitle(articleName).clickOnTitle();
        return new ArticlePageDesktop(baseFunctions);
    }


}
