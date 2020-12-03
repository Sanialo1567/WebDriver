import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebDriverSeleniumTNFTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup(){
        driver=new ChromeDriver();
    }

    @Test
    public void AddingItemsToCartTest() throws InterruptedException {
        driver.get("https://www.thenorthface.com/shop/shoes-womens");
        new WebDriverWait(driver,10).until(jQueryAJAXCompleted());
        WebElement searchInput = waitWebElementLocatedBy(driver,By.xpath("//*[@id='-button-id']"));
        searchInput.click();
        WebElement targetSize = waitWebElementLocatedBy(driver,By.xpath("//button[@class='product-content-form-size-btn-label attr-box']"));
        System.out.println(targetSize.toString());
        targetSize.click();
        WebElement addToCartButton = waitWebElementLocatedBy(driver,By.xpath("//*[@id='buttonAddToBag']"));
        addToCartButton.click();
        List<WebElement> itemsInCart = driver.findElements(By.xpath("//span[@class='topnav-cart-qty']"));

        Assert.assertTrue(itemsInCart.size()>0,"The item has not been added to the cart");

    }

    @AfterMethod(alwaysRun = true)
    public void BrowserTearDown() {
        driver.quit();
    }

    public static WebElement waitWebElementLocatedBy(WebDriver driver, By by)
    {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static ExpectedCondition<Boolean> jQueryAJAXCompleted(){
        return new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver){
                return (Boolean) ((JavascriptExecutor)
                driver).executeScript("return (window.jQuery" +
                        "!=null && (jQuery.active===0));");
            }
        };
    }
}
