import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class RegistrationPage extends PageBase {
    private By emailInputBy = By.xpath("//input[@id='email' and @name='email']");
    private By passwordInputBy = By.xpath("//input[@id='password' and @name='password']");
    private By firstNameInputBy = By.xpath("//input[@id='firstname' and @name='firstname']");
    private By lastNameInputBy = By.xpath("//input[@id='lastname' and @name='lastname']");
    private By telephoneInputBy = By.xpath("//input[@id='telephone' and @name='telephone']");
    private By registrationCheckboxBy = By.xpath("//input[@id='registrationCheck_id' and @name='registrationCheck']");

    private By registerButtonBy = By.xpath("//button[contains(@class, 'ac-register-btn') and .//span[normalize-space()='Regisztráció']]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isRegistrationPageOpened() {
        return isElementPresent(emailInputBy)
                && isElementPresent(passwordInputBy)
                && isElementPresent(firstNameInputBy)
                && isElementPresent(lastNameInputBy);
    }

    public boolean areRegistrationFormFieldsDisplayed() {
        return isElementPresent(emailInputBy)
                && isElementPresent(passwordInputBy)
                && isElementPresent(firstNameInputBy)
                && isElementPresent(lastNameInputBy)
                && isElementPresent(telephoneInputBy)
                && isElementPresent(registrationCheckboxBy);
    }


    public boolean isRegisterButtonDisabled() {
        WebElement button = waitAndReturnElement(registerButtonBy);

        return !button.isEnabled()
                || button.getAttribute("disabled") != null;
    }
}