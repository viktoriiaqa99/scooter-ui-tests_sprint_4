package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentalConditionsPage {

    private WebDriver driver;
    //локатор для поля "Когда привезти самокат"
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор для поля "Срок аренды"
    private By rentalPeriodField = By.cssSelector(".Dropdown-control");
    //локатор для чекбокса "черный жемчуг"
    private By blackCheckbox = By.id("black");
    //локатор для чекбокса "серая безысходность"
    private By greyCheckbox = By.id("grey");
    //локатор для поля "Комментарий для курьера"
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор для кнопки "Заказать"
    private By orderButton = By.xpath(".//button[text()='Заказать' and contains(@class, 'Button_Middle__1CSJM')]");

    //локатор для модального окна подтверждения заказа
    private By confirmationModal = By.className("Order_Modal__YZD3");
    //локатор для кнопки "Да" модального окна подтверждения заказа
    private By confirmOrderButton = By.xpath(".//button[text()='Да']");
    //локатор для модального окна об успешном заказе
    private By successModal = By.className("Order_ModalHeader__3FDaJ");

    public RentalConditionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(String dayOfMonth, String periodValue, String color, String comment) {
        setDeliveryDate(dayOfMonth);
        setRentalPeriod(periodValue);
        selectColor(color);
        setComment(comment);
    }

    private void setDeliveryDate(String dayOfMonth) {
        driver.findElement(dateField).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".react-datepicker")));

        WebElement dateElement = driver.findElement(
                By.xpath(".//div[contains(@class, 'react-datepicker__day') and text()='" + dayOfMonth + "']"));
        dateElement.click();
    }

    private void setRentalPeriod(String periodValue) {
        driver.findElement(rentalPeriodField).click();

        WebElement periodOption = driver.findElement(
                By.xpath(".//div[contains(@class, 'Dropdown-option') and text()='" + periodValue + "']"));
        periodOption.click();
    }

    private void selectColor(String color) {
        if ("black".equals(color)) {
            driver.findElement(blackCheckbox).click();
        } else if ("grey".equals(color)) {
            driver.findElement(greyCheckbox).click();
        }
    }

    private void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(orderButton));
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationModal));

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        driver.findElement(confirmOrderButton).click();
    }

    public boolean checkSuccessModalAppeared() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(successModal));
        return driver.findElement(successModal).isDisplayed();
    }
}