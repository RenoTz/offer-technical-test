package offer.technical.test.model.validation;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import offer.technical.test.helper.ExpectedTestViolation;
import offer.technical.test.helper.TestValidationHelper;
import offer.technical.test.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdultConstraintValidatorTest {

  @Autowired
  private TestValidationHelper testValidationHelper;

  @Test
  void testWithValidValue() {
    final List<ExpectedTestViolation> expectedTestViolations = Collections.emptyList();

    final User user = new User()
        .setName("name")
        .setCountry("france")
        .setBirthDate(LocalDate.of(2000, 1, 1));

    final Set<ConstraintViolation<User>> validate = testValidationHelper
        .getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(validate, expectedTestViolations);
  }

  @Test
  void testWithInvalidValue() {
    final List<ExpectedTestViolation> expectedTestViolations = Collections.singletonList(
        new ExpectedTestViolation("birthDate", "must be an adult"));

    final User user = new User()
        .setName("name")
        .setCountry("france")
        .setBirthDate(LocalDate.of(2015, 1, 1));

    final Set<ConstraintViolation<User>> validate = testValidationHelper
        .getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(validate, expectedTestViolations);
  }

}
