import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class JenHW10 {
    private static WebDriver driver;
    private static NgWebDriver ngWebDriver;

    @BeforeClass
    public static void runBefore() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //1 - implicit wait
    @Test
    public void test01() {
        driver.get("https://dgotlieb.github.io/Selenium/synchronization.html");
        driver.findElement(By.id("btn")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("message")).isDisplayed();
    }
    //1 - thread sleep
    @Test
    public void test02() throws InterruptedException {
        driver.findElement(By.id("hidden")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("finish1")).isDisplayed();
    }

    //1 - Explicit wait
    @Test
    public void test03() throws InterruptedException {
        driver.findElement(By.id("rendered")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish2")));
    }
    //2 - Using ngWebDriver
    @Test
    public void test04(){
        driver.navigate().to("https://dgotlieb.github.io/AngularJS/main.html");
        ngWebDriver.waitForAngularRequestsToFinish();
        WebElement first = driver.findElement(ByAngular.model("firstName"));
        first.clear();
        first.sendKeys("Jenny");
        assertEquals(driver.findElement(ByAngular.model("firstName")).getAttribute("value"),"Jenny");

    }
    @Test
    public void test05() {
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        System.out.println(driver.findElement(By.id(Constants.SEVEN)).getSize());
        System.out.println(driver.findElement(By.id("six")).isDisplayed());
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
