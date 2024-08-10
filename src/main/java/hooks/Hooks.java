package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import methods.Methods;
public class Hooks extends Methods {
 
    @Before
	public void runTest() {
     startBrowser("chrome");
	}
	
    @After
	public void after() {
		closeBrowser();
	 }
}
