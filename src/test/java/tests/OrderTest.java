package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.PersonalDataPage;
import pages.RentalConditionsPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private String orderButtonType;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private String deliveryDate;
    private String rentalPeriod;
    private String scooterColor;
    private String comment;

    public OrderTest(String orderButtonType, String name, String surname, String address,
                     String metroStation, String phone, String deliveryDate,
                     String rentalPeriod, String scooterColor, String comment) {
        this.orderButtonType = orderButtonType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тестовые данные: кнопка {0}, клиент {1} {2}")
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"header", "Иван", "Иванов", "ул. Ленина, д. 1", "Тверская",
                        "+79991234567", "20", "сутки", "black", "Позвонить за час"},

                {"middle", "Петр", "Петров", "пр. Мира, д. 15", "Сокольники",
                        "+79997654321", "21", "двое суток", "grey", "Оставить у двери"},
        };
    }

    @Test
    public void testOrderScooter() {

        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        RentalConditionsPage rentalConditionsPage = new RentalConditionsPage(driver);

        if ("header".equals(orderButtonType)) {
            mainPage.clickOrderButtonHeader();
        } else {
            mainPage.scrollToFAQSection();
            mainPage.clickOrderButtonMiddle();
        }

        personalDataPage.fillForm(name, surname, address, metroStation, phone);
        personalDataPage.clickNextButton();

        rentalConditionsPage.fillForm(deliveryDate, rentalPeriod, scooterColor, comment);
        rentalConditionsPage.clickOrderButton();
        rentalConditionsPage.confirmOrder();

        boolean isSuccessModalDisplayed = rentalConditionsPage.checkSuccessModalAppeared();
        assertTrue("Модальное окно успешного оформления заказа должно отображаться", isSuccessModalDisplayed);
    }

}