import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.ITestResult;
import org.testng.annotations.*;

import utility.Base;
import utility.ReportGenerator;
import utility.TakeScreenshot;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import java.util.Date;


public class SimplePage extends Base {

    private WebDriver driver;
    private long executionTime;
    private long startTime=0;
    private long endTime=0;
    private String executionDate;
    private String actual;
    private String expected;
    private String path;
    private String fileName;
    private String testName;
    WebElement element;
    TakeScreenshot takeScreenshot=new TakeScreenshot();

       By inputform =By.className("dropdown-toggle");
       By simpleform =By.xpath("//*[@id=\"navbar-brand-centered\"]/ul[1]/li[1]/ul/li[1]/a");

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\Drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.seleniumeasy.com/test/");
        ReportGenerator.initialize();
    }
    @BeforeMethod
    public void nameBefore(ITestResult tr,Method method)
    {
        System.out.println("Started : " + method.getName());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        executionDate = formatter.format(date);
        startTime = System.currentTimeMillis();
    }

    @Test
    public void VerifyPage(Method method) {

        element =driver.findElement(inputform);
        actual = element.getText();
        element.click();
        path = takeScreenshot.captureScreenShot(driver,method.getName());
        expected ="Input Forms";
        fileName =getFileName(path);
        testName =method.getName();
    }

    @Test
    public void VerifySimpleFormPage(Method method) {
        element = driver.findElement(simpleform);
        actual = element.getText();
        path = takeScreenshot.captureScreenShot(driver, method.getName());
        expected = "Simple Form Demo";
        testName = method.getName();
        fileName = getFileName(path);
    }
    @Test
    public void VerifyFormPage(Method method) {
        element = driver.findElement(simpleform);
        actual = element.getText();
        path = takeScreenshot.captureScreenShot(driver, method.getName());
        expected = "Input Formsq324";
        testName = method.getName();
        fileName = getFileName(path);
    }
    @Test
    public void VerifySimple(Method method) {
        element = driver.findElement(simpleform);
        actual = element.getText();
        path = takeScreenshot.captureScreenShot(driver, method.getName());
        expected = "";
        testName = method.getName();
        fileName = getFileName(path);
    }
    @AfterMethod
    public void getRunTime(ITestResult tr,Method method) {
        endTime =tr.getEndMillis();
        executionTime = endTime - startTime;
        ReportGenerator.report(testName,actual,expected ,executionDate,executionTime,fileName);
    }
    @AfterClass
    public void tearDown() {

        ReportGenerator.displaySummary();
        ReportGenerator.writeTestResults();
        driver.close();
    }
}