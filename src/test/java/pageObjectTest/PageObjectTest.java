package pageObjectTest;

import org.junit.Assert;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjectTest.pages.ArticlePageDesktop;
import pageObjectTest.pages.BaseFunctions;
import pageObjectTest.pages.HomePageDesktop;
import pageObjectTest.pages.HomePageMobile;

public class PageObjectTest {
    BaseFunctions baseFunctions = new BaseFunctions();

    private static final Logger LOGGER = LogManager.getLogger(PageObjectTest.class);
    private static final String DELFI_DESK_HOME_URL = "http://rus.delfi.lv";
    private static final String DELFI_MOB_HOME_URL = "http://m.rus.delfi.lv";

    @Test
    public void delfiPageTestDesktop() {
        LOGGER.info("DESKTOP: Opening homepage");
        baseFunctions.goToUrl(DELFI_DESK_HOME_URL);
        HomePageDesktop homePageDesktop = new HomePageDesktop(baseFunctions);
        LOGGER.info("DESKTOP: Getting first article title");

        homePageDesktop.getFirstArticle();
        String titleDesk = homePageDesktop.getTitle(homePageDesktop.getFirstArticle());
        LOGGER.info("DESKTOP: *Found artice title: " + titleDesk);

        LOGGER.info("DESKTOP: Getting first article comment count");
        int commentCountDesk = homePageDesktop.getCommentCount(homePageDesktop.getFirstArticle());
        LOGGER.info("DESKTOP: *Found comment count: " + commentCountDesk);

        LOGGER.info("DESKTOP: Opening article page");
        ArticlePageDesktop articlePageDesktopDesk = homePageDesktop.openArticle();

        LOGGER.info("DESKTOP: Getting article title from article page");
        articlePageDesktopDesk.getArticleInfo();
        String currentArticleTitleDesk = articlePageDesktopDesk.getTitle(articlePageDesktopDesk.getArticleInfo());
        LOGGER.info("DESKTOP: **Found article title: " + currentArticleTitleDesk);

        LOGGER.info("DESKTOP: Getting article comment count from article page");
        int articlePageCommentCountDesk = articlePageDesktopDesk.getCommentCount(articlePageDesktopDesk.getArticleInfo());
        LOGGER.info("DESKTOP: **Found comment count: " + articlePageCommentCountDesk);

        LOGGER.info("DESKTOP: Comparing titles");
        Assert.assertTrue("DESKTOP: Titles on main and article pages differs!", titleDesk.equals(currentArticleTitleDesk));
        LOGGER.info("DESKTOP: Comparing comment count");
        Assert.assertEquals("DESKTOP: Comment count on main and article page differs!", commentCountDesk, articlePageCommentCountDesk);

        LOGGER.info("DESKTOP: Opening comment page");
        LOGGER.info("DESKTOP: Getting article title from comment page");
        LOGGER.info("DESKTOP: Getting article registered comment count from comment page");
        LOGGER.info("DESKTOP: Getting article anonymous comment count from comment page");
        LOGGER.info("DESKTOP: Getting article comment sum from comment page");
        LOGGER.info("DESKTOP: Checking title");
        LOGGER.info("DESKTOP: Checking comment");

        LOGGER.info("Test successful");
    }

    @Test
    public void delfiPageTestMobile() {
        LOGGER.info("MOBILE: Opening homepage");
        baseFunctions.goToUrl(DELFI_MOB_HOME_URL);

        LOGGER.info("MOBILE: Getting first article title");
        HomePageMobile homePageMobile = new HomePageMobile(baseFunctions);
        homePageMobile.getFirstArticle();
        String titleMob = homePageMobile.getTitle(homePageMobile.getFirstArticle());
        LOGGER.info("MOBILE: *Found artice title: " + titleMob);

        LOGGER.info("MOBILE: Getting first article comment count");
        int commentCountMob = homePageMobile.getCommentCount(homePageMobile.getFirstArticle());
        LOGGER.info("MOBILE: *Found comment count: " + commentCountMob);

        LOGGER.info("MOBILE: Opening article page");
        ArticlePageDesktop articlePageMob = homePageMobile.openArticle();

        LOGGER.info("MOBILE: Getting article title from article page");
        articlePageMob.getArticleInfo();
        String currentArticleTitleMob = articlePageMob.getTitle(articlePageMob.getArticleInfo());
        LOGGER.info("MOBILE: **Found article title: " + currentArticleTitleMob);

        LOGGER.info("MOBILE: Getting article comment count from article page");
        int articlePageCommentCountMob = articlePageMob.getCommentCount(articlePageMob.getArticleInfo());
        LOGGER.info("MOBILE: **Found comment count: " + articlePageCommentCountMob);

        LOGGER.info("MOBILE: Comparing titles");
        Assert.assertTrue("DESKTOP: Titles on main and article pages differs!", titleMob.equals(currentArticleTitleMob));
        LOGGER.info("MOBILE: Comparing comment count");
        Assert.assertEquals("DESKTOP: Comment count on main and article page differs!", commentCountMob, articlePageCommentCountMob);

        LOGGER.info("DESKTOP: Opening comment page");
        LOGGER.info("DESKTOP: Getting article title from comment page");
        LOGGER.info("DESKTOP: Getting article registered comment count from comment page");
        LOGGER.info("DESKTOP: Getting article anonymous comment count from comment page");
        LOGGER.info("DESKTOP: Getting article comment sum from comment page");
        LOGGER.info("DESKTOP: Checking title");
        LOGGER.info("DESKTOP: Checking comment");

        LOGGER.info("Test successful");
    }

}
