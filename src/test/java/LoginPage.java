import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class LoginPage extends PageBase {
    private By emailInputBy = By.xpath("//input[@name='email']");
    private By passwordInputBy = By.xpath("//input[@name='password']");
    private By loginButtonBy = By.xpath("//button[.//span[normalize-space()='Belépés']]");
    private By forgottenPasswordLinkBy = By.xpath("//a[@href='/index.php?route=account/forgotten' and normalize-space()='Elfelejtett jelszó']");
    private By loginErrorBy = By.xpath("//div[contains(@class, 'alert-danger') and contains(normalize-space(), 'Hibás felhasználónév és/vagy jelszó')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageOpened() {
        return isElementPresent(emailInputBy)
                && isElementPresent(passwordInputBy)
                && isElementPresent(forgottenPasswordLinkBy);
    }

    public void loginWithInvalidCredentials(String email, String password) {
        type(emailInputBy, email);
        type(passwordInputBy, password);
        click(loginButtonBy);
    }

    public boolean isLoginErrorDisplayed() {
        return waitAndReturnElement(loginErrorBy).isDisplayed();
    }

    public boolean isForgottenPasswordLinkDisplayed() {
        return isElementPresent(forgottenPasswordLinkBy);
    }

    public RegistrationPage openRegistrationFromLoginPage() {
        driver.get(TestData.BASE_URL + "customer/register#/customerdata");
        return new RegistrationPage(driver);
    }
}