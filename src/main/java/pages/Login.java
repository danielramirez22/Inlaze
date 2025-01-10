package pages;

import com.ibm.icu.impl.Assert;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.logging.Logger;

@DefaultUrl("https://test-qa.inlaze.com/auth/sign-in")
public class Login extends PageObject {

    @FindBy (id = "email")
    public WebElement email;
    @FindBy(xpath = "//label[@class='label']//following-sibling::app-password//input[@id='password']")
    public WebElement passwordLogin;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signInButton;
    @FindBy(css = "label.btn.btn-ghost.btn-circle.avatar")
    public WebElement profileImage;
    @FindBy(xpath =  "//a[text()='Logout']")
    public WebElement logout;

    public void fillsLoginForm (List<List<String>> parameters){
        String emailString = String.valueOf(parameters.get(0).get(0));
        String passwordString = String.valueOf(parameters.get(0).get(1));
        if(!emailString.trim().equals("null")){
            email.sendKeys(emailString);
        }
        passwordLogin.click();
        if(!passwordString.trim().equals("null")) {
            passwordLogin.sendKeys(passwordString);
        }
    }

    public void clickOnSignIn(){
        signInButton.click();
    }

    public void verifyLogin(){
        if(profileImage.isDisplayed()){
            Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: You're login in");
        } else {
            Assert.fail("TEST FAILED: Expected 'Login in', but got: Wrong email or password");
        }
    }

    public void verifyButtonSignIn(){
        if(signInButton.isEnabled()){
            Assert.fail("TEST FAILED: Expected 'Button disabled', but got: button enabled");
        }else{
            Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: Button is disabled");
        }
    }

    public void logoutSession(){
        profileImage.click();
        logout.click();
    }

    public void verifyLogout(){
        if(!profileImage.isDisplayed()){
            Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: You're login out successfully");
        } else {
            Assert.fail("TEST FAILED: Expected 'Login out', but got: You're stil log in");
        }
    }
}
