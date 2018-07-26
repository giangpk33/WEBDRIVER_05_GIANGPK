package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_WebElement_Browser {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC_01_isDisplay() throws Exception {
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));

		Assert.assertTrue(isControlDisplayed(emailTextbox));
		Assert.assertTrue(isControlDisplayed(ageRadioButton));
		Assert.assertTrue(isControlDisplayed(educationTextArea));

		if (isControlDisplayed(emailTextbox) && isControlDisplayed(educationTextArea)) {
			emailTextbox.sendKeys("Automation Testing");
			educationTextArea.sendKeys("Automation Testing");
			ageRadioButton.click();
		}
		Thread.sleep(5000);
		;
	}

	public boolean isControlDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	@Test
	public void TC_02_isEnable() {
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement passwwordTextbox = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
		WebElement jobRole02ddl = driver.findElement(By.xpath("//select[@id='job2']"));
		WebElement interestsCheckbox = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		WebElement slider2Text = driver.findElement(By.xpath("//input[@id='slider-2']"));
		WebElement buttonDisable = driver.findElement(By.cssSelector("#button-disabled"));

		isControlEnabled(emailTextbox);
		isControlEnabled(passwwordTextbox);
		isControlEnabled(ageRadioButton);
		isControlEnabled(educationTextArea);
		isControlEnabled(jobRole02ddl);
		isControlEnabled(interestsCheckbox);
		isControlEnabled(slider2Text);
		isControlEnabled(buttonDisable);
	}

	@Test
	public void TC_03_isSelected() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement interestsCheckbox = driver.findElement(By.xpath("//input[@id='development']"));

		ageRadioButton.click();
		interestsCheckbox.click();

		if (isSelected(ageRadioButton)) {
			System.out.println("Element is selected");
		} else {
			ageRadioButton.click();
		}
		if (isSelected(interestsCheckbox)) {
			System.out.println("Element is selected");
		} else {
			interestsCheckbox.click();
		}

	}

	public boolean isSelected(WebElement element) {
		return element.isSelected();
	}

	public void isControlEnabled(WebElement element) {
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
