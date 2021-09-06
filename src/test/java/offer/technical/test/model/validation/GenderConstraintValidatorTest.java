package offer.technical.test.model.validation;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import offer.technical.test.helper.ExpectedTestViolation;
import offer.technical.test.helper.TestValidationHelper;
import offer.technical.test.model.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GenderConstraintValidatorTest {

  @Autowired
  private TestValidationHelper testValidationHelper;

  @ParameterizedTest
  @ValueSource(strings = {"M", "F"})
  void testWithValidValues(final String gender) {
    final List<ExpectedTestViolation> expectedTestViolations = Collections.emptyList();

    final User user = getMinimalUser()
        .setGender(gender);

    final Set<ConstraintViolation<User>> validate = testValidationHelper
        .getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(validate, expectedTestViolations);
  }

  private User getMinimalUser() {
    return new User()
        .setName("name")
        .setCountry("france")
        .setBirthDate(LocalDate.of(2000, 1, 1));
  }

  @ParameterizedTest
  @ValueSource(strings = {"A", "B", "C", "D", "E", "G", "H", "I", "J", "K", "L", "N", "O", "P", "Q",
      "R", "S", "T", "U", "V", "W", "X", "Y", "Z"})
  void testWithInvalidValues(final String gender) {
    final List<ExpectedTestViolation> expectedTestViolations = Collections.singletonList(
        new ExpectedTestViolation("gender", "must be a male or female"));

    final User user = getMinimalUser()
        .setGender(gender);

    final Set<ConstraintViolation<User>> validate = testValidationHelper
        .getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(validate, expectedTestViolations);
  }

}
