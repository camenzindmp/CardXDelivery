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
        LocalDate meetingDate = LocalDate.now().plusDays(7);  // вычисление текущей даты + 7 дней;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // перевод даты в нужный формат;
        $("[data-test-id=date] [type=tel]").setValue(meetingDate.format(formatter)); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000);
        String successText = $("[data-test-id=notification]").getText(); // получить текст и дату из нотификейшена;
        assertEquals("Успешно!\nВстреча успешно забронирована на " + meetingDate.format(formatter), successText); // сравнить текст и дату из нотификейшена с ожидаемым текстом и текущей датой;
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

    // ЗАДАНИЕ 2!
    //=========================================================>
    @Test
    void secondTask() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Вл");
        $(byText("Владивосток")).click();
        $("[data-test-id=date] button[type=button]").click();
        LocalDate currentDate = LocalDate.now(); // расчет текущей даты;
        LocalDate meetingDate = LocalDate.now().plusDays(7); // расчет текущей даты +7 дней;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // перевод даты в нужный формат;
        int currentMonth = currentDate.getMonthValue(); // значение месяца от текущей даты;
        int meetingMonth = meetingDate.getMonthValue(); // значение месяца о текущей даты +7 дней;
        int meetingDay = meetingDate.getDayOfMonth(); // расчет дня от ткущей даты +7 дней;
        if (meetingMonth != currentMonth) { // сравнение значений месяца текщей даты и текущей +7 дней;
            $("[class='popup__container'] [data-step='1']").click(); // переход на следующую страницу календаря;
        }
        $(byText(String.valueOf(meetingDay))).click(); // поиск по значению дня от текущей даты+7 дней;
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000);
        String successText = $("[data-test-id=notification]").getText();
        assertEquals("Успешно!\nВстреча успешно забронирована на " + meetingDate.format(formatter), successText);
    }
}





