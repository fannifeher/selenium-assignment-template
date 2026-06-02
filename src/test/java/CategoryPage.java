import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

class CategoryPage extends PageBase {
    private By productNameLinkBy = By.xpath("//a[contains(@class, 'list-productname-link')]");
    private By productPriceBy = By.xpath("//span[contains(@class, 'list_price')]");
    private By sortDropdownBy = By.xpath("//select[@name='sort']");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCategoryPageOpened() {
        return isElementPresent(productNameLinkBy);
    }

    public boolean areProductsDisplayed() {
        return isElementPresent(productNameLinkBy)
                && isElementPresent(productPriceBy);
    }

    public ProductPage openFirstProduct() {
        click(productNameLinkBy);
        return new ProductPage(driver);
    }

    public void sortProductsByPriceAscending() {
        Select sortDropdown = new Select(waitAndReturnElement(sortDropdownBy));
        sortDropdown.selectByVisibleText("Ár szerint növekvő");
    }

    public boolean isSortDropdownDisplayed() {
        return isElementPresent(sortDropdownBy);
    }
}