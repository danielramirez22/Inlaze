package steps;

import net.serenitybdd.annotations.Step;
import pages.Login;

import java.util.List;

public class LoginSteps extends Login {

    Login login;

    @Step
    public void openPage (){
        login.open();
    }

    @Step
    public void fillsForm(List<List<String>> parameters){
        login.fillsLoginForm(parameters);
    }

    @Step
    public void clickOnSignIn(){
        login.clickOnSignIn();
    }

    @Step
    public void activeSession(){
        login.verifyLogin();
    }

    @Step
    public void veryfyButton(){
        login.verifyButtonSignIn();
    }

    @Step
    public void logoutFromInlaze(){
        login.logoutSession();
    }

    @Step
    public void verifyLogoutFromInlaze(){
        login.verifyLogout();
    }
}
