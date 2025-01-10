package pages;

import com.ibm.icu.impl.Assert;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
@DefaultUrl("https://test-qa.inlaze.com/auth/sign-up")
public class Register extends PageObject {

    @FindBy(id = "full-name")
    public WebElement fullName;
    @FindBy(id = "email")
    public WebElement emailRegister;
    @FindBy(xpath = "//label[@class='label']//following-sibling::app-password//input[@id='password']")
    public WebElement passwordRegister;
    @FindBy(xpath = "//label[@class='label']//following-sibling::app-password//input[@id='confirm-password']")
    public WebElement repeatPassword;
    @FindBy(css = "button[type='submit'].btn.btn-primary")
    public WebElement signUpButton;
    @FindBy(className = "label-text-alt")
    public WebElement passwordMessage;
    @FindBy(xpath = "//app-toast[not(contains(@class,'hidden'))]//div[text()='Successful registration!']")
    public WebElement successfulRegistration;


    public void fillsRegisterForm (List<List<String>> parameters){
        String fullNameString = parameters.get(0).get(0);
        String emailString = String.valueOf(parameters.get(0).get(1));
        String passwordString = String.valueOf(parameters.get(0).get(2));
        String passwordRepearString = String.valueOf(parameters.get(0).get(3));
        if(!fullNameString.trim().equals("null")){
            emailRegister.sendKeys(fullNameString);
        }
        if(!emailString.trim().equals("null")){
            fullName.sendKeys(fullNameString);
        }
        if(!passwordString.trim().equals("null")) {
            emailRegister.sendKeys(emailString);
        }
        if(!emailString.trim().equals("null")){
            passwordRegister.sendKeys(passwordString);
        }
        if(!passwordRepearString.trim().equals("null")){
            repeatPassword.sendKeys(passwordRepearString);
        }
    }

    public void signUp() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        boton.click();
        Thread.sleep(2000);
    }

    public void verifyAccountCreation() {
        try {
            // Esperar hasta que el elemento esté presente
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WebElement successMessageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//app-toast[not(contains(@class,'hidden'))]//div[text()='Successful registration!']")));

            // Asegurarse de que el elemento no sea nulo
            if (successMessageElement == null) {
                Assert.fail("Element 'successfulRegistration' is null.");
            }

            // Obtener el texto del elemento
            String textToVerify = successMessageElement.getText();

            // Verificar el texto obtenido
            if (textToVerify.equals("Successful registration!")) {
                Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: Account creation successful.");
            } else {
                Assert.fail("TEST FAILED: Expected 'Sign in', but got: " + textToVerify);
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Element 'successfulRegistration' was not found: " + e.getMessage());
        } catch (TimeoutException e) {
            Assert.fail("Timeout while waiting for element 'successfulRegistration': " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void countWords(){
        String text = fullName.getText().trim();
        String[] words = text.split("\\s+");
        int result = words.length;
        assertThat(result).isGreaterThanOrEqualTo(1);
    }

    public void accountDoesNotBeCreated(){
        Boolean button = (Boolean) successfulRegistration.isDisplayed();
        if(button.equals(FALSE)){
            Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: Account has been created previously");
        } else {
            Assert.fail("TEST FAILED: Expected 'Sign in', but got: Account creation successful");
        }
    }

    public void signUpIsNotEnabledForEmail (List<List<String>> parameters){

        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compilar el patrón y crear un Matcher
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(parameters.get(0).get(1));
        if (matcher.matches()) {
            Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: Email has correct structure");
        } else {
            Assert.fail("TEST FAILED: Expected 'email structure', but got: Email does not have correct structure");
        }
    }
    public void signUpIsNotEnabled (){
       Boolean buttonDisplayed = (Boolean) passwordMessage.isDisplayed();
       if(buttonDisplayed == FALSE){
           Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: All fields was filled");
       } else {
           Assert.fail("TEST FAILED: Expected 'Filled fields', but got: You may have empty fields");
       }
    }

    public void fillsPasswordFields (List<List<String>> parameters){
        passwordRegister.sendKeys(parameters.get(0).get(0));
        repeatPassword.sendKeys(parameters.get(0).get(1));
    }

    public void passwordMatch (List<List<String>> parameters){
        String password = parameters.get(0).get(0);
        boolean matcher = password.equals(parameters.get(0).get(1));
        if (matcher == TRUE) {
            Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: Passwords are the same");
        } else {
            Assert.fail("TEST FAILED: Expected 'Same password and repeat password fields', but got: different passwords");
        }
    }

    public void passwordRulesRegistration(List<List<String>> parameters) {
        String pass = String.valueOf(parameters.get(0).get(0));
        String repeatPass = String.valueOf(parameters.get(0).get(1));
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (signUpButton.isEnabled()) {
            if (!pass.matches(passwordRegex) && !repeatPass.matches(passwordRegex)) {
                Assert.fail("TEST FAILED:password and repeat password does not follow the rules but button is enabled");
            }else if (pass.matches(passwordRegex) && repeatPass.matches(passwordRegex)) {
                Logger.getLogger(this.getClass().getName()).info("TEST SUCCESS: Password and Repeat Password follow the rules and button is enabled");
            }
        }else{
            if (!pass.matches(passwordRegex) && !repeatPass.matches(passwordRegex)) {
                Assert.fail("TEST FAILED:password and repeat password does not follow the rules and button is not enabled");
            }else if (passwordRegister.getText().matches(passwordRegex) && repeatPassword.getText().matches(passwordRegex)) {
                Assert.fail("TEST FAILED:Password and Repeat Password follow the rules but button is not enabled");
            }
        }
    }
}
