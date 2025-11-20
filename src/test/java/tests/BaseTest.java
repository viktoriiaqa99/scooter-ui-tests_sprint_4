package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;

    //константа урл
    protected static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(SCOOTER_URL);

        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


