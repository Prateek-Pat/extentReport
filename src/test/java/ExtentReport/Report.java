package ExtentReport;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report {
	
	
	public ExtentReports report;
	public WebDriver driver;	
	
	
	// ExtentReport, ExtentSparkReporter, ExtentHtmlReporter
	
	@BeforeMethod()
	public void config(){
		
		// Passing path where we waant to create report in ExtentSparkReporter parameter
		// Used for configuring the report
		
		String reportPath = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter extent = new ExtentSparkReporter(reportPath);
		extent.config().setReportName("WebAutomation_Report");
		extent.config().setDocumentTitle("Test_Report");
		
		// Now pass extent object in ExtentReports class so that it get to know that where the report is being generate
		
		report = new ExtentReports(); 
		report.attachReporter(extent);	
		report.setSystemInfo("Tester", "Max Payne");
		
}
		
	@Test(priority=1)
	public void Reports(){
		
		// Now we need to pass objecct of ExtentReports class so that it can tract the test while executing
		
		report.createTest("Reports");
		System.setProperty("webdriver.chrome.driver", "E:\\SELENIUM\\Selenium Chrome Driver\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://fb.com");
		String title = driver.getTitle();
		System.out.println(title);
		driver.close();
		
	}
	
	@Test(priority=2)
	public void Login(){
		
		report.createTest("Login");
		System.setProperty("webdriver.chrome.driver", "E:\\SELENIUM\\Selenium Chrome Driver\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://fb.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("Prateek.innu@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("prat@face123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.close();
		
	}
	@AfterMethod
	public void tearDown(){
		report.flush();
		
	}
	
}
