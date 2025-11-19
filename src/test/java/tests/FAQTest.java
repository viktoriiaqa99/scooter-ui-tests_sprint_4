package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import pages.FAQPage;
import org.junit.After;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQTest {

    private WebDriver driver;
    private int questionIndex;
    private String expectedAnswerPart;

    public FAQTest(int questionIndex, String expectedAnswerPart) {
        this.questionIndex = questionIndex;
        this.expectedAnswerPart = expectedAnswerPart;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void testFAQAnswer() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        FAQPage faqPage = new FAQPage(driver);

        mainPage.acceptCookies();
        mainPage.scrollToFAQSection();
        faqPage.clickQuestion(questionIndex);

        String actualAnswer = faqPage.getAnswerText(questionIndex);
        assertEquals("Проверка ответа на вопрос №" + questionIndex,
                expectedAnswerPart, actualAnswer);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}