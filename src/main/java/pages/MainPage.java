package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    //локатор кнопки "заказать" в хеддере
    private By orderButtonHeader = By.className("Button_Button__ra12g");
    //локатор кнопки "заказать" в середине страницы
    private By orderButtonMiddle = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    //локатор раздела "вопросы о важном"
    private By faqSection = By.className("Home_FourPart__1uthg");
    /**
     * при запуске тестов было обнаружено, что мешает cookie-баннер, который перекрывает кнопки
     * локатор для кнопки принятия кук
     */
    private By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(cookieButton));

        WebElement cookieElement = driver.findElement(cookieButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieElement);
    }

    public void scrollToFAQSection() {
        WebElement faqElement = driver.findElement(faqSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqElement);
    }

    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    public void clickOrderButtonMiddle() {
        driver.findElement(orderButtonMiddle).click();
    }

}