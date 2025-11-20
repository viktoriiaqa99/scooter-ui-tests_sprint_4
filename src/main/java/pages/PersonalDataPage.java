package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PersonalDataPage {
    private WebDriver driver;

    //локатор для поля Имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //локатор для поля Фамилия
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор для поля Адрес
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор для поля Станция метро
    private By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    //локатор для поля Телефон
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор для кнопки Далее
    private By nextButton = By.cssSelector(".Button_Middle__1CSJM");

    public PersonalDataPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(String name, String surname, String address, String metroStation, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroField).click();
        selectMetroStation(metroStation);
        driver.findElement(phoneField).sendKeys(phone);
    }

    private void selectMetroStation(String stationName) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".select-search__select")));
        WebElement metroStation = driver.findElement(
                By.xpath(".//button[contains(., '" + stationName + "')]"));
        metroStation.click();
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

}