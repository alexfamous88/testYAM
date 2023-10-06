package Pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Result {

    public WebDriver driver;

    public Result(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Кнопка "Показать еще"
    @FindBy(xpath = "//*[@data-auto='pager-more']")
    private WebElement showMoreBtn;

    // Список товаров на странице
    @FindBy(xpath = "//*[@data-test-id='virtuoso-item-list']")
    private WebElement goodsList;

    // Первый товар списка
    @FindBy(xpath = "(//article[@data-autotest-id='product-snippet'])[1]//h3/a/span")
    private WebElement firstInList;

    // Сортировка по рейтингу
    @FindBy(xpath = "/html/body/div[1]/div/div[4]/div/div[1]/div/div[5]/div/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div/noindex/div/button[3]")
    private WebElement sortByRate;

    // Локатор прогрузки страницы
    @FindBy(xpath = "//*[@data-grabber='SearchLegalInfo']")
    private WebElement pageLoad;

    public void goodsCount(int count) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", showMoreBtn);
        wait.until(ExpectedConditions.visibilityOf(pageLoad));
        List<WebElement> list = driver.findElements(By.xpath("//article[@data-autotest-id='product-snippet']"));
        Assert.assertEquals("Количество отображаемых на странице товаров не соответствует ожидаемому",
                count, list.size());
    }

    public void rememberAndFind() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String titleOfFirstGood = firstInList.getText();
        js.executeScript("arguments[0].scrollIntoView();", sortByRate);
        sortByRate.click();

        js.executeScript("arguments[0].scrollIntoView();", showMoreBtn);

        WebElement firstAfterSort;

        int i = 0;
        while (i == 0) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(showMoreBtn));
                showMoreBtn.click();
                js.executeScript("arguments[0].scrollIntoView();", showMoreBtn);
                firstAfterSort = driver.findElement(By.xpath("//*[.='" + titleOfFirstGood + "']"));
                js.executeScript("arguments[0].scrollIntoView();", firstAfterSort);
                wait.until(ExpectedConditions.elementToBeClickable(firstAfterSort));
                firstAfterSort.click();
                i = -1;
            } catch (NoSuchElementException s) {
            }
        }
    }
}