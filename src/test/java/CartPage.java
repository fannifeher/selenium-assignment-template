import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class CartPage extends PageBase {
    private By cartTitleBy = By.xpath("//h1[contains(@class, 'page-head-center-title') and normalize-space()='Kosár']");
    private By checkoutButtonBy = By.xpath("//a[contains(@class, 'ac-next-to-checkout') and contains(normalize-space(), 'Tovább a pénztárba')]");
    private By removeButtonBy = By.xpath("//button[contains(@class, 'ac-prod-delete') and normalize-space()='Törlés']");
    private By emptyCartTextBy = By.xpath("//div[@class='text-center' and contains(text(), 'A kosár üres')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageOpened() {
        return isElementPresent(cartTitleBy);
    }

    public boolean hasCartContent() {
        return isElementPresent(removeButtonBy)
                || isElementPresent(checkoutButtonBy)
                || !isCartEmptyMessageDisplayed();
    }

    public void removeFirstProductIfPossible() {
        if (isElementPresent(removeButtonBy)) {
            click(removeButtonBy);
        }
    }

    public boolean isCartEmptyMessageDisplayed() {
        return isElementPresent(emptyCartTextBy);
    }
}