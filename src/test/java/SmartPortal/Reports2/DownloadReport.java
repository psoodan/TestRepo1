package SmartPortal.Reports2;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DownloadReport {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	String dateFrom = "1/January/2018";
	String dateTo="31/January/2018";
	
	@Test
	public void greenCargoReport(){
				
				report = new ExtentReports(System.getProperty("user.dir") +"/test-output/STMExtentReport.html", false);
				logger = report.startTest("Test has been started");
				System.setProperty("webdriver.chrome.driver", "C:/Users/psoodan/git/Reports2/Drivers/chromedriver.exe");
				driver = new ChromeDriver();
				logger.log(LogStatus.INFO, "Chrome browser open");
				driver.manage().window().maximize();
				driver.get("https://ddakshi:Mpower@123@20.149.4.228/access/prd2/index.pl");  //http://username:password@url
				logger.log(LogStatus.INFO, "User login");
		        driver.findElement(By.xpath("//*[text()='SERVICE REPORTS']")).click();
		        logger.log(LogStatus.INFO, "Click on service reports");
		        driver.findElement(By.xpath("//*[@id='Menu2']/li[1]/ul/li[1]/a/span[2]")).click();
		        driver.findElement(By.xpath("//*[@id='Menu2']/li[1]/ul/li[1]/ul/li[1]/a/span")).click();
		        Select AccountName = new Select(driver.findElement(By.xpath("html/body/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[3]/select")));
		        AccountName.selectByVisibleText("Green Cargo (GCO)");
		        driver.findElement(By.xpath(".//*[@id='button']")).click();
		        Select View = new Select(driver.findElement(By.xpath(".//*[@name='reportview']")));
		        View.selectByVisibleText("Historical Events");
				
				WebElement element=driver.findElement(By.name("columns"));
				Select se=new Select(element);
				se.selectByVisibleText("Class");
				se.selectByVisibleText("Count");
				se.selectByVisibleText("Domain");
				se.selectByVisibleText("ElementName");
				se.selectByVisibleText("Event");
				se.selectByVisibleText("FirstNotifiedAt");
				se.selectByVisibleText("InstanceDisplayName");
				se.selectByVisibleText("Owner");
				se.selectByVisibleText("Ticket");
				se.selectByVisibleText("EventText");
				se.selectByVisibleText("LastClearedAt");
				se.selectByVisibleText("snhID");
				
							
				Select timeSpan = new Select(driver.findElement(By.name("timespan")));
		        timeSpan.selectByVisibleText("Last 24 Hours");
		        
				driver.findElement(By.xpath(".//*[@id='effect']/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr/td[4]/table/tbody/tr[4]/td/a/img")).click();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindow = driver.getWindowHandles();
				for(String obj : allWindow){
					if(!(obj.equals(parentWindow))){
						driver.switchTo().window(obj);
						break;
					}
				}
				
				String [] dateFromArray = dateFrom.split("/");
				String dateFrom = dateFromArray[0];
				String monthFrom = dateFromArray[1];
				String yearFrom = dateFromArray[2];
				String monthYearFrom = monthFrom+" "+yearFrom ;

				System.out.println(dateFrom);
				System.out.println(monthFrom);
				System.out.println(yearFrom);
				System.out.println(monthYearFrom);
				
				while(!(driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/font")).getText().contains(monthYearFrom))){
					
					driver.findElement(By.xpath("//img[@alt='previous month']")).click();
					
				}
								
								
				driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[3]/td/a/font[text()='"+dateFrom+"']")).click();
				
				driver.switchTo().window(parentWindow);
				//driver.switchTo().defaultContent();
				driver.findElement(By.xpath("//*[@id='effect']/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr/td[4]/table/tbody/tr[5]/td/a/img")).click();
				
				Set<String> allWindow1 = driver.getWindowHandles();
				for(String obj : allWindow1){
					if(!(obj.equals(parentWindow))){
						driver.switchTo().window(obj);
						break;
					}
				}
				
				String [] dateToArray = dateTo.split("/");
				String dateTo = dateToArray[0];
				String monthTo = dateToArray[1];
				String yearTo = dateToArray[2];
				String monthYearTo = monthTo+" "+yearTo ;

				while(!(driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/font")).getText().contains(monthYearTo))){
					
					driver.findElement(By.xpath("//img[@alt='previous month']")).click();
					
				}
					
				
				driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[7]/td/a/font[text()='"+dateTo+"']")).click();
				
				/*  driver.findElement(By.xpath("//a[@href='javascript:set_datetime(1514746938771, true);']")).click();
		        driver.findElement(By.xpath("//a[@href='javascript:set_datetime(1516042853565, true);']")).click();*/
		        
				driver.switchTo().window(parentWindow);
		        
		        Select OutputOptions = new Select(driver.findElement(By.name("outtype")));
				OutputOptions.selectByVisibleText("Excel");
				driver.findElement(By.name("Submit")).click();
				report.endTest(logger);
				
			
				report.flush();
                
				report.close();
		
	}
	

}
