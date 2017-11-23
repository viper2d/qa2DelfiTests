package mainPageTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.*;
import java.util.List;

public class TempClass {
    private static final By ARTICLE = (By.xpath(""));
    private static final By ARTICLE_NAME = (By.xpath(""));
    private static final By COMMENT_COUNT = (By.xpath(""));

    public void testMethod() {
//        WebDriver driver = new FirefoxDriver();
//
//        List<WebElement> articleList = driver.findElements(ARTICLE);

        List<String> titles = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        List<String> linkIdsToCheck = new ArrayList<String>();

        List<String> titlesM = new ArrayList<String>();
        List<Integer> countsM = new ArrayList<Integer>();

//        for (int i = 0; i < 5; i++) {
//            WebElement element = articleList.get(i);
//            titles.add(element.findElement(ARTICLE_NAME).getText());
//            linkIdsToCheck.add(element.findElement(ARTICLE_NAME).getAttribute("href").substring(1,2));
//
//            if (element.findElements(COMMENT_COUNT).size() != 0) {
//                String countToParse = element.findElement(COMMENT_COUNT).getText(); //(10)
//                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
//                counts.add(Integer.valueOf(countToParse));
//            } else {
//                counts.add(0);
//            }
//        }

        //Compare lists

    }
    @Test
    public void test01() {
        List<String> titles = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        List<String> linkIdsToCheck = new ArrayList<String>();

        List<String> titlesM = new ArrayList<String>();
        List<Integer> countsM = new ArrayList<Integer>();
        titles.add("a");
        titles.add("b");
        titlesM.add("aM");
        titlesM.add("bM");
        for (String s : titles) {
            System.out.println(s + " " + titles.get(titles.indexOf(s)));
            Assert.assertEquals("Titles doesn't match", s, titlesM.get(titles.indexOf(s)));
        }
    }
}


