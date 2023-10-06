import Pages.*;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Tests {

    public static WebDriver driver;
    String startPage = "https://market.yandex.ru/";
    public static StartPage mainPage;
    public static Electronics electronics;
    public static Filters filters;
    public static Result result;
    public static Goods goods;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(startPage);
        mainPage = new StartPage(driver);
        electronics = new Electronics(driver);
        filters = new Filters(driver);
        result = new Result(driver);
        goods = new Goods(driver);
    }


    @org.junit.Test
    public void test() {
        mainPage.clickOnCatalog();
        mainPage.selectElectronics();
        electronics.toSmartphonesAndGadgets();
        electronics.toSmartphones();
        filters.toAllFilters();
        filters.setMaxPrice("20000");
        filters.setMinScreenSize("3");
        filters.setProducers(5);
        filters.showResults();
        result.goodsCount(48);
        result.rememberAndFind();
        goods.switchToGood();
        goods.getRate();
    }


    @After
    public void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(
                "./screenshots/screenshot.png"));
        driver.quit();
    }
}