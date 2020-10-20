import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {
    @Test
    void successCaseWithCounter() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Владивосток");
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        LocalDate date = LocalDate.now().plusDays(7);  // вычисление текущей даты + 7 дней;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // перевод даты в нужный формат;
        $("[data-test-id=date] [type=tel]").setValue(date.format(formatter)); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000);
        String successText = $("[data-test-id=notification]").getText(); // получить текст и дату из нотификейшена;
        assertEquals("Успешно!\nВстреча успешно забронирована на " + date.format(formatter), successText); // сравнить текст и дату из нотификейшена с ожидаемым текстом и текущей датой;
    }

    // кейс с вводом города не из списка административных центров субъектов РФ:
    @Test
    void incorrectCityName() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Минск");
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=city].input_invalid").shouldBe(Condition.visible);
    }

    // кейс с вводом некорректного имени пользователя:
    @Test
    void incorrectClientName() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Владивосток");
        $("[data-test-id=name] [type=text]").setValue("Incorrect Name");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=name].input_invalid").shouldBe(Condition.visible);
    }

    // кейс с вводом номера телефона >12 символов:
    @Test
    void tooLongPhoneNumber() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Владивосток");
        $("[data-test-id=name] [type=text]").setValue("Фамилия Имя");
        $("[data-test-id=phone] [type=tel]").setValue("+790123456784325416541313254");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=phone].input_invalid").shouldBe(Condition.visible);
    }

    // посторонние символы в номере телфона:
    @Test
    void neptSymbolsInPhoneNumber() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Владивосток");
        $("[data-test-id=name] [type=text]").setValue("Фамилия Имя");
        $("[data-test-id=phone] [type=tel]").setValue("+7901j#@./,*");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=phone].input_invalid").shouldBe(Condition.visible);
    }

    // не отмечен чекбокс:
    @Test
    void checkboxIsFalse() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Владивосток");
        $("[data-test-id=name] [type=text]").setValue("Фамилия Имя");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=agreement].input_invalid").shouldBe(Condition.visible);
    }

    // отправка пустой формы:
    @Test
    void sendEmptyForm() {
        open("http://localhost:9999/");
        $("[type=button] [class='button__text']").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    //=========================================================>
    // ЗАДАНИЕ 2!
//    @Test
//    void taskTwo() {
//        open("http://localhost:9999/");
//        $("[data-test-id=city] input[class=input__control]").setValue("Вл");
//        $(byText("Владивосток")).click();
//        $("[data-test-id=date] button[type=button]").click();    // клик на иконку календаря;
//        $("[class='popup__container'] [data-step='1']").click(); // переход на следующую страницу календаря;
//        // Исходя из того, что на следующей странице всегда будет число 5 и оно всегда будет
//        // не ранее трёх дней с текущей даты, оставил такую реализацию --->
//        $(byText("5")).click();
//        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
//        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
//        $("[data-test-id=agreement]").click();
//        $("[type=button] [class='button__text']").click();
//        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000);
//    }
}





