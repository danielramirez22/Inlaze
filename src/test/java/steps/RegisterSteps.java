package steps;

import net.serenitybdd.annotations.Step;
import pages.Register;

import java.util.List;

public class RegisterSteps extends Register {

    Register register;

    @Step
    public void openPage (){
        register.open();
    }

    @Step
    public void fillsForm (List<List<String>> parameters){
        register.fillsRegisterForm(parameters);
    }

    @Step
    public void clickSignUp() throws InterruptedException {
        register.signUp();
    }
    @Step
    public void accountCreation (){
        register.verifyAccountCreation();
    }

    @Step
    public void numberOfWords(){
        register.countWords();
    }
    @Step
    public void accountDoesNotBeCreated (){
        register.accountDoesNotBeCreated();
    }
    @Step
    public void signUpButtonIsNotEnabledForEmail (List<List<String>> parameters){
        register.signUpIsNotEnabledForEmail(parameters);
    }

    public void signUpIsNotEnabled(){
        register.signUpIsNotEnabled();
    }

    @Step
    public void userFillsPasswordFields (List<List<String>> parameters){
        register.fillsPasswordFields(parameters);
    }

    @Step
    public void passwordMessage (List<List<String>> parameters){
        register.passwordMatch(parameters);
    }

    @Step
    public void verifyPasswordRulesInlaze (List<List<String>> parameters){
        register.passwordRulesRegistration(parameters);
    }
}
