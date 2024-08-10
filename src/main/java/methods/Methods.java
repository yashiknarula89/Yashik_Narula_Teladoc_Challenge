package methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Methods {

	public static WebDriver driver;
	public String url;
	public String locatorAddusr,locatorUsrnm,locatorpswrd,locatorfstnm,locatorlstnm,locatorCust,
	locatorRole,locatorEmail,locatorPhn,locatorFrame,locatorelefrm,locatorSave;
	String setUsrnm,setPwrd,setRole,custValue;
	public Properties prop;
	WebElement eleRole,frame,table,row,cell;
	public WebDriverWait wait;
	List<WebElement> rows,cells;

	public Methods() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			url = prop.getProperty("url");
			locatorUsrnm = prop.getProperty("username"); locatorpswrd = prop.getProperty("password"); locatorfstnm = prop.getProperty("firstName");
			locatorlstnm = prop.getProperty("lastName"); locatorCust = prop.getProperty("customer"); locatorRole = prop.getProperty("role");
			locatorEmail = prop.getProperty("email"); locatorPhn = prop.getProperty("phone"); locatorAddusr = prop.getProperty("addUser");
			setUsrnm = prop.getProperty("setUsername"); setPwrd = prop.getProperty("setPassword"); locatorFrame = prop.getProperty("frame");
			locatorelefrm = prop.getProperty("framele"); locatorSave= prop.getProperty("save"); setRole= prop.getProperty("selRole");custValue= prop.getProperty("setCust");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		}
		else {
			System.out.println("Check for other browser");
		}
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void currentPage() {
		String currentUrl=driver.getCurrentUrl();
		System.out.println("Current URL is: "+currentUrl);
		Assertions.assertEquals(currentUrl,url);
		System.out.println("Used navigated to webtable page");
	}

	public void addUser(String frst,String lst, String role,String email,String phone){
		try {
			driver.findElement(By.xpath(locatorAddusr)).click();
			Thread.sleep(3000);
			//frame=driver.findElement(By.xpath(locatorFrame));
			minimizeFrame();
			driver.findElement(By.tagName(locatorfstnm)).sendKeys(frst);
			driver.findElement(By.tagName(locatorlstnm)).sendKeys(lst);
			driver.findElement(By.tagName(locatorUsrnm)).sendKeys(setUsrnm);
			driver.findElement(By.tagName(locatorpswrd)).sendKeys(setPwrd);
			driver.findElement(By.tagName(locatorCust)).click();
			eleRole=driver.findElement(By.tagName(locatorRole));
			driver.findElement(By.tagName(locatorRole)).click();
			new Select(eleRole).selectByVisibleText(setRole);
			driver.findElement(By.tagName(locatorEmail)).sendKeys(email);
			driver.findElement(By.tagName(locatorPhn)).sendKeys(phone);
			driver.findElement(By.xpath(locatorSave)).click();
			
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}catch(WebDriverException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyUserIsAdded() {
		table=driver.findElement(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]"));
		rows=table.findElements(By.xpath("//tbody/tr"));
		for(int i=0;i<rows.size();i++){
			row=rows.get(i);
			cells=row.findElements(By.cssSelector("td[class='smart-table-data-cell']"));
			for(int j=0;j<cells.size()-3;j++) { 
				cell=cells.get(j);
				if(cell.getText().equals(setUsrnm)){
				System.out.println("New User " +cell.getText()+" added to the table and available in the row:"+ ++i);
				}
			}
		}
	}

	public void deleteUser() {
		
	}
	
	public void minimizeFrame() {
		try {
			frame=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorFrame)));
			driver.switchTo().frame(frame);
			driver.findElement(By.xpath(locatorelefrm)).click();
			driver.switchTo().defaultContent();
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		} catch (WebDriverException e) {
			e.printStackTrace();
		} 
	}

	
	
	public void closeBrowser() {
		driver.close();
	}




}
