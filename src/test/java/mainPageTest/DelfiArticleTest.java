package mainPageTest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

// Additional: By article title, check WEB: name and comments; and the same on modile.

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DelfiArticleTest {

    private String HOME_PAGE_DESKTOP = "http://rus.delfi.lv";
    private String HOME_PAGE_MOBILE = "http://m.rus.delfi.lv";

    int artNumToCheck = 3;
    List<String> articleNameListDesktop = new ArrayList<String>();
    List<String> articleNameListMobile = new ArrayList<String>();

    @Test
    public void test01compareFirstFiveArticleNamesOnAllLevels_desktop() {

        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //Compare article names on all levels
        for (int i = 0; i < artNumToCheck; i++) {
            driver.get(HOME_PAGE_DESKTOP);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='headerLogo']")));
            Assert.assertTrue(driver.getTitle().equals("DELFI - Ведущий новостной портал в Латвии - DELFI"));

            List<WebElement> elements = new ArrayList<WebElement>(driver.findElements(By.xpath("//h3//a[@class='top2012-title']")));
            List<String> articleNames = new ArrayList<String>();
            List commentCount = new ArrayList();

            articleNames.add(elements.get(i).getText());
            System.out.println("Testing article: " + (i + 1));
            System.out.println("Main page: " + elements.get(i).getText());
            elements.get(i).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='article-title']")));
            articleNames.add(driver.findElement(By.xpath("//h1[@class='article-title']")).getText());
            System.out.println("Article page: " + driver.findElement(By.xpath("//h1[@class='article-title']")).getText());

            List<WebElement> element = new ArrayList<WebElement>();
            element.add(driver.findElement(By.xpath("//div//h1[@class='article-title']")));
            boolean commentsExist;
            if (element.contains(By.xpath("//a[@class='comment-count']"))) {
                commentsExist = true;
            } else {
                commentsExist = false;
            }
            System.out.println(commentsExist);

            if (commentsExist) {
                driver.findElement(By.xpath("//div//h1[@class='article-title']//..//a")).click();

                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='comments-about-title']//a[@class='comment-main-title-link']")));
                if (driver.findElement(By.xpath("//div[@class='comments-about-title']//a[@class='comment-main-title-link']")).getText().contains(": комментарии")) {
                    articleNames.add(StringUtils.substringBeforeLast(driver.findElement(By.xpath("//div[@class='comments-about-title']//a[@class='comment-main-title-link']")).getText(), ":"));
                    System.out.println("Comment page: " + StringUtils.substringBeforeLast(driver.findElement(By.xpath("//div[@class='comments-about-title']//a[@class='comment-main-title-link']")).getText(), ":"));
                } else {
                    articleNames.add(driver.findElement(By.xpath("//div[@class='comments-about-title']//a[@class='comment-main-title-link']")).getText());
                    System.out.println("Comment page: " + driver.findElement(By.xpath("//div[@class='comments-about-title']//a[@class='comment-main-title-link']")).getText());
                }
            }

            for (int x = 0; x < articleNames.size(); x++) {
                Assert.assertTrue(articleNames.get(0).equals(articleNames.get(x)));
            }

            articleNameListDesktop.add(articleNames.get(0));
        }
        Assert.assertTrue(articleNameListDesktop.size() == artNumToCheck);
        System.out.println(articleNameListDesktop);
    }

    @Test
    public void test02compareFirstFiveArticleNamesOnAllLevels_mobile() {

        System.setProperty("webdriver.gecko.driver", "C:/QA2/GeckoDriver/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //Compare article names on all levels
        for (int i = 0; i < artNumToCheck; i++) {
            driver.get(HOME_PAGE_MOBILE);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='header-logo']")));
            Assert.assertTrue(driver.getTitle().equals("RUS DELFI"));

            List<WebElement> elementMobile = new ArrayList<WebElement>(driver.findElements(By.xpath("//a[@class='md-scrollpos']")));
            List<String> articleNamesMobile = new ArrayList<String>();

            articleNamesMobile.add(elementMobile.get(i).getText());
            System.out.println(elementMobile.get(i).getText());
            elementMobile.get(i).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='article-title']")));
            articleNamesMobile.add(driver.findElement(By.xpath("//div[@class='article-title']//h1")).getText());
            System.out.println(driver.findElement(By.xpath("//div[@class='article-title']//h1")).getText());

            if (driver.findElement(By.xpath("//a[@class='commentCount']")).isDisplayed()) {
                driver.findElement(By.xpath("//a[@class='commentCount']")).click();

                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='comments-about-title']")));
                articleNamesMobile.add(driver.findElement(By.xpath("//div[@class='comments-about-title']")).getText());
                System.out.println(driver.findElement(By.xpath("//div[@class='comments-about-title']")).getText());
            }

            for (int x = 0; x < articleNamesMobile.size(); x++) {
                Assert.assertTrue(articleNamesMobile.get(x).equals(driver.findElement(By.xpath("//div[@class='comments-about-title']")).getText()));
            }

            articleNameListMobile.add(articleNamesMobile.get(0));
        }

        Assert.assertTrue(articleNameListMobile.size() == artNumToCheck);
        System.out.println(articleNameListMobile);
    }

    @Test
    public void test03compareFirstFiveArticleNamesBetweenDesktopAndMobile(){

        Assert.assertTrue(articleNameListDesktop.size() == articleNameListMobile.size());
        for (int i = 0; i < articleNameListDesktop.size(); i++) {
            System.out.println(articleNameListDesktop.get(i));
            Assert.assertTrue(articleNameListDesktop.get(i).matches(articleNameListMobile.get(i)));
        }
    }

    public void openNewTab() throws AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
    }

    public void openInNewTab(WebElement element) throws AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_SHIFT);
        element.click();
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_SHIFT);
    }

}
