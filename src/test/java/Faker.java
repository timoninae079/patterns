import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class Faker {
    private Faker faker;
}

 @BeforeEach

 void setUpAll (){
         faker = new Faker(newLocale("ru"));
         }


  @Test

 void shouldPreventSendRequestMultipleTimes() {
    String name = faker.name().fullName();
    String phone = faker.phoneNumber().phoneNumber();
    String cardNumber = faker.finance().creditCard(CreditCardType.MASTERCARD);
    System.out.println(name);
    System.out.println(phone);
    System.out.println(cardNumber);
}
}

