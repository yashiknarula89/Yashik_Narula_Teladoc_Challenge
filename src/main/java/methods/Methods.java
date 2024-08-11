package methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
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
	String setUsrnm,setPwrd,setRole,custValue,phone;
	public Properties prop;
	WebElement eleRole,frame,table,row,cell,table1,row1,cell1;
	List<WebElement> rows,cells,rows1,cells1;
	int colNum;
	
	public Methods() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			url = prop.getProperty("url"); 
			locatorUsrnm = prop.getProperty("username"); locatorpswrd = prop.getProperty("password"); locatorfstnm = prop.getProperty("firstName");
			locatorlstnm = prop.getProperty("lastName"); locatorCust = prop.getProperty("customer"); locatorRole = prop.getProperty("role");
			locatorEmail = prop.getProperty("email"); locatorPhn = prop.getProperty("phone"); locatorAddusr = prop.getProperty("addUser");
			setUsrnm = prop.getProperty("setUsername"); setPwrd = prop.getProperty("setPassword"); locatorFrame = prop.getProperty("frame");
			locatorelefrm = prop.getProperty("framele"); locatorSave= prop.getProperty("save"); setRole= prop.getProperty("selRole");
			custValue= prop.getProperty("setCust"); phone= prop.getProperty("setPhone");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
   
     //****** Open the browser and navigate to url *******//
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
		Assert.assertEquals(currentUrl,url);
		System.out.println("Used navigated to webtable page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		minimizeFrame();
	}

	//****** Adding the user *******//
	public void addUser(String frst,String lst, String role,String email) {
		try {
			driver.findElement(By.xpath(locatorAddusr)).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath(locatorfstnm)).sendKeys(frst);
			driver.findElement(By.xpath(locatorlstnm)).sendKeys(lst);
			driver.findElement(By.xpath(locatorUsrnm)).sendKeys(setUsrnm);
			driver.findElement(By.xpath(locatorpswrd)).sendKeys(setPwrd);
			driver.findElement(By.xpath(locatorCust)).click();
			eleRole=driver.findElement(By.xpath(locatorRole));
			new Select(eleRole).selectByVisibleText(setRole);
			driver.findElement(By.xpath(locatorEmail)).sendKeys(email);
			driver.findElement(By.xpath(locatorPhn)).sendKeys(phone);
			Thread.sleep(2000);
			driver.findElement(By.xpath(locatorSave)).click();
		}catch(NoSuchFrameException e) {
			System.out.println(e.getMessage());
		}catch(WebDriverException e) {
			System.out.println(e.getMessage());
		}catch(InterruptedException e){
			System.out.println(e.getMessage());
		}
	}
    
	//****** To verify user is added *******//
	public void verifyUserIsAdded() {
		//driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		table=driver.findElement(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]"));
		rows=table.findElements(By.xpath("//tbody/tr"));
		for(int i=0;i<rows.size();i++){
			row=rows.get(i);
			cells=row.findElements(By.cssSelector("td[class='smart-table-data-cell']"));
			for(int j=0;j<cells.size()-3;j++) { 
				cell=cells.get(j);
				if(cell.getText().equals(setUsrnm)){
					System.out.println("New User " +cell.getText()+" added to the table and available in the row:"+ ++i);
					--i;
				}
			}
		}
	}
 
	//****** Delete user *******//
	public void deleteUser() {
		table1=driver.findElement(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]"));
		rows1=table1.findElements(By.xpath("//tbody/tr"));
		for(int i=0;i<rows1.size();i++){
			row1=rows1.get(i);
			cells1=row1.findElements(By.cssSelector("td[class='smart-table-data-cell']"));
			for(int j=0;j<cells1.size()-3;j++) {
				cell1=cells1.get(j);
				if(cell1.getText().equals("novak")){
					colNum=++j;
					System.out.println("User is available in row: "+ ++i);
					table1.findElement(By.xpath("//tbody/tr["+i+"]/td//*[contains(@class,'icon icon-remove')]")).click();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					driver.findElement(By.xpath("//button[.='OK']")).click();
					System.out.println("User is deleted");
					break;
				}
			}
		}
	}
 
	//****** To verify user is deleted *******//
	public void verifyUserIsdeleted() {
		WebElement table2=driver.findElement(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]"));
		List<WebElement> upRows=table2.findElements(By.xpath("//tbody/tr"));
		for(int i=1;i<=upRows.size();i++){
			WebElement user=table2.findElement(By.xpath("//tbody/tr["+i+"]/td["+colNum+"]"));
			if(user.getText().equals("novak")){
				Assert.fail("User is not deleted");
			}	
		}
		System.out.println("User is deleted from the table");
	}
	
	public void minimizeFrame() {
		try {
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));
			frame=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorFrame)));
			if(frame.isDisplayed()) {
			driver.switchTo().frame(frame);
			driver.findElement(By.xpath(locatorelefrm)).click();
			driver.switchTo().defaultContent();
			}
			else {
				System.out.println("Frame is not visible.");
			}
		} catch (NoSuchFrameException e) {
			System.out.println(e.getMessage());
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		} 
	}

	public void closeBrowser() {
		driver.close();
	}

}
