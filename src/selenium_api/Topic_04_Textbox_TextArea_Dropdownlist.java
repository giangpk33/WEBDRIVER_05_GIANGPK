package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Textbox_TextArea_Dropdownlist {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC() {
		//Case 01 - Nếu giá trị nó nằm trong attribute -> getAttribute("attributeName");
		String nameValue = driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value");
		
		
		// Case 02 - Giá trị nằm trong text (ko nằm trong attribute) getText();
		String addressValue = driver.findElement(By.xpath("//textarea[@name='addr']")).getText();
	}

	@Test
	public void TC_01() {
		driver.get("http://daominhdam.890m.com/");
		Select jobRole_1 = new Select(driver.findElement(By.id("job1")));
		
		
		/*<select id="job1" name="user_job1">
        	<option value="automation">Automation Tester</option>
        	<option value="manual">Manual Tester</option>
        	<option value="website">Website Tester</option>
        	<option value="mobile">Mobile Tester</option>
        	<option disabled="disabled" value="disabled">Dropdown disable</option>
        </select>*/
		
		//TH1: index: Chỉ số 0/1/2/3/
		//Đã chọn text ở option  Manual Tester
		jobRole_1.selectByIndex(1); 
		
		//TH2
		//Lấy theo giá trị nằm trong attribute value
		jobRole_1.selectByValue("manual");
		
		//TH3
		//Lấy theo text, khuyến khích làm theo cách này
		jobRole_1.selectByVisibleText("Automation Tester");
		
		//Nếu dropdown nào hỗ trợ multi-select, có hàm:
		jobRole_1.deselectAll();
		
		
		//Được dùng nhiều, Lấy ra giá trị vừa được chọn
		String text = jobRole_1.getFirstSelectedOption().getText();
		
		//Dropdown có bao nhiêu item (VN countries 54 tỉnh thành)
		int cityItem = jobRole_1.getOptions().size();
		//54
		
		//Kiểm tra xem dropdown list có cho chọn nhiều hay không
		Assert.assertTrue(jobRole_1.isMultiple());
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
