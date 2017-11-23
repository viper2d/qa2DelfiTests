package mainPageTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DelfiArticleAndCommentTest {

    private String HOME_PAGE_DESKTOP = "http://rus.delfi.lv";
    private String HOME_PAGE_MOBILE = "http://m.rus.delfi.lv";
    private String HOME_PAGE_DESKTOP_TITLE = "DELFI - Ведущий новостной портал в Латвии - DELFI";
    private String HOME_PAGE_MOBILE_TITLE = "RUS DELFI";

    private static final By ARTICLE_LIST_DESK = (By.xpath("//h3[@class='top2012-title']"));
    private static final By ARTICLE_NAME_DESK = (By.xpath("//a[@class='top2012-title']"));
    private static final By COMMENT_COUNT_DESK = (By.xpath("//a[@class='comment-count']"));
    private static final By CURRENT_ARTICLE_DESK = (By.xpath("//h1[@class='article-title']"));
    private static final By CURRENT_COMMENT_DESK = (By.xpath("//div[@class='article-title']//a[@class='comment-count']"));
    private static final By CURRENT_COMMENT_DESK_REG = (By.xpath("//a[@class='comment-thread-switcher-list-a comment-thread-switcher-list-a-reg']"));
    private static final By CURRENT_COMMENT_DESK_ANON = (By.xpath("//a[@class='comment-thread-switcher-list-a comment-thread-switcher-list-a-anon']"));

    private static final By ARTICLE_LIST_MOB = (By.xpath("//div[@class='md-mosaic-title']"));
    private static final By ARTICLE_NAME_MOB = (By.xpath("//a[@class='md-scrollpos']"));
    private static final By COMMENT_COUNT_MOB = (By.xpath("//a[@class='commentCount']"));
    private static final By CURRENT_ARTICLE_MOB = (By.xpath("//div[@class='article-title']//h1"));
    private static final By CURRENT_COMMENT_MOB = (By.xpath("//div[@class='article-title']//a[@class='commentCount']"));
    private static final By CURRENT_COMMENT_MOB_REG = (By.xpath("//a[@class='comment-thread-switcher-list-a comment-thread-switcher-list-a-reg']"));
    private static final By CURRENT_COMMENT_MOB_ANON = (By.xpath("//a[@class='comment-thread-switcher-list-a comment-thread-switcher-list-a-anon']"));

    private static final Logger LOGGER = LogManager.getLogger(DelfiArticleAndCommentTest.class);

    List<String> articleNamesDesktop = new ArrayList<String>();
    List<Integer> commentCountDesktop = new ArrayList<Integer>();
    List<String> articleNamesMobile = new ArrayList<String>();
    List<Integer> commentCountMobile = new ArrayList<Integer>();
    List<String> articleLinksDesk = new ArrayList<String>();
    List<String> articleLinksMob = new ArrayList<String>();
    int articlesToTest = 2;

    @Test
    public void test01preconditions() {

        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        WebDriver driverDesk = new FirefoxDriver();
        driverDesk.manage().window().maximize();
        WebDriver driverMob = new FirefoxDriver();
        driverMob.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driverDesk, 10);

        driverDesk.get(HOME_PAGE_DESKTOP);
        List<WebElement> elementsDesk = driverDesk.findElements(ARTICLE_LIST_DESK);
        Assert.assertTrue(driverDesk.getTitle().equals(HOME_PAGE_DESKTOP_TITLE));

        driverMob.get(HOME_PAGE_MOBILE);
        List<WebElement> elementsMob = driverMob.findElements(ARTICLE_LIST_MOB);
        Assert.assertTrue(driverMob.getTitle().equals(HOME_PAGE_MOBILE_TITLE));

        for (int i = 0; i < articlesToTest; i++) {

            WebElement currentElementDesk = elementsDesk.get(i);
            articleNamesDesktop.add(currentElementDesk.findElements(ARTICLE_NAME_DESK).get(i).getText());
            articleLinksDesk.add(currentElementDesk.findElements(ARTICLE_NAME_DESK).get(i).getAttribute("href"));

            WebElement currentElementMob = elementsMob.get(i);
            articleNamesMobile.add(currentElementMob.findElements(ARTICLE_NAME_MOB).get(i).getText());
            articleLinksMob.add(currentElementMob.findElements(ARTICLE_NAME_MOB).get(i).getAttribute("href"));


            if (currentElementDesk.findElements(COMMENT_COUNT_DESK).size() != 0) {
                System.out.println("DEBUG *1 : " + currentElementDesk.findElements(COMMENT_COUNT_DESK).get(i).getText());
                String countToParseDesk = currentElementDesk.findElements(COMMENT_COUNT_DESK).get(i).getText();
                countToParseDesk = countToParseDesk.substring(countToParseDesk.indexOf('(') + 1, countToParseDesk.indexOf(')'));
                System.out.println("DEBUG *2 : " + countToParseDesk);
                commentCountDesktop.add(Integer.valueOf(countToParseDesk));
            } else {
                System.out.println("DEBUG *3 : Adding 0");
                commentCountDesktop.add(0);
                System.out.println("DEBUG *3 : Added " + commentCountDesktop.get(i));
            }

            if (currentElementDesk.findElements(COMMENT_COUNT_MOB).size() != 0) {
                String countToParseMobile = currentElementMob.findElements(COMMENT_COUNT_MOB).get(i).getText();
                countToParseMobile = countToParseMobile.substring(countToParseMobile.indexOf('(') + 1, countToParseMobile.indexOf(')'));
                commentCountMobile.add(Integer.valueOf(countToParseMobile));
            } else {
                commentCountMobile.add(0);
            }
        }

        System.out.println("----------------------DESKTOP----------------------");
        System.out.println(articleNamesDesktop);
        System.out.println(commentCountDesktop);
        System.out.println(articleLinksDesk);
        System.out.println("----------------------MOBILE----------------------");
        System.out.println(articleNamesMobile);
        System.out.println(commentCountMobile);
        System.out.println(articleLinksMob);

        for (int i = 0; i < articlesToTest; i++) {

            driverDesk.get(articleLinksDesk.get(i));
            driverMob.get(articleLinksMob.get(i));

            Assert.assertTrue("Desktop article names differs! " + articleNamesDesktop.get(i), driverDesk.findElement(CURRENT_ARTICLE_DESK).getText().equals(articleNamesDesktop.get(i)));
            Assert.assertTrue("Mobile article names differs! " + articleNamesMobile.get(i), driverDesk.findElement(CURRENT_ARTICLE_MOB).getText().equals(articleNamesMobile.get(i)));

            if (driverDesk.findElements(CURRENT_COMMENT_DESK).size() != 0) {
                System.out.println("Desk: Testing comments for: " + articleNamesDesktop.get(i));
                System.out.println("Desk: Expected comment count: " + commentCountDesktop.get(i));

                String countToParseDesk = driverDesk.findElement(CURRENT_COMMENT_DESK).getText();
                countToParseDesk = countToParseDesk.substring(countToParseDesk.indexOf('(') + 1, countToParseDesk.indexOf(')'));
                System.out.println("Desk: Comment count on article page: " + Integer.valueOf(countToParseDesk));
                Assert.assertTrue("Desktop current comment differs for: " + articleNamesDesktop.get(i), commentCountDesktop.get(i) <= (Integer.valueOf(countToParseDesk)));
                driverDesk.findElement(CURRENT_COMMENT_DESK).click();

                String countToParseReg = driverDesk.findElement(CURRENT_COMMENT_DESK_REG).getText();
                countToParseReg = countToParseReg.substring(countToParseReg.indexOf('(') + 1, countToParseReg.indexOf(')'));
                String countToParseAnon = driverDesk.findElement(CURRENT_COMMENT_DESK_ANON).getText();
                countToParseAnon = countToParseAnon.substring(countToParseAnon.indexOf('(') + 1, countToParseAnon.indexOf(')'));
                System.out.println("Desk: Sum of comments on comment page: " + (Integer.valueOf(countToParseReg) + Integer.valueOf(countToParseAnon)));
                Assert.assertTrue("Desktop comment page count differs for: " + articleNamesDesktop.get(i), commentCountDesktop.get(i) <= (Integer.valueOf(countToParseReg) + Integer.valueOf(countToParseAnon)));
            } else {
                System.out.println("DEBUG: ** " + commentCountDesktop.get(i));
                Assert.assertTrue("Desktop comment differs for: " + articleNamesDesktop.get(i), commentCountDesktop.get(i).equals(0));
            }

            if (driverMob.findElements(CURRENT_COMMENT_MOB).size() != 0) {

                System.out.println("Mob: Testing comments for: " + articleNamesMobile.get(i));
                System.out.println("Mob: Expected comment count: " + commentCountMobile.get(i));

                String countToParseMob = driverMob.findElement(CURRENT_COMMENT_MOB).getText();
                countToParseMob = countToParseMob.substring(countToParseMob.indexOf('(') + 1, countToParseMob.indexOf(')'));
                System.out.println("Mob: Comment count on article page: " + Integer.valueOf(countToParseMob));
                Assert.assertTrue("Desktop current comment differs for: " + articleNamesMobile.get(i), commentCountMobile.get(i) <= (Integer.valueOf(countToParseMob)));
                driverMob.findElement(CURRENT_COMMENT_MOB).click();

                String countToParseReg = driverDesk.findElement(CURRENT_COMMENT_MOB_REG).getText();
                countToParseReg = countToParseReg.substring(countToParseReg.indexOf('(') + 1, countToParseReg.indexOf(')'));
                String countToParseAnon = driverDesk.findElement(CURRENT_COMMENT_MOB_ANON).getText();
                countToParseAnon = countToParseAnon.substring(countToParseAnon.indexOf('(') + 1, countToParseAnon.indexOf(')'));
                System.out.println("Mob: Sum of comments on comment page: " + (Integer.valueOf(countToParseReg) + Integer.valueOf(countToParseAnon)));
                Assert.assertTrue("Desktop comment page count differs for: " + articleNamesMobile.get(i), commentCountMobile.get(i) <= (Integer.valueOf(countToParseReg) + Integer.valueOf(countToParseAnon)));
            } else {
                Assert.assertTrue("Desktop comment differs for: " + articleNamesMobile.get(i), commentCountMobile.get(i).equals(0));
            }
        }

        Assert.assertTrue("Article names mismatch: ",articleNamesDesktop.equals(articleNamesMobile));
        Assert.assertTrue("Comment count mismatch: ",commentCountDesktop.equals(commentCountMobile));
    }

    @Test
    public void test02compare() {

        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        WebDriver driverDesk = new FirefoxDriver();
        driverDesk.manage().window().maximize();
        WebDriver driverMob = new FirefoxDriver();
        driverMob.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driverDesk, 10);

        for (int i = 0; i < 5; i++) {

            driverDesk.get(articleLinksDesk.get(i));
            driverMob.get(articleLinksMob.get(i));
        }
    }
}
