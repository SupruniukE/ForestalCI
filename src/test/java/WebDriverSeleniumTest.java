import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class WebDriverSeleniumTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
    }

    @Test
    public void OrderSiryonBikeTest() {

        String site = "https://forestal.com/en/home";
        String cookieButtonXpath = "//*[text()=\"Accept all cookies\"]";
        String siryonButtonXpath = "//*[@id=\"app\"]/div[1]/header/div/nav[1]/ul/li[4]/a";
        String orderButtonXpath = "//*[@id=\"app\"]/main/div[1]/div/a";
        String bikeButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/div/ul/li[2]";
        String editionOneButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[1]/div[2]/div/ul/li[4]";
        String confirmBuildKitButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[1]/div[2]/div/button";
        String regularButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[1]/div[3]/div/div[2]/div/ul/li[1]";
        String confirmBrakeButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[1]/div[3]/div/div[2]/button";
        String polarLightsButtonXpath = "//*[@id=\"group-1\"]/ul/li";
        String confirmColourButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[1]/div[4]/div/div[2]/button";
        String extraLargeButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[1]/div[5]/div/div[2]/ul/li[1]";
        String confirmSizeButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[1]/div[5]/div/div[2]/button";
        String sizeItemXpath = "//*[text()=\"Extra large\"]";
        String checkboxButtonXpath = "//*[@id=\"app\"]/div[2]/div[1]/div/div/div[2]/div[2]/div/label/span[1]";
        String addToBagButtonXpath = "//*[@id=\"app\"]/div[2]/div[4]/div/div/div[2]/a";
        String itemsInBagXpath = "//*[@id=\"app\"]/div[1]/header/div/nav[2]/ul/li[1]/a/div";

        List<String> buttons = List.of(cookieButtonXpath, siryonButtonXpath, orderButtonXpath, editionOneButtonXpath, confirmBuildKitButtonXpath, regularButtonXpath, confirmBrakeButtonXpath,
                polarLightsButtonXpath, confirmColourButtonXpath, extraLargeButtonXpath, confirmSizeButtonXpath, checkboxButtonXpath, addToBagButtonXpath);

        driver.get(site);
        driver.manage().window().fullscreen();

        buttons.forEach(button ->
                {
                    new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(button)));
                    new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(button)));
                    new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(button)));
                    WebElement testElement = driver.findElement(By.xpath(button));
                    testElement.click();
                    System.out.println(button);
                }
        );

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(itemsInBagXpath)));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(itemsInBagXpath)));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(itemsInBagXpath)));
        WebElement itemsInBag = driver.findElement(By.xpath(itemsInBagXpath));
        System.out.println("Number of elements in bag: " + itemsInBag.getText());

        Assert.assertEquals(itemsInBag.getText(), "1", "Search results are empty!");
        //Assert.assertTrue(true);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.close();
        driver.quit();
        driver = null;
    }
}
