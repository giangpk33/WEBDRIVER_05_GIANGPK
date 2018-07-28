package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_CheckNavigatePage() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account'")).click();
		
		driver.navigate().back();
		//Về lại trang login thành công
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
		
		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginUrl,"http://live.guru99.com/index.php/customer/account/login/" );
		
		driver.navigate().forward();
		
		//Về lại page register thành công
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
		
		String registerUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerUrl,"http://live.guru99.com/index.php/customer/account/create/" );
	}


	@Test
	public void TC_02_LoginEmpty() {
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("send2")).click();
		
		String emailInvalidMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		String passwordInvalidMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		
		Assert.assertEquals(emailInvalidMessage, "This is a required field.");
		Assert.assertEquals(passwordInvalidMessage, "This is a required field.");
	}

	@Test
	public void TC_03_LoginWithEmailInvalid() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("send2")).click();
		
		String emailInvalidMessage = driver.findElement(By.id("advice-validate-email-email")).getText();

		Assert.assertEquals(emailInvalidMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		String passwordInvalidMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();

		Assert.assertEquals(passwordInvalidMessage, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_05_CreateAnAccount() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Create an Account')]")).click();
		driver.findElement(By.id("firstname")).sendKeys("Giang");
		driver.findElement(By.xpath("//input[@name='middlename']")).sendKeys("Ki");
		driver.findElement(By.cssSelector("input[title='Last Name']")).sendKeys("Phan");
		driver.findElement(By.xpath("//div[@class='fieldset']//input[@type='email']")).sendKeys("automation" +randomEmail() + "@gmail.com" );
		driver.findElement(By.xpath("//input[starts-with(@title,  'Password')]")).sendKeys("123456");
		driver.findElement(By.xpath("//input[starts-with(@title,  'Confirm Password')]")).sendKeys("123456");
		driver.findElement(By.xpath("//span[text() = 'Register']")).click();
		driver.findElement(By.xpath("//span[text() = 'Thank you for registering with Main Website Store.']"));
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		try {
			TimeUnit.SECONDS.sleep(10);
			String currentUrl = driver.getCurrentUrl();
			Assert.assertEquals(currentUrl, "http://live.guru99.com/index.php/");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(100000);
		return number;
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
