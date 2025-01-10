package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import steps.RegisterSteps;

import java.util.List;

public class RegisterDefinitions {

    private List<List<String>> params;

    @Steps
    private RegisterSteps registerSteps;

    @Given("The user enters into the page")
    public void theUserEntersIntoThePage() {
        registerSteps.openPage();
    }

    @When("The user fills the form")
    public void the_user_fills_the_form(DataTable parameters) {
        params = parameters.cells();
        registerSteps.fillsForm(params);
    }

    @And("Click on sign up button")
    public void click_on_sign_up_button() throws InterruptedException {
        registerSteps.clickSignUp();
    }

    @Then("Verify account creation")
    public void verify_account_creation() {
        registerSteps.accountCreation();
    }

    @Then("Validate number of words")
    public void validate_number_of_words() {
        registerSteps.numberOfWords();
    }

    @Then("Validate the account does not be created")
    public void validate_the_account_does_not_be_created() {
        registerSteps.accountDoesNotBeCreated();
    }

    @Then("Verify if sign up button is not enabled for email")
    public void verify_if_sign_up_button_is_not_enabled_for_email() {
        registerSteps.signUpButtonIsNotEnabledForEmail(params);
    }

    @When("The user fills the password fields")
    public void the_user_fills_the_password_fields(DataTable parameters) {
        registerSteps.userFillsPasswordFields(parameters.cells());
    }

    @Then("Validate if the passwords match")
    public void validate_if_the_passwords_match(DataTable parameters) {
        registerSteps.passwordMessage(parameters.cells());
    }

    @Then("Verify if sign up button is not enabled")
    public void verifyIfSignUpButtonIsNotEnabled() {
        registerSteps.signUpIsNotEnabled();
    }

    @Then("Validate if the passwords follow rules")
    public void validateIfThePasswordsFollowRules(DataTable parameters) {
        params = parameters.cells();
        registerSteps.verifyPasswordRulesInlaze(params);
    }
}
