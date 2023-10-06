package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage {

    public WebDriver driver;

    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Кнопка каталога
    @FindBy(xpath = "//*[@data-zone-name='catalog']/button")
    private WebElement catalogBtn;

    // Список категорий
    @FindBy(xpath = "//*[@class='_1FyR2']")
    private WebElement categoriesList;

    // Категория "Электроника" в списке категорий
    @FindBy(xpath = "//*[@class='_1010X']//*[.='Электроника']")
    private WebElement electronicsLink;


    public void clickOnCatalog() {
        catalogBtn.click();
    }

    public void selectElectronics() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(categoriesList));
        electronicsLink.click();
    }
}