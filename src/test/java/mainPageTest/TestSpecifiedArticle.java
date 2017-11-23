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

        import java.util.*;
        import java.util.List;
        import java.util.NoSuchElementException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSpecifiedArticle {

    private String PAGE_TO_TEST = "http://www.delfi.lv/showtime/news/whatson/family/belorusskaya-gruppa-super-besse-predstavit-v-rige-novyj-albom.d?id=49245405";
    private String PAGE_TO_TEST2 = "http://rus.delfi.lv/news/eurobasket/novosti/vse-tolko-nachinaetsya-pochemu-latvijskaya-komanda-mechty-eto-nadolgo.d?id=49246531";
    List<String> articleNameListDesktop = new ArrayList<String>();


    @Test
    public void test01singleArticle_desktop() {

        System.setProperty("webdriver.gecko.driver", "C:/QA2/GeckoDriver/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);


        driver.get(PAGE_TO_TEST);

        List<String> articleNames = new ArrayList<String>();
        List<WebElement> element = new ArrayList<WebElement>();
        boolean commentsExist;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='article-title']")));
        articleNames.add(driver.findElement(By.xpath("//h1[@class='article-title']")).getText());
        System.out.println("Article page: " + driver.findElement(By.xpath("//h1[@class='article-title']")).getText());

        element.add(driver.findElement(By.xpath("//div//h1[@class='article-title']")));

//        if(driver.findElement(By.xpath("//div//h1[@class='article-title']")).getAttribute("href")){
//            commentsExist = true;
//        } else {
//            commentsExist = false;
//        }
//
        try {
            driver.findElement(By.xpath("//div//h1[@class='article-title']//a"));
            commentsExist = true;
        } catch (Exception e) {
            commentsExist = false;
        }

        System.out.println(commentsExist);

        if (commentsExist) {
            System.out.println(driver.findElement(By.xpath("//div//h1[@class='article-title']//..//a")).isDisplayed());
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

        System.out.println(articleNameListDesktop);
    }
}