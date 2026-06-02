import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

class MainPage extends PageBase {
    private final String loginUrl = TestData.BASE_URL + "index.php?route=account/login";
    private final String registrationUrl = TestData.BASE_URL + "customer/register#/customerdata";
    private final String cartUrl = TestData.BASE_URL + "cart";
    private final String contactUrl = TestData.BASE_URL + "index.php?route=information/contact";
    private final String faceCreamUrl = TestData.BASE_URL + "arcapolas-79/arckremek-97";
    private final String sunscreenUrl = TestData.BASE_URL + "arcapolas-79/fenyvedok-314";

    private By searchInputBy = By.xpath("//input[@id='filter_keyword']");
    private By acceptCookieButtonBy = By.xpath("//button[contains(normalize-space(), 'Elfogadom')]");

    public MainPage(WebDriver driver) {
        super(driver);
        driver.get(TestData.BASE_URL);
        acceptCookiesIfDisplayed();
    }

    public boolean isMainPageOpened() {
        return getBodyText().contains("Magister");
    }

    public LoginPage openLoginPage() {
        driver.get(loginUrl);
        return new LoginPage(driver);
    }

    public RegistrationPage openRegistrationPage() {
        driver.get(registrationUrl);
        return new RegistrationPage(driver);
    }

    public CartPage openCartPage() {
        driver.get(cartUrl);
        return new CartPage(driver);
    }

    public ContactPage openContactPage() {
        driver.get(contactUrl);
        return new ContactPage(driver);
    }

    public CategoryPage openFaceCareCategory() {
        driver.get(faceCreamUrl);
        return new CategoryPage(driver);
    }

    public CategoryPage openSunscreenCategory() {
        driver.get(sunscreenUrl);
        return new CategoryPage(driver);
    }

    public CategoryPage searchForProduct(String searchText) {
        type(searchInputBy, searchText);
        waitAndReturnElement(searchInputBy).sendKeys(Keys.ENTER);
        return new CategoryPage(driver);
    }

    private void acceptCookiesIfDisplayed() {
        try {
            if (isElementPresent(acceptCookieButtonBy)) {
                click(acceptCookieButtonBy);
            }
        } catch (Exception ignored) {
        }
    }
}