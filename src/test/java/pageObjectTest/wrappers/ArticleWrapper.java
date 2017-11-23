package pageObjectTest.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjectTest.pages.BaseFunctions;

public class ArticleWrapper {

    private final BaseFunctions baseFunctions;
    private final WebElement element;

    private static final By TITLE = (By.xpath("//a[@class='top2012-title']"));

    public ArticleWrapper(BaseFunctions baseFunctions, WebElement element) {
        this.baseFunctions = baseFunctions;
        this.element = element;
    }

    public String getArticleTitle() {
        return element.findElements(TITLE).isEmpty() ? element.findElement(TITLE).getText() : null;
    }

    public void clickOnTitle() {
        element.click();
    }
}
