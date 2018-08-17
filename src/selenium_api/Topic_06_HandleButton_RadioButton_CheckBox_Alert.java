package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_HandleButton_RadioButton_CheckBox_Alert {
	 WebDriver driver;
	    
		@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
//			driver = new FirefoxDriver();
//			driver.get("http://demo.guru99.com/v4/");
//			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//			driver.manage().window().maximize();
		}

		//@Test
		public void TC_01() {
			driver.get("http://live.guru99.com/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			
			//Check Form Login
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-login']")).isDisplayed());
			
			//Click Create Account - Su dung Javascrip
			clickElementByJavascrip("//span[text()='Create an Account']");
			
		}
		
		//@Test
		public void TC_02() {
			driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
			
			String Check_Dual_zone = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
			clickElementByJavascrip(Check_Dual_zone);
			
			//Kiem tra checkbox da duoc chon
			Assert.assertTrue(isSelect(Check_Dual_zone));
			
			//Uncheck va kiem tra lai
			unCheck(Check_Dual_zone);
			Assert.assertFalse(isSelect(Check_Dual_zone));
			
		}
		
		//@Test
		public void TC_03() {
			driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
			
			String Radio_Diesel = "//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input";
			clickElementByJavascrip(Radio_Diesel);
			
			//Kiem tra da duoc chon chua
			Assert.assertTrue(isSelect(Radio_Diesel));
		}
		
		//@Test
		public void TC_04() {
			driver.get("http://daominhdam.890m.com/");
			
			driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
			
			//Ham Alert
			Alert alert = driver.switchTo().alert();
			
			//Check text
			Assert.assertEquals(alert.getText(), "I am a JS Alert");
			alert.accept();
			//Veryfy
			Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).getText(), "You clicked an alert successfully");
			
		}
		
		//@Test
		public void TC_05() {
			driver.get("http://daominhdam.890m.com/");
			
			driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
			
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), "I am a JS Confirm");
			
			alert.dismiss();
			Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).getText(), "You clicked: Cancel");
		}
		
		@Test
		public void TC_06() {
			driver.get("http://daominhdam.890m.com/");
			
			driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
			
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), "I am a JS prompt");
			
			alert.sendKeys("Autotest");
			alert.accept();
			Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Autotest");
		}
		
		
		//Ham Click
		public void clickElementByJavascrip(String localtion) {
			WebElement element = driver.findElement(By.xpath(localtion));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", element);
		}	
		
		//Ham kiem tra checkbox duoc chon
		public boolean isSelect(String location) {
			WebElement element = driver.findElement(By.xpath(location));
			return element.isSelected();
		}
		
		//Ham Uncheck
		public void unCheck(String location) {
			if(isSelect(location)) {
				clickElementByJavascrip(location);
			}
		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
