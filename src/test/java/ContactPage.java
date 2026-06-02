import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class ContactPage extends PageBase {
    private By contactTitleBy = By.xpath("//h1[contains(@class, 'page-head-center-title') and normalize-space()='Kapcsolat']");
    private By emailTextBy = By.xpath("//*[contains(normalize-space(), 'info@magisterproducts.hu')]");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public boolean isContactPageOpened() {
        return isElementPresent(contactTitleBy);
    }

    public boolean isCustomerServiceEmailDisplayed() {
        return isElementPresent(emailTextBy);
    }
}