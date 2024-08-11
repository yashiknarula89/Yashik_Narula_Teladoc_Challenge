package hooks;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	    driver.get("https://www.way2automation.com/angularjs-protractor/webtables/");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.xpath("//button[contains(.,' Add User')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//table/tbody//td/input[@name='FirstName']")).sendKeys("Yashik");
		driver.findElement(By.xpath("//table/tbody//td/input[@name='LastName']")).sendKeys("Narula");
		driver.findElement(By.xpath("//table/tbody//td/input[@name='UserName']")).sendKeys("yash123");
		driver.findElement(By.xpath("//table/tbody/tr/td/input[@name='Password']")).sendKeys("abcde");
		driver.findElement(By.xpath("//label[text()='Company AAA']//preceding-sibling::input")).click();
		WebElement eleRole=driver.findElement(By.xpath("//table/tbody//td/*[@name='RoleId']"));
		new Select(eleRole).selectByVisibleText("Admin");
		driver.findElement(By.xpath("//table/tbody/tr/td/input[@name='Email']")).sendKeys("yashiknarula89@gmail.com");
		driver.findElement(By.xpath("//table/tbody/tr/td/input[@name='Mobilephone']")).sendKeys("7082083258");
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
	
	}

}
