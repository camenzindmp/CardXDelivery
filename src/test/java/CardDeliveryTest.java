import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test // тест проходит  успешно, но не написана логика на дату.
    void successCase() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Вл");
        $(byText("Владивосток")).click();
        $("[data-test-id=date] button[type=button]").click();
        $("[class='popup__container'] [data-day='1604066400000']").click();
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 10000);
        // 1 вариант: написать логику календаря;
        // 2 вариант: листать страницу и тыкать на последнюю строчку всегда;
    }
}








