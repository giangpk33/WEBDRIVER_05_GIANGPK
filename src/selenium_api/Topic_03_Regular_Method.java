package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Regular_Method {

	WebDriver driver;
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Test() {
		
	//WebBrowser (refresh, navigate, maximize, foward, back)
		//Mở 1 page url lên
		driver.get("https://google.com");
		
		//Trả về url của page hiện tại
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "expected");
		
		//Trả về source code của page hiện tại
		String course_page = driver.getPageSource();
		
		//Trả về Title của page hiện tại
		String title = driver.getTitle();
		
		//Trả về page id (GUID) của page hiện tại -> Windows
		String parent_ID = driver.getWindowHandle();
		
		//Trả về page id của tất cả các page -> Windows
		driver.getWindowHandles();
		
		//Đóng Browser, chỉ đóng cái tab hiện tại (1 tab = đóng browser)
		driver.close();
		
		//Đóng Browser, nhiều tab = đóng browser
		driver.quit();
		
		//Tương tác duy nhất với 1 element
		//Nếu không tìm thấy element -> Fail -> Ném ra ngoại lệ No Such element
		WebElement emailTextBox = driver.findElement(By.id("email"));
		emailTextBox.click();

		//Tương tác với 1 list element
		//Nếu không tìm thấy element -> trả về 1 list empty element
		List<WebElement> elements = driver.findElements(By.id("email"));
		
		//Wait cho page hiện tại được render thành công (DOM: html/css/js/jquery/ajax...)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		//Những phần làm Auto cho GUI 
		//Selenium chỉ mạnh về Functional GUI
		//Suggested GalenFramework mạnh về GUI: Chạy được nhiều browser, tích hợp được code selenium
		
		//Tương ứng F11, full screen
		driver.manage().window().fullscreen();
		
		//0-0 Điểm trên cùng bên trái
		//1366 x 768
		//1920 x 1080-> 1k
		//2048 x 1920 -> 2k
		//4096 x 2048 -> 4k
		//Check vị trí của browser nằm trong độ phân giải OS là bao nhiêu 
		//40 x 500
		driver.manage().window().getPosition();
		
		//Chiều rộng và chiều cao của Browser là bao nhiêu
		driver.manage().window().getSize();
	    
		//Open browser lên -> full browser ( không phải full screen)
		driver.manage().window().maximize();
		
		//Muốn back lại page cũ
		driver.navigate().back();
		
		//Đang ở Page 1, back về page trước, sau đó lại forward về page 1
		driver.navigate().forward();
		
		//F5 lại page, reload page
		driver.navigate().refresh();
		
		//Luôn chờ page render/load thành công trong khoảng timeout của selenium
		driver.get("");
		
		//Không chờ load thành công trong khoảng timeout của selenium
		driver.navigate().to("");
		
		//Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		alert.dismiss();
		alert.sendKeys("");
		String abc = alert.getText();
		
		//Frame/ iframe
		driver.switchTo().frame("");
		driver.switchTo().defaultContent();
		
		
	//WEBELEMENT (TEXTBOX, TEXT AREA. DROPDOWN LIST,..)
		
		WebElement email_Textbox = driver.findElement(By.id("email"));
		
		//Trả về empty data cho textbox, text are, dropdown..
		email_Textbox.clear();
		
		//Nhập text, value vào các element như textbox, text are, dropdown
		email_Textbox.sendKeys("");
		
		//Tất cả các hàm get đều trả về String ngoại trừ driver.get("");
		//Trả lại giá trị nằm trong attribute của element
		String value = email_Textbox.getAttribute("");
		
		//Auto GUI
		String background_color = email_Textbox.getCssValue("background-color");
		
		//Trả về vị trí của element trong màn hình
		email_Textbox.getLocation();
		
		//Trả về chiều rộng và chiều cao của element trong màn hình
		email_Textbox.getSize();
		
		//Trả về Text của element
		String text_email = email_Textbox.getText();
		
		//Kiểm tra Xem 1 element có hiển thị hay không. phạm vi dùng cho all element
		Assert.assertTrue(email_Textbox.isDisplayed());
		
		//Kiểm tra Xem 1 element có enable/ disable hay không. phạm vi dùng cho element có khả năng bị disable.
		//Đó là những element có khả năng tương tác được như textbox, textarea, dropdown, radio, checkbox
		Assert.assertTrue(email_Textbox.isEnabled());
		
		//Kiểm tra Xem 1 element có được chọn hay không. phạm vi dùng cho checkbox, radio (dropdown có riêng 1 API support rồi)
		Assert.assertTrue(email_Textbox.isSelected());
		
		//Click vào button, radio, link, image, check box, dropdown,...
		email_Textbox.click();
		
		//Gửi sự kiện ENTER cho element mà nó thao tác
		//Form Login, Search, Register
		email_Textbox.submit();
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
