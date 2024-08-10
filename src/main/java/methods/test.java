package methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test {
	//static WebElement row;
	//static List<WebElement> cells;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		List<String> newUsrValues=new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver.get("https://www.way2automation.com/angularjs-protractor/webtables/");
		driver.manage().window().maximize();
		Collections.addAll(newUsrValues,"sale","sale","matt","Company BBB","Customer","sales@comp.com","11122233");
		System.out.println(newUsrValues);
		WebElement table=driver.findElement(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]"));
		List<WebElement> rows=table.findElements(By.xpath("//tbody/tr"));
		//List<WebElement> columns=driver.findElements(By.xpath("//table[contains(@class,'smart-table')][contains(@class,'table')]/tbody/tr[0]/td"));
		for(int i=0;i<rows.size();i++){
			WebElement row=rows.get(i);
			 List<WebElement> cells=row.findElements(By.cssSelector("td[class='smart-table-data-cell']"));
			 for(int j=0;j<cells.size()-3;j++) {
					WebElement cell=cells.get(j);
					if(cell.getText().equals("Mark")||cell.getText().equals("novak")) {
					System.out.println("New User " +cell.getText()+" added to the table and available in the table row:"+ ++i);
					i--;
					}
					if(cell.getText().equals("Novak")){
						List<WebElement> ele=table.findElements(By.xpath("//thead/tr[3]/th[@class='smart-table-header-cell']"));
						for(int k=0;k<ele.size();k++) {
							WebElement headrow=ele.get(k);
							if(headrow.getText().equals("E-mail")) {
								System.out.println(headrow.getText()+" is available at index "+ ++k);
							}
						}
					}
				}
		}
	}

}
