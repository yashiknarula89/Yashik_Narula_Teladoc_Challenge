package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import methods.Methods;

public class WebtableSteps extends Methods{
	
	Methods methds= new Methods();

	@Given("^Verify the user navigated to webtable page$")
	public void verify_the_user_navigated_to_webtable_page() {
		methds.currentPage();
	}

	@When("^Adding a user (.*),(.*),(.*),(.*)$")
	public void adding_a_user(String frst,String lst,String role,String email) {
		methds.addUser(frst,lst,role,email);
	}

	@Then("^Verify user is added to the table$")
	public void verify_user_is_added_to_the_table() {
		methds.verifyUserIsAdded();
	}

	@When("^Delete the user novak$")
	public void delete_the_user_novak() {
		methds.deleteUser();
	}

	@Then("^Verify user is deleted from the table$")
	public void verify_user_is_deleted_from_the_table() {
		methds.verifyUserIsdeleted();
	}

	@Then("^Close the browser$")
	public void close_the_Browser() {
		methds.closeBrowser();
	}
}
