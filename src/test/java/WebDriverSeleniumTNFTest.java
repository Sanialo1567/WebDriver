import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverSeleniumTNFTest {
    WebDriver edge;

    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup(){
        System.setProperty("webdriver.chrome.driver", "C://webdrivers");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        edge = new EdgeDriver(chromeOptions);
        edge.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void AddingItemsToCartTest() throws InterruptedException {



        edge.get("https://www.thenorthface.com/womens.html");
        WebElement searchButton = edge.findElement(By.xpath("//*[@id=\"body-container\"]/div/div[1]/div/div/nav/div/ul/li[5]/a"));
        searchButton.click();
        WebElement searchInput = edge.findElement(By.xpath(" //*[@id=\"-button-id\"]/span "));
        searchInput.click();
        WebElement targetSize = waitForElementLocatedBy(edge,By.xpath("//*[@id=\"product-attr-form\"]/section[2]/div[3]/div/button[3]"));
        System.out.println(targetSize.toString());
        targetSize.click();
        WebElement addToCartButton = edge.findElement(By.xpath("//*[@id=\"buttonAddToBag\"]"));
        addToCartButton.click();
        List<WebElement> itemsInCart = edge.findElements(By.xpath("//*[@id=\"body-container\"]/div[1]/header/section/div/div[2]/article/div[4]/div/div[1]/div[1]/div[2]"));

        Assert.assertTrue(itemsInCart.size()>0,"The item has not been added to the cart");

    }

    @AfterMethod(alwaysRun = true)
    public void BrowserTearDown() {
        edge.quit();
    }
    private static WebElement waitForElementLocatedBy(WebDriver driver, By by){
        return new WebDriverWait(driver,4)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
