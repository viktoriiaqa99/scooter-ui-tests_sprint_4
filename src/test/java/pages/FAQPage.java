package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FAQPage {

    private WebDriver driver;

    public FAQPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickQuestion(int questionIndex) {
        By questionLocator = By.id("accordion__heading-" + questionIndex);

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(questionLocator));

        driver.findElement(questionLocator).click();
    }

    public String getAnswerText(int questionIndex) {
        By answerLocator = By.id("accordion__panel-" + questionIndex);

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));

        return driver.findElement(answerLocator).getText();
    }
}