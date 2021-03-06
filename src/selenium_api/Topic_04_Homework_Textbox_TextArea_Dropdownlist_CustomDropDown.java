
package selenium_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

public class Topic_04_Homework_Textbox_TextArea_Dropdownlist_CustomDropDown {
	WebDriver driver;
	WebDriverWait wait;

	// BY ELEMENT VARIABLE
	By nameTextbox = By.xpath("//input[@name='name']");
	By dobTextbox = By.xpath("//input[@name='dob']");
	By genderTextbox = By.xpath("//input[@name='gender']");
	By addrTextarea = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By phoneTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passTextbox = By.xpath("//input[@name='password']");

	String name, dob, addr, city, pin, state, phone, email, pass, customerID, newaddr, newcity;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// DATA TEST (INPUT)
		name = "Phan Giang";
		dob = "1996-10-20";
		addr = "PTIT HCM";
		city = "Ho Chi Minh";
		pin = "123456";
		state = "Nice District";
		phone = "0872281892";
		email = "autotest" + randomUniqueNumber() + "@gmail.com";
		pass = "123456";
		customerID = "";
		newaddr = "Malaysia";
		newcity = "Kuala lumper";

	}

	public int randomUniqueNumber() {
		Random random = new Random();
		int number = random.nextInt(100000) + 1;
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

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Input data to Add New Customer form
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

		// Get ra thông tin của Customer ID
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();

		// Verify create new customer success
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),
				addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(),
				email);

		//
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// Check field bị disable
		Assert.assertTrue(!driver.findElement(nameTextbox).isEnabled());
		Assert.assertTrue(!driver.findElement(dobTextbox).isEnabled());
		Assert.assertTrue(!driver.findElement(genderTextbox).isEnabled());

		// Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo
		// mới New Customer
		Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(addrTextarea).getText(), addr);

		// Nhập giá trị mới tại 2 field Customer Address và City > Submit
		driver.findElement(addrTextarea).clear();
		driver.findElement(cityTextbox).clear();
		driver.findElement(addrTextarea).sendKeys(newaddr);
		driver.findElement(cityTextbox).sendKeys(newcity);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// Verify giá trị tại 2 field: Customer Address và City đúng với dữ liệu sau khi
		// đã Edit thành công
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),
				newaddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(),
				newcity);

	}

	@Test
	public void TC_02_Custom_DropdownList() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
	}
	
	 public void TC04_HandleCustomDropdownList() throws InterruptedException{
		 //jquery
		 driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		 selectCustomDropdownList("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div", "Fast");
		 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text' and text()='Fast']")).isDisplayed());
		 
		 selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']//div", "19");
		 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		 
		 //angular
		 driver.get("https://material.angular.io/components/select/examples");
		 selectCustomDropdownList("//span[@class='mat-select-placeholder ng-tns-c21-4 ng-star-inserted']", "//mat-option//span", "Pizza");
		 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Pizza']")).isDisplayed());
		 
		 driver.get("https://material.angular.io/components/select/examples");
		 selectCustomDropdownList("//mat-select[@placeholder='Panel color']", "//mat-option/span", "Green");
		 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Green']")).isDisplayed());
		 
		 //kendo
		 driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		selectCustomDropdownList("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']//li", "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[text()='Grey']")).isDisplayed());
	
		selectCustomDropdownList("//span[@aria-owns='size_listbox']", "//ul[@id='size_listbox']/li", "XL - 7 5/8\"");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='size_listbox']//span[@class='k-input' and text()='XL - 7 5/8\"']")).isDisplayed());
		 
		//VueJS 
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectCustomDropdownList("//div[@class='btn-group']", "//ul[@class='dropdown-menu']//li", "Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='btn-group']//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
		
		
	 }

	public void selectCustomDropdownList(String dropdown, String listitem, String valueitem)
			throws InterruptedException {

		// click vào dropdown
		WebElement element = driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath(dropdown)).click();
		// Lấy tất cả các phần tử trong dropdown vào 1 list
		List<WebElement> allitem = driver.findElements(By.xpath(listitem));
		
		wait.until(ExpectedConditions.visibilityOfAllElements(allitem));
		for (WebElement item : allitem) {
			if (item.getText().equals(valueitem)) {
				// scroll
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				// nếu item.getText() == actual thì click
				item.click();
				// Thread.sleep(3000);
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
