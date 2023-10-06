package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Filters {

    public WebDriver driver;

    public Filters(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Кнопка перехода к расширенному поиску
    @FindBy(xpath = "//button[.='Все фильтры']")
    private WebElement allFilters;

    // Ввод максимальной стоимости
    @FindBy(xpath = "//*[@id='glprice']//*[@data-prefix='до']//input")
    private WebElement maxPriceInput;

    // Диагональ экрана
    @FindBy(xpath = "//*[contains(text(), 'Диагональ экрана (точно)')]")
    private WebElement diagonalLocator;

    // Ввод минимального размера экрана
    @FindBy(xpath = "//*[@data-filter-id='14805766']//*[@data-auto='range-filter-input-min']")
    private WebElement minScreenSizeInput;

    // Производители
    @FindBy(xpath = "//*[contains(text(), 'Производитель')]")
    private WebElement producersLocator;

    // Кнопка показа предложений по выбранным фильтрам
    @FindBy(xpath = "//*[@data-autotest-id='result-filters-link']")
    private WebElement showResults;


    public void toAllFilters() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", allFilters);
        allFilters.click();
    }

    public void setMaxPrice(String price) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", maxPriceInput);
        maxPriceInput.sendKeys(price);
    }

    public void setMinScreenSize(String size) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", diagonalLocator);
        diagonalLocator.click();
        minScreenSizeInput.sendKeys(size);
    }

    public void setProducers(int quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 1; i <= quantity; i++) {
            js.executeScript("arguments[0].scrollIntoView();", producersLocator);
            wait.until(ExpectedConditions.elementToBeClickable(producersLocator));
            producersLocator.click();
            driver.findElement(By.xpath("(//*[@data-filter-id='7893318']//label)[" + i + "]")).click();
        }
    }

    public void showResults() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", showResults);
        showResults.click();
    }

}