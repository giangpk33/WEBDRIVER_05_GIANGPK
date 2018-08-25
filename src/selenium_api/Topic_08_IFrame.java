package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_IFrame {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test  
	public void TC_01() {
		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//Step 02 - Close popup nếu có hiển thị 
		/*List<WebElement> iframeNotification = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if(iframeNotification.size()>0) {
			driver.switchTo().frame(iframeNotification.get(0));
			WebElement closePopup = driver.findElement(By.xpath("//div[@id='div-close']"));
			closePopup.click();
		}*/
		
		//Step 03 - Verify đoạn text được hiển thị:  What are you looking for?
		WebElement iframeMessage = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(iframeMessage);
		WebElement messageText = driver.findElement(By.xpath("//span[@id='messageText']"));
		Assert.assertEquals("What are you looking for?", messageText.getText());

		//Step 04:
			//Verify banner image được hiển thị (switch qua iframe nếu có)
		driver.switchTo().defaultContent();
		WebElement iframeBanner = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(iframeBanner);
		WebElement imageDisplay = driver.findElement(By.xpath("//div[@class='bannerimage-container']"));
		Assert.assertTrue(imageDisplay.isDisplayed());
			//Verify banner có đúng 6 images
		By image =By.xpath("//div[@class='bannerimage-container']");
		List<WebElement> imagesDisplay = driver.findElements(image);
		int img = imagesDisplay.size();
		Assert.assertEquals(img, 6);
			//Check All Image Display
		wait.until(ExpectedConditions.presenceOfElementLocated(image));
		
		//Step 05 - Verify flipper banner được hiển thị và có 8 items
		driver.switchTo().defaultContent();
		WebElement flipperBanner = driver.findElement(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertTrue(flipperBanner.isDisplayed());
			//Verify banner có đúng 8 item
		By item =By.xpath("//div[@class='flipBanner']//img[@class='front icon']");
		List<WebElement> itemDisplay = driver.findElements(item);
		int itemImg = itemDisplay.size();
		Assert.assertEquals(itemImg, 8);
	}

	//@Test
	public void TC_02() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click Here']"));
		clickHere.click();
		
		//Kiem tra parrenWindow
		String parentWindow = driver.getWindowHandle();
		
		//Switch qua tab moi-su dung only 2 window
			/*switchOnly2Window(parentWindow);
			
			String newpage = driver.getTitle();		
			Assert.assertEquals("Google", newpage);*/
		
		//Switch qua tab moi-su dung only > 2 window
		switchToWindowByTitle("Google");
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL,"https://www.google.com.vn/?gws_rd=ssl");
		
		//Switch lai ve parren window
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		String URL_ = driver.getCurrentUrl();
		Assert.assertEquals(URL_,"http://daominhdam.890m.com/");
		
		//Dong tat cac cac window lai sau khi da su dung xong
		closeAllWindow(parentWindow);
	}

	@Test	
	public void TC_03() {
		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		String ParrenWindow = driver.getWindowHandle();
		
		//Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện
		List<WebElement> closePopup = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if(closePopup.size()>0) {
			driver.switchTo().frame(closePopup.get(0));
			driver.findElement(By.xpath("//div[@id='div-close']")).click();
		}
		
		//Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Agri']")).click();			
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		String getTitle1 = driver.getTitle();
		Assert.assertEquals(getTitle1, "HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		//Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		
		String getTitle2 = driver.getTitle();
		Assert.assertEquals(getTitle2, "Welcome to HDFC Bank NetBanking");
		
		//Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
		
		WebElement frameprivacy = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(frameprivacy);
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		
		//Step 06- Click CSR link on Privacy Policy page
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		switchToWindowByTitle("HDFC BANK - CSR - Homepage");
		
		//Step 07- Back về Main window (Parent window)
		driver.switchTo().window(ParrenWindow);
		//Step 08 - Close tất cả popup khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
		closeAllWindow(ParrenWindow);
	}
	
	//Cac Ham commonfuntion
	//Switch only 2 window
	public void switchOnly2Window(String Parrent) {
		//Get tat cac cac window
		Set<String> allWindow = driver.getWindowHandles();
		//Su dung vong lap duyet qua cac window
		for(String runWindow : allWindow) {
			//Neu window hien tai khac window parren
			if(!runWindow.equals(Parrent)) {
				//tien hang switch qua va dung vong lap
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	
	//Switch only > 2 window
	public void switchToWindowByTitle(String Title) {
		//get tat cac cac window
		Set<String> AllWindow = driver.getWindowHandles();
		
		//su dung vong lap duyet qua tat ca cac window
		for(String runWindow : AllWindow) {
			
			//switch qua tung window
			driver.switchTo().window(runWindow);
			
			//get title cua tung widow
			String currentWindow = driver.getTitle();
			
			//neu nhu ma currentWindow ma bang title truyen vao thi dung vong lap
			if(currentWindow.equals(Title)) {
				break;
			}
		}
	}
	
	//Dong tat cac ca cua so lai
	public boolean closeAllWindow(String ParrentWindow) {
		//Get tat cac cac window
				Set<String> allWindow = driver.getWindowHandles();
				//Su dung vong lap duyet qua tat cac cac window
				for(String runWindow : allWindow) {
					if(!runWindow.equals(ParrentWindow)) {
						driver.switchTo().window(runWindow);
						driver.close();
					}
				}
				driver.switchTo().window(ParrentWindow);
				if(driver.getWindowHandles().size()==1) {
					return true;
				}
				else {
					return false;
				}
	}
	
	// Switch 2 window only
	

	// Switch >= 2 window only 
	
		

	@AfterClass
	public void afterClass() {
		driver.close();
	}


}
