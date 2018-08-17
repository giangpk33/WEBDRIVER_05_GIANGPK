package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_UserInterface {
WebDriver driver;
    
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01() throws Exception {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement Tooltip = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		Actions action = new Actions(driver);
		action.moveToElement(Tooltip).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).getText(), "Hooray!");
	}

	//@Test
	public void TC_02() {
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement HoverLogin=driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		Actions action = new Actions(driver);
		action.moveToElement(HoverLogin).perform();
		
		driver.findElement(By.xpath("//a[@data-track='login']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	}

	//@Test
	public void TC_03() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		
		List <WebElement> listitem = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		Actions action = new Actions(driver);
		action.clickAndHold(listitem.get(0)).moveToElement(listitem.get(3)).release().perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@id='selectable']//li[@class='ui-state-default ui-selectee ui-selected']")).isDisplayed());
	}
	
	//@Test
	public void TC_03_02() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		
		List <WebElement> listitem = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		Actions action = new Actions(driver);
		
		action.keyDown(Keys.CONTROL).build().perform();
		listitem.get(0).click();
		listitem.get(5).click();
		listitem.get(8).click();
		listitem.get(11).click();
		
		Thread.sleep(4000);
		action.keyUp(Keys.CONTROL).build().perform();
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@id='selectable']//li[@class='ui-state-default ui-selectee ui-selected']")).isDisplayed());
	}

	//@Test
	public void TC_04_Double_Click() throws Exception{
		driver.get("http://www.seleniumlearn.com/double-click");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		
		WebElement dobleclick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		action.doubleClick(dobleclick).perform();
		
		Alert alert =driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();
	}
	
	//@Test
	public void TC_05_Right_Click() throws Exception {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		
		WebElement righclick = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(righclick).perform();
		
		WebElement quit = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.moveToElement(quit).perform();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]//span[text()='Quit']")),"Quit");
		
		quit.click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	//@Test
	public void TC_06_dragAndDrop(){
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Assert.assertEquals(target.getText(), "Drag the small circle here.");
		
		Actions action = new Actions(driver);		
		
		action.dragAndDrop(source, target).perform();
		
		Assert.assertEquals(target.getText(), "You did great!");
		
	}
	
	@Test
	public void TC_06_dragAndDrop_2(){
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droppable']"));
		
		Assert.assertEquals(target.getText(), "Drop here");
		
		Actions action = new Actions(driver);		
		
		action.dragAndDrop(source, target).perform();
		
		Assert.assertEquals(target.getText(), "Dropped!");
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
		

}
