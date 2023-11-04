package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.Helper;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static data.DataGenerator.*;

public class BuyTicketTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clearTable() {
        Helper.cleanDatabase();
    }

    @BeforeEach
    void openPage() {
        open("http://localhost:8080");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    //Положительные сценарии (2)
    @Test
    @DisplayName("1. Оплата с помощью валидной карты, " +
            "поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить\"")
    void shouldDebitPaymentApprovedCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.findSuccessNotification();
        assertEquals("APPROVED", Helper.getDebitCardStatus());
    }


    @Test
    @DisplayName("2. Оплата с помощью валидной карты, " +
            "поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldCreditPaymentApprovedCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.findSuccessNotification();
        assertEquals("APPROVED", Helper.getCreditStatus());
    }


    // Отрицательные сценарии (34)
    @Test
    @DisplayName("1. Оплата с помощью отклоненной карты, " +
            "поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить\"")
    void shouldNotDebitPaymentDeclinedCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getDeclinedCardNumber(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.findErrorNotification();
        assertEquals("DECLINED", Helper.getDebitCardStatus());
    }

    @Test
    @DisplayName("2. Оплата с помощью отклоненной карты, " +
            "поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotCreditPaymentDeclinedCard() {
        CardInfo card = new CardInfo(getDeclinedCardNumber(), nextMonth, nextYear, generateValidName(), generateCVC());
        var mainPage = new MainPage();
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.findErrorNotification();
        assertEquals("DECLINED", Helper.getCreditStatus());
    }

    @Test
    @DisplayName("3. Оплата по несуществующей карте, " +
            "поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить\"")
    void shouldNOtPayRandomCardDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(generateCreditCard(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.findErrorNotification();
    }

    @Test
    @DisplayName("4. Оплата по несуществующей карте, " +
            "поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNOtPayRandomCardCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(generateCreditCard(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.findErrorNotification();
    }
    @Test
    @DisplayName("5. Отправка формы покупки тура с пустым полем \"Номер карты\", " +
            "остальные поля заполнены валидными значениями, путем нажатия кнопки \"Купить\"")
    void shouldNOtPayCardNumberEmptyDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getEmptyCArd(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardNumberError("Поле обязательно для заполнения");

    }
    @Test
    @DisplayName("6. Отправка формы покупки тура с пустым полем \"Номер карты\", " +
            "остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNOtPayCardNumberEmptyCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getEmptyCArd(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardNumberError("Поле обязательно для заполнения");
    }
    @Test
    @DisplayName("7. Отправка формы покупки тура с невалидными значениями номера карты, " +
            "остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить\"")
    void shouldNOtPayCardNumberShortDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(generateShortCardNumber(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardNumberError("Неверный формат");

    }

    @Test
    @DisplayName("8. Отправка формы покупки тура с невалидными значениями номера карты, " +
            "остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNOtPayCardNumberShortCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(generateShortCardNumber(), nextMonth, nextYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("9.Отправка формы покупки тура с невалидными значениями номера карты," +
            "Оплата по карте с истекшим сроком действия (Месяц),остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayPreviousMonthDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), previousMonth, currentYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("10. Отправка формы покупки тура с валидными значениями номера карты," +
            "Указание карты с истекшим сроком действия (Месяц), остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayPreviousMonthCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), previousMonth, currentYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("11. Отправка формы покупки тура с валидными значениями номера карты," +
            " в поле \"Месяц\" указано невалидное (00) значение, остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayZeroMonthDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), zeroMonth, currentYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("12. Отправка формы покупки тура с валидными значениями номера карты, " +
            "в поле \"Месяц\" указано невалидное (00) значение, остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayZeroMonthCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), zeroMonth, currentYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("13. Отправка формы покупки тура с валидными значениями номера карты, " +
            "в поле \"Месяц\" указано невалидное (13), путем нажатия кнопки \"Купить\"")
    void shouldNotPayMonthGreaterThanTwelveDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), generateMonthNumberGreaterThanTwelve(), currentYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("14. Отправка формы покупки тура с валидными значениями номера карты, " +
            "\"Месяц\" указано невалидное (13), путем нажатия кнопки  \"Купить в кредит\"")
    void shouldNotPayMonthGreaterThanTwelveCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), generateMonthNumberGreaterThanTwelve(), currentYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("15. Отправка формы покупки тура с валидными значениями номера карты, " +
            "поле \"Месяц\" остается пустым, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayEmptyMonthDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), emptyMonth, currentYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getMonthError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("16. Отправка формы покупки тура с валидными значениями номера карты, " +
            "поле \"Месяц\" остается пустым, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayEmptyMonthCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), emptyMonth, currentYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getMonthError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("17. Отправка формы покупки тура с валидными значениями номера карты," +
            "Указание карты с истекшим сроком действия (Год), остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayPreviousYearDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, previousYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getYearError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("18. Отправка формы покупки тура с валидными значениями номера карты," +
            "Указание карты с истекшим сроком действия (Год), остальные поля заполнены валидными значениями, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayPreviousYearCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), currentMonth, previousYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getYearError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("19.Отправка формы покупки тура с валидными значениями номера карты, " +
            "указание карты с сроком действия более 5-ти лет от текущего, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayFiveYearFutureDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, generateInvalidYearFuture, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getYearError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("20.Отправка формы покупки тура с валидными значениями номера карты, " +
            "указание карты с сроком действия более 5-ти лет от текущего, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayFiveYearFutureCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, generateInvalidYearFuture, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getYearError("Неверно указан срок действия карты");
    }


    @Test
    @DisplayName("21. Отправка формы покупки тура с валидными значениями номера карты, " +
            "поле \"Год\" остается пустым, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayEmptyYearDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, emptyYear, generateValidName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getYearError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("22. Отправка формы покупки тура с валидными значениями номера карты, " +
            "поле \"Год\" остается пустым, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayEmptyYearCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, emptyYear, generateValidName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getYearError("Поле обязательно для заполнения");
    }

    //27 баг
    @Test
    @DisplayName("23. Отправка формы покупки тура с валидными значениями номера карты," +
            "в поле \"Владелец\" фамилия и имя заполнено кирилицей, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayCyrillicNameDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, generateCyrillicName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardholderError("Неверный формат");
    }

    @Test
    @DisplayName("24. Отправка формы покупки тура с валидными значениями номера карты," +
            "в поле \"Владелец\" фамилия и имя заполнено кирилицей, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayCyrillicNameCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, generateCyrillicName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardholderError("Неверный формат");
    }

    @Test
    @DisplayName("25. Отправка формы покупки тура с валидными значениями номера карты," +
            "в поле \"Владелец\" зполнено только имя, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayOnlyNameDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, generateFirstName(), generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardholderError("Неверный формат");
    }

    @Test
    @DisplayName("26. Отправка формы покупки тура с валидными значениями номера карты," +
            "в поле \"Владелец\" зполнено только имя, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayOnlyNameCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, generateFirstName(), generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardholderError("Неверный формат");
    }

    @Test
    @DisplayName("27. Отправка формы покупки тура с валидными значениями номера карты, "
            + "поле \"Владелец\", останется пустым, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayEmptyHolderDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, emptyCardholder, generateCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardholderError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("28. Отправка формы покупки тура с валидными значениями номера карты, "
            + "поле \"Владелец\", останется пустым, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayEmptyHolderCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, emptyCardholder, generateCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCardholderError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("29. Отправка формы покупки тура с валидными значениями номера карты, "
            + "в поле \"CVC/CVV\" указаны невалидные данные, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayInvalidCVVDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, generateValidName(), generateInvalidCVC());
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCVCError("Неверный формат");
    }

    @Test
    @DisplayName("30. Отправка формы покупки тура с валидными значениями номера карты, "
            + "в поле \"CVC/CVV\" указаны невалидные данные, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayInvalidCVVCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, generateValidName(), generateInvalidCVC());
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCVCError("Неверный формат");
    }

    @Test
    @DisplayName("31. Отправка формы покупки тура с валидными значениями номера карты, "
            + "поле \"CVC/CVV\", останется пустым, с помощью нажатия кнопки \"Купить\"")
    void shouldNotPayEmptyCVVDebitPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, generateValidName(), emptyCvv);
        mainPage.payWithDebitCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCVCError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("32. Отправка формы покупки тура с валидными значениями номера карты, "
            + "поле \"CVC/CVV\", останется пустым, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotPayEmptyCVVCreditPayment() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getApprovedCardNumber(), nextMonth, currentYear, generateValidName(), emptyCvv);
        mainPage.payWithCreditCard();
        mainPage.fillForm(card);
        mainPage.clickContinueButton();
        mainPage.getCVCError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("33.Отправка пустой формы покупки тура, с помощью нажатия кнопки \"Купить\"")
    void shouldNotDebitPaymentEmptyForm() {
        var mainPage = new MainPage();
        mainPage.payWithDebitCard();
        mainPage.clickContinueButton();
        mainPage.getCardNumberError("Поле обязательно для заполнения");
        mainPage.getMonthError("Поле обязательно для заполнения");
        mainPage.getYearError("Поле обязательно для заполнения");
        mainPage.getCardholderError("Поле обязательно для заполнения");
        mainPage.getCVCError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("34. Отправка пустой формы покупки тура, с помощью нажатия кнопки \"Купить в кредит\"")
    void shouldNotCreditPaymentEmptyForm() {
        var mainPage = new MainPage();
        mainPage.payWithCreditCard();
        mainPage.clickContinueButton();
        mainPage.getCardNumberError("Поле обязательно для заполнения");
        mainPage.getMonthError("Поле обязательно для заполнения");
        mainPage.getYearError("Поле обязательно для заполнения");
        mainPage.getCardholderError("Поле обязательно для заполнения");
        mainPage.getCVCError("Поле обязательно для заполнения");
    }
}
