import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class ProductPage extends PageBase {
    private By productTitleBy = By.xpath("//span[contains(@class, 'product-page-product-name')]");
    private By productPriceBy = By.xpath("//span[contains(@class, 'product_table_price')]");
    private By addToCartButtonBy = By.xpath("//a[@id='add_to_cart' and contains(@class, 'button-add-to-cart')]");
    private By quantityInputBy = By.xpath("//input[@name='quantity' and contains(@class, 'quantity-to-cart')]");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductPageOpened() {
        return isElementPresent(productTitleBy);
    }

    public boolean isProductPriceDisplayed() {
        return isElementPresent(productPriceBy);
    }

    public void setQuantity(String quantity) {
        type(quantityInputBy, quantity);
    }


    public void addProductToCart() {
        String addToCartUrl = waitAndReturnElement(addToCartButtonBy).getAttribute("href");
        driver.get(addToCartUrl);
    }
}