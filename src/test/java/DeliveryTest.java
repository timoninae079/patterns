import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        final DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        final int daysToAddForFirstMeeting = 4;
        final String firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        final int daysToAddForSecondMeeting = 7;
        final String secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[placeholder=\"Город\"]").setValue(validUser.getCity());
        $("input[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder=\"Дата встречи\"]").setValue(firstMeetingDate);
        $("[data-test-id=\"name\"] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
        $("[data-test-id=\"success-notification\"] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
        $("input[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder=\"Дата встречи\"]").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).should(visible, Duration.ofSeconds(15));
        $x("//span[text()='Перепланировать']").click();
        $("[data-test-id=\"success-notification\"] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }
}