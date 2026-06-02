import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.UUID;

public class FirstSeleniumTest {
    private WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        driver = DriverConfig.createDriver();
    }

    @Test
    public void homePageOpensAndShowsMainBrandContent() {
        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.isMainPageOpened());
        Assert.assertTrue(mainPage.getBodyText().contains("Magister"));
    }

    @Test
    public void visitorCanOpenLoginPageFromHomePage() {
        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.openLoginPage();

        Assert.assertTrue(loginPage.isLoginPageOpened());
        Assert.assertTrue(loginPage.isForgottenPasswordLinkDisplayed());
    }

    @Test
    public void visitorCannotLoginWithInvalidCredentials() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.openLoginPage();

        loginPage.loginWithInvalidCredentials(TestData.INVALID_EMAIL, TestData.INVALID_PASSWORD);

        Assert.assertTrue(loginPage.isLoginErrorDisplayed());
    }

    @Test
    public void loginFormCanBeSubmittedWithRandomInvalidUserData() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.openLoginPage();

        String randomEmail = "test-" + UUID.randomUUID() + "@example.com";

        loginPage.loginWithInvalidCredentials(randomEmail, TestData.INVALID_PASSWORD);

        Assert.assertTrue(loginPage.isLoginErrorDisplayed());
    }

    @Test
    public void visitorCanNavigateFromLoginPageToRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.openLoginPage();

        RegistrationPage registrationPage = loginPage.openRegistrationFromLoginPage();

        Assert.assertTrue(registrationPage.isRegistrationPageOpened());
    }

    @Test
    public void registrationPageShowsRequiredInputFields() {
        MainPage mainPage = new MainPage(driver);

        RegistrationPage registrationPage = mainPage.openRegistrationPage();

        Assert.assertTrue(registrationPage.isRegistrationPageOpened());
        Assert.assertTrue(registrationPage.areRegistrationFormFieldsDisplayed());
    }

    @Test
    public void registrationFormDoesNotAllowSubmissionWithMissingRequiredData() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = mainPage.openRegistrationPage();

        Assert.assertTrue(registrationPage.areRegistrationFormFieldsDisplayed());
        Assert.assertTrue(registrationPage.isRegisterButtonDisabled());
    }

    @Test
    public void visitorCanOpenFaceCareCategoryAndSeeProducts() {
        MainPage mainPage = new MainPage(driver);

        CategoryPage categoryPage = mainPage.openFaceCareCategory();

        Assert.assertTrue(categoryPage.isCategoryPageOpened());
        Assert.assertTrue(categoryPage.areProductsDisplayed());
    }

    @Test
    public void visitorCanOpenSunscreenCategoryAndSeeProducts() {
        MainPage mainPage = new MainPage(driver);

        CategoryPage categoryPage = mainPage.openSunscreenCategory();

        Assert.assertTrue(categoryPage.isCategoryPageOpened());
        Assert.assertTrue(categoryPage.areProductsDisplayed());
    }

    @Test
    public void visitorCanSearchForProduct() {
        MainPage mainPage = new MainPage(driver);

        CategoryPage searchResultsPage = mainPage.searchForProduct(TestData.SEARCH_TEXT);

        Assert.assertTrue(searchResultsPage.areProductsDisplayed());
    }

    @Test
    public void visitorCanSortCategoryProductsByPrice() {
        MainPage mainPage = new MainPage(driver);
        CategoryPage categoryPage = mainPage.openFaceCareCategory();

        Assert.assertTrue(categoryPage.isSortDropdownDisplayed());

        categoryPage.sortProductsByPriceAscending();

        Assert.assertTrue(categoryPage.areProductsDisplayed());
    }

    @Test
    public void visitorCanOpenProductDetailsFromCategoryPage() {
        MainPage mainPage = new MainPage(driver);
        CategoryPage categoryPage = mainPage.openFaceCareCategory();

        ProductPage productPage = categoryPage.openFirstProduct();

        Assert.assertTrue(productPage.isProductPageOpened());
        Assert.assertTrue(productPage.isProductPriceDisplayed());
    }

    @Test
    public void visitorCanOpenProductAndUseAddToCartLink() {
        MainPage mainPage = new MainPage(driver);
        CategoryPage categoryPage = mainPage.openFaceCareCategory();
        ProductPage productPage = categoryPage.openFirstProduct();

        Assert.assertTrue(productPage.isProductPageOpened());
        Assert.assertTrue(productPage.isProductPriceDisplayed());

        productPage.setQuantity("1");
        productPage.addProductToCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isCartPageOpened());
    }

    @Test
    public void visitorCanOpenEmptyCartPage() {
        MainPage mainPage = new MainPage(driver);

        CartPage cartPage = mainPage.openCartPage();

        Assert.assertTrue(cartPage.isCartPageOpened());
        Assert.assertTrue(cartPage.isCartEmptyMessageDisplayed());
    }

    @Test
    public void visitorCanOpenContactPageAndSeeCustomerServiceEmail() {
        MainPage mainPage = new MainPage(driver);

        ContactPage contactPage = mainPage.openContactPage();

        Assert.assertTrue(contactPage.isContactPageOpened());
        Assert.assertTrue(contactPage.isCustomerServiceEmailDisplayed());
    }

    @Test
    public void importantPagesCanBeOpened() {
        String[] pageUrls = {
                TestData.BASE_URL,
                TestData.BASE_URL + "index.php?route=account/login",
                TestData.BASE_URL + "index.php?route=account/register",
                TestData.BASE_URL + "cart",
                TestData.BASE_URL + "index.php?route=information/contact",
                TestData.BASE_URL + "arcapolas-79/arckremek-97"
        };

        for (String pageUrl : pageUrls) {
            driver.get(pageUrl);

            Assert.assertFalse(driver.getTitle().isEmpty());
            Assert.assertTrue(driver.getPageSource().length() > 1000);
        }
    }

    @Test
    public void browserHistoryBackAndForwardWorksBetweenPages() {
        MainPage mainPage = new MainPage(driver);

        mainPage.openLoginPage();
        String loginUrl = driver.getCurrentUrl();

        mainPage.openContactPage();
        String contactUrl = driver.getCurrentUrl();

        driver.navigate().back();
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());

        driver.navigate().forward();
        Assert.assertEquals(contactUrl, driver.getCurrentUrl());
    }

    @Test
    public void visitorCookieCanBeAddedAndDeleted() {
        new MainPage(driver);

        Cookie testCookie = new Cookie("selenium_test_cookie", "magister");
        driver.manage().addCookie(testCookie);

        Assert.assertEquals("magister", driver.manage().getCookieNamed("selenium_test_cookie").getValue());

        driver.manage().deleteCookieNamed("selenium_test_cookie");

        Assert.assertNull(driver.manage().getCookieNamed("selenium_test_cookie"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}