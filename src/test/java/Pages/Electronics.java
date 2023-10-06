package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Electronics {

    public WebDriver driver;

    public Electronics(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Раздел "Смартфоны и гаджеты"
    @FindBy(xpath = "//a[@href='https://market.yandex.ru/catalog--smartfony-i-gadzhety/26893630']")
    private WebElement smartphonesAndGadgets;

    // Ссылка на раздел "Смартфоны"
    @FindBy(xpath = "//*[@data-apiary-widget-name='@MarketNode/NavigationTree']//a[.='Смартфоны']")
    private WebElement smartphonesLink;


    public void toSmartphonesAndGadgets() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", smartphonesAndGadgets);
        smartphonesAndGadgets.click();
    }

    public void toSmartphones() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", smartphonesLink);
        smartphonesLink.click();
    }
}