import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class JenHW11 {
    private static WebDriver driver;

    @BeforeClass
    public static void runBefore() throws Exception {
        ExtentReport.createTest("Homework 11", "some tests");
        ExtentReport.extent.setSystemInfo("Company", "Jen");
        driver = DriverSingleton.getDriverInstance();
        //3
        driver.get(DriverSingleton.getData("URL"));
        ExtentReport.getTest().log(Status.INFO, "Before test prep done");
    }

    //   @Test
//    public void test01() throws Exception {
//        //change the url in data xml to "https://dgotlieb.github.io/Navigation/Navigation.html"
//        driver.switchTo().frame("my-frame");
//        System.out.println("IFrame text is: " + driver.findElement(By.id("iframe_container")).getText());
//    }

    // 2
//    @Test
//    public void test02() throws Exception {
//        //change the url in data xml to "https://translate.google.com/"
//        driver.findElement(By.id("source")).click();
//        ExtentReport.getTest().pass("Translate box clicked");
//        ExtentReport.getTest().pass("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(ExtentReport.takeScreenShot(driver, Constants.TIMENOW)).build());
//    }
    // 4
    @Test
    public void test041() throws Exception {
        driver.findElement(By.id("MyAlert")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }
    @Test
    public void test042() throws Exception {
        driver.findElement(By.id("MyPrompt")).click();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("Jen");
        promptAlert.accept();
        assertEquals("Jen",driver.findElement(By.id("output")).getText());
    }

    @Test
    public void test043() throws Exception {
        driver.findElement(By.id("MyConfirm")).click();
        Alert confirmAlert = driver.switchTo().alert();
        confirmAlert.accept();
        assertEquals("Confirmed", driver.findElement(By.id("output")).getText());

        driver.findElement(By.id("MyConfirm")).click();
        confirmAlert.dismiss();
        assertEquals("canceled", driver.findElement(By.id("output")).getText());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        ExtentReport.getTest().log(Status.INFO, "All tests finished");
        ExtentReport.getReporter().flush();
        DriverSingleton.getDriverInstance().quit();
    }

}


