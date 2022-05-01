import name.finsterwalder.fileutils.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JenHW9 {
    private static WebDriver driver;

    @BeforeClass
    public static void runBefore() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
    }

    //1
    @Test
    public void test01() {
        //print 7 button dimentions
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        System.out.println(driver.findElement(By.id("seven")).getRect().getX());
        System.out.println(driver.findElement(By.id("seven")).getRect().getY());

        //check if 6 button displayed
        boolean six = driver.findElement(By.id("six")).isDisplayed();
        System.out.println(six);

        //calculation with expected result
        String expResult = "6";
        driver.findElement(By.id("four")).click();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("two")).click();
        driver.findElement(By.id("equal")).click();
        String actResult = driver.findElement(By.id("screen")).getText();
        Assert.assertEquals(expResult, actResult);
    }

    //2-3 - assert website url and title
    @Test
    public void test02() {
        driver.get("https://www.amazon.com/");
        String url = "https://www.amazon.com/";
        String title = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(url, driver.getCurrentUrl());
        driver.navigate().refresh();
        Assert.assertEquals(title, driver.getTitle());
        driver.quit();
    }

    //4 - run chrome without extensions
    @Test
    public void test03() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
    }

    //5 - screenshot the box element
    @Test
    public void test04() {
        driver.get("https://dgotlieb.github.io/Actions/");
        Object webElement;
        WebElement box = driver.findElement(By.id("div1"));
        File screenShotFile = box.getScreenshotAs(OutputType.FILE); // take the screenshot
        try {
            FileHandler.copy(screenShotFile, new File("element-screenshot.png")); // save screenshot to disk
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //5-double click
    @Test
    public void test05() {
        WebElement dclick = driver.findElement(By.xpath("//p[@ondblclick='doubleClickFunction()']"));
        Actions doubleclick = new Actions(driver);
        doubleclick.doubleClick(dclick);
        doubleclick.perform();
        String clicked = driver.findElement(By.id("demo")).getText();
        Assert.assertEquals("You double clicked", clicked);
    }

    //5 - hover
    @Test
    public void test06() {

        Actions hover = new Actions(driver);
        WebElement ex = driver.findElement(By.id("close"));
        hover.moveToElement(ex).perform();
    }

    //5-select multiple - not working.((
    @Test
    public void test07() {
        driver.get("https://dgotlieb.github.io/Actions/");
        List<WebElement> elementList = driver.findElements(By.name("kind"));
        Actions multiSelect = new Actions(driver);
        multiSelect.clickAndHold(elementList.get(0)).clickAndHold(elementList.get(2)).click();
        multiSelect.perform();
    }

    //5 - upload a file
    @Test
    public void test08() {
        driver.findElement(By.name("pic")).sendKeys("/Users/jennyroitman/Downloads/1.png");
    }

    //6
    @Test
    public void test09() {
        driver.get("https://dgotlieb.github.io/Controllers/");
        driver.findElement(By.name("group1")).click();

        Select selected = new Select(driver.findElement(By.name("dropdownmenu")));
        selected.selectByIndex(1);
        for (int i = 0; i < selected.getOptions().size(); i++) {
            System.out.println(selected.getOptions().get(i).getText());
        }
    }

    //7 - buttons height and width
    @Test
    public void test10() {
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        System.out.println(driver.findElement(By.id("two")).getRect().height);
        System.out.println(driver.findElement(By.id("six")).getRect().width);
    }


    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

}

