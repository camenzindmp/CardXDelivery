import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
/*  @Test
        // реализация successCase с внедрением логики стороннего класса;
    void successCase() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Вл");
        $(byText("Владивосток")).click();
        $("[data-test-id=date] [type=tel]").setValue(LocalDateFromDateCounterClass);
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 10000);
        // 1 вариант: написать логику календаря;
        // 2 вариант: листать страницу и тыкать на последнюю строчку всегда;
    }*/

    @Test
        // successCase без использования класса DateCounter;
    void successCase() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue("Вл");
        $(byText("Владивосток")).click();
        $("[data-test-id=date] button[type=button]").click();    // клик на иконку календаря;
        $("[class='popup__container'] [data-step='1']").click(); // переход на следующую страницу календаря;
        // Изначально хотел выбирать третью строку в календаре и затем первую дату в строке,
        // но исходя из того, что на следующей странице всегда будет число 5 и оно всегда будет
        // не ранее трёх дней с текущей даты, оставил такую реализацию --->
        $(byText("5")).click();
        $("[data-test-id=name] [type=text]").setValue("Имя Фамилия");
        $("[data-test-id=phone] [type=tel]").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000);
    }
}







