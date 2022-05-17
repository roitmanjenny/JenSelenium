import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DriverSingleton {
    private static WebDriver driver;

    //creating an instance of WebDriver based on a browser-type in xml config file
    public static WebDriver getDriverInstance() throws Exception {
        if (driver == null) {
            try {
                String type = getData("browserType");
                if (type.equals("Chrome")) {
                    System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    ExtentReport.getTest().log(Status.PASS, "ChromeDriver created successfully");
                } else if (type.equals("FF")) {
                    System.setProperty("webdriver.firefox.driver", "C:tbd");
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    ExtentReport.getTest().log(Status.PASS, "FFDriver created successfully");
                }
            } catch (Exception e) {
                ExtentReport.getTest().log(Status.FAIL, "Driver failed!" + e.getMessage());
                throw new Exception("Driver failed!");
            }
        }
        return driver;
    }

    //parsing the xml
    public static String getData(String keyName) throws Exception {
        File fXmlFile = new File("/Users/jennyroitman/Library/CloudStorage/OneDrive-IgentifyLtd/JennyR/IdeaProjects/JenSelenium/src/test/resources/data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }
}
