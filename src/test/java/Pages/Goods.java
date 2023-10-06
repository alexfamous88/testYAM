package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Set;

public class Goods {

    public WebDriver driver;

    public Goods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Локатор рейтинга товара
    @FindBy(xpath = "(//*[@aria-label='Рейтинг товара']//span)[2]")
    private WebElement rateOfGood;


    public void switchToGood() {
        Set<String> windowsSet = driver.getWindowHandles();
        ArrayList<String> windowsList = new ArrayList<>(windowsSet);
        String goodHandle = windowsList.get(1);
        driver.switchTo().window(goodHandle);
    }

    public void getRate() {
        String rate = rateOfGood.getText().strip();
        System.out.println("Рейтинг товара: " + rate);
    }
}