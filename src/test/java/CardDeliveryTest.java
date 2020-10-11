import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

  /*@Test
        // реализация successCase с внедрением логики стороннего класса;
    void successCase() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Вл");
        $(byText("Владивосток")).click();
        $("[data-test-id=date] [type=tel]").setValue(//тут ответ от dateCounter);
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 10000);
        // 1 вариант: написать логику календаря;
        // 2 вариант: листать страницу и тыкать на последнюю строчку всегда;
    }*/

   /* @Test
        // недоделанная реализация successCase без внедрения логики стороннего класса;
    void successCase() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Вл");
        $(byText("Владивосток")).click();
        $("[data-test-id=date] button[type=button]").click(); // клик на колендарь;
        $("[class='popup__container'] [data-step='1']").click(); // переход на некс тпейдж календаря;
        $("[class='popup__container'] [class='calendar__row']").shouldBe(Condition.visible); // найти строку в календаре (нужна доработка);
        $("[class='calendar__day']").click(); // кликнуть на дату (нужна доработка);
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 10000);
    }*/
}







