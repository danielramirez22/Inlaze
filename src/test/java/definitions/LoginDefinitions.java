package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.LoginSteps;

public class LoginDefinitions extends LoginSteps {

    LoginSteps loginSteps;


    @When("Fills the email and password fields")
    public void fills_the_email_and_password_fields(DataTable parameters){
        loginSteps.fillsForm(parameters.cells());
    }

    @And("Click on sign in button")
    public void click_on_sign_in_button(){
        loginSteps.clickOnSignIn();
    }

    @Then("Verify login is successful")
    public void verify_login_is_successful (){
        loginSteps.activeSession();
    }

    @Then("Verify if sign in button is not enabled")
    public void verify_if_sign_in_button_is_not_enabled (){
        loginSteps.veryfyButton();
    }

    @And("Click on profile image and logout")
    public void click_on_profile_image_and_logout (){
        loginSteps.logoutFromInlaze();
    }

    @Then("Verify if log out was completed")
    public void verify_if_log_out_was_completed (){
        loginSteps.verifyLogoutFromInlaze();
    }

    @Given("The user enters into the page login")
    public void the_user_enters_into_the_page_login() {
        loginSteps.openPage();
    }
}
