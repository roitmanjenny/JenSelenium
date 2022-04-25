import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JenHW8 {
    private static WebDriver driver;

    @BeforeClass
    public static void runBefore() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();


    }
    @Test
    public void test02() {
        driver.get("https://translate.google.com");
        driver.findElement(By.className("er8xn"));
        System.out.println(driver.findElement(By.className("er8xn")));
        driver.findElement(By.className("er8xn")).sendKeys("משהו בעברית");
    }
    @Test
    public void test03(){
        driver.get("https://www.youtube.com/");
        driver.findElement(By.id("search-icon-legacy"));
        System.out.println(driver.findElement(By.id("search-icon-legacy")));
        driver.findElement(By.name("search_query")).sendKeys("Madonna");
        driver.findElement(By.id("search-icon-legacy")).click();
    }
    @Test
    public void test04(){
        driver.get("https://www.amazon.com/");
        Assert.assertEquals(driver.getTitle(),"Amazon.com. Spend less. Smile more.");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Leather shoes");
        driver.findElement(By.id("nav-search-submit-button")).click();
    }
    @Test
    public void test05(){
        driver.get("https://www.facebook.com/");
        //don't know how not to send credentials.((

    }
    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

}