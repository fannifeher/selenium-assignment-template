import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected WebElement waitAndReturnClickableElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        hideFirstLoginNanobarIfPresent();
        waitAndReturnClickableElement(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitAndReturnElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected void scrollToElement(By locator) {
        WebElement element = waitAndReturnElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public String getBodyText() {
        try {
            return waitAndReturnElement(By.tagName("body")).getText();
        } catch (StaleElementReferenceException exception) {
            return waitAndReturnElement(By.tagName("body")).getText();
        }
    }

    protected void hideFirstLoginNanobarIfPresent() {
        By nanobarBy = By.id("firstLogNanobar");

        if (isElementPresent(nanobarBy)) {
            WebElement nanobar = driver.findElement(nanobarBy);
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", nanobar);
        }
    }
}