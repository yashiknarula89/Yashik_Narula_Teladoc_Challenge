package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import methods.Methods;

public class Webtable extends Methods{

	@Given("Verify the user navigated to webtable page")
	public void verify_the_user_navigated_to_webtable_page() {
		currentPage();
	}

	@When("Adding a user (.*),(.*),(.*)(.*)(.*)")
	public void adding_a_user(String frst,String lst,String role, String email,String phone) {
	    // Write code here that turns the phrase above into concrete actions
		addUser(frst, lst, role, email, phone);
	}

	@Then("Verify user is added to the table")
	public void verify_user_is_added_to_the_table() {
	    // Write code here that turns the phrase above into concrete actions
		verifyUserIsAdded();
	}

	@When("Delete the user novak")
	public void delete_the_user_novak() {
	    // Write code here that turns the phrase above into concrete actions
	    deleteUser();
	}

	@Then("Verify user is deleted from the table")
	public void verify_user_is_deleted_from_the_table() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
}
