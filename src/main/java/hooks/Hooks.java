package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import methods.Methods;

public class Hooks extends Methods{
	
	Methods methds= new Methods();
	
    @Before
	public void runTest() {
    	methds.startBrowser("chrome");
	}
	
    @After
	public void after() {
		//closeBrowser();
	 }
}
