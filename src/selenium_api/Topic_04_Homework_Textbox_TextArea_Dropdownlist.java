
package selenium_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

public class Topic_04_Homework_Textbox_TextArea_Dropdownlist {
	WebDriver driver;
	
	//BY ELEMENT VARIABLE
	By nameTextbox = By.xpath("//input[@name='name']");
	By dobTextbox  = By.xpath("//input[@name='dob']");
	By genderTextbox  = By.xpath("//input[@name='gender']");
	By addrTextarea = By.xpath("//textarea[@name='addr']");
	By cityTextbox  = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox  = By.xpath("//input[@name='pinno']");
	By phoneTextbox  = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passTextbox  = By.xpath("//input[@name='password']");
	
	String name, dob, addr, city, pin, state, phone, email, pass, customerID, newaddr, newcity;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//DATA TEST (INPUT)
		name = "Phan Giang";
		dob  = "1996-10-20";
		addr = "PTIT HCM";
		city = "Ho Chi Minh";
		pin = "123456";
		state = "Nice District";
		phone = "0872281892";
		email = "autotest" + randomUniqueNumber() +"@gmail.com";
		pass = "123456";
		customerID = "";
		newaddr = "Malaysia";
		newcity = "Kuala lumper";
		
	}

	public int randomUniqueNumber() {
		Random random = new Random();
	    int number =	random.nextInt(100000) + 1;
		return number;
	}
	
	@Test(enabled = false)
	public void TC_01_DropdownList() {
		driver.get("http://daominhdam.890m.com/");
		Select jobRole_1 = new Select(driver.findElement(By.id("job1")));
		
		Assert.assertTrue(!jobRole_1.isMultiple());
		
		jobRole_1.selectByVisibleText("Automation Tester");
		Assert.assertEquals(jobRole_1.getFirstSelectedOption().getText(), "Automation Tester");
		
		jobRole_1.selectByValue("Manual Tester");
		Assert.assertEquals(jobRole_1.getFirstSelectedOption().getText(), "Manual Tester");
		
		jobRole_1.selectByIndex(3);
		Assert.assertEquals(jobRole_1.getFirstSelectedOption().getText(), "Mobile Tester");
		
		int countList = jobRole_1.getOptions().size();
		Assert.assertEquals(countList, 5);
	}
	
	@Test(enabled = false)
	public void TC_03_Textbox_TextArea() {
		driver.get("http://demo.guru99.com/v4/");
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr145616");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("eqYmUza");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		//Input data to Add New Customer form
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(dobTextbox).sendKeys(dob);
		driver.findElement(addrTextarea).sendKeys(addr);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passTextbox).sendKeys(pass);
		
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		//Get ra thông tin của Customer ID
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		
		//Verify create new customer success
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), email);
		
		//
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		//Check field bị disable
		Assert.assertTrue(!driver.findElement(nameTextbox).isEnabled());
		Assert.assertTrue(!driver.findElement(dobTextbox).isEnabled());
		Assert.assertTrue(!driver.findElement(genderTextbox).isEnabled());
		
		
		//Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer 
		Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(addrTextarea).getText(), addr);
		
		//Nhập giá trị mới tại 2 field Customer Address và City > Submit
		driver.findElement(addrTextarea).clear();
		driver.findElement(cityTextbox).clear();
		driver.findElement(addrTextarea).sendKeys(newaddr);
		driver.findElement(cityTextbox).sendKeys(newcity);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		//Verify giá trị tại 2 field: Customer Address và City đúng với dữ liệu sau khi đã Edit thành công
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), newaddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), newcity);
		
	}

	@Test
	public void TC_02_Custom_DropdownList() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		Select numberButton = new Select(driver.findElement(By.xpath("//select[@id='number']"))) ;
		Thread.sleep(10000);
		List<WebElement> numbers = numberButton.getOptions();
	
		for (WebElement webElement : numbers) {
			if(webElement.getText().equals("19")) {
			    numberButton.selectByVisibleText("19");
			}
		}
		
		Assert.assertEquals(numberButton.getFirstSelectedOption().getText(), "19");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
