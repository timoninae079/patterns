import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.*;

@SuppressWarnings("ALL")
class AppCardDeliveryTest {
    private SelenideElement form;
    private final String city = DataGenerator.getRandomCity();
    private final String cityInvalid = DataGenerator.getRandomCityInvalid();
    private final String date = DataGenerator.getDate(3);
    private final String dateReplan = DataGenerator.getDate(10);
    private final String dateInvalid = DataGenerator.getDate(1);
    private final String name = DataGenerator.getFakerName();
    private final String phone = DataGenerator.getFakerPhone();

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
        form = $("[action='/']");
        form.$("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    @Test
    void testPositiveAllInputFirstPlan() {
        form.$("[data-test-id='city'] input").setValue(city);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(name);
        form.$("[data-test-id='phone'] input").setValue(phone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='success-notification']").waitUntil(visible, 15000).shouldHave(text(date));
    }

}
