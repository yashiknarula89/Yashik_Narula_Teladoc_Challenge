package stepDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Webtable {

	public void test() {
		
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
		// WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.edge.driver", "path/to/msedgedriver");
		//WebDriver driver = new EdgeDriver();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.way2automation.com/angularjs-protractor/webtables/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]"));
		List<WebElement> rows=driver.findElements(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]/tbody/tr"));
		System.out.println("No. of rows: "+rows.size());
        
		List<WebElement> columns=driver.findElements(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]/tbody/tr[0]/td"));
		System.out.println("No. of columns: "+columns.size()); 
	}
	
}
