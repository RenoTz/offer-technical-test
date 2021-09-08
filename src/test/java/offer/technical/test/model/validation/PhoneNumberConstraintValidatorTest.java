package offer.technical.test.model.validation;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import offer.technical.test.helper.ExpectedTestViolation;
import offer.technical.test.helper.TestValidationHelper;
import offer.technical.test.model.UserResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhoneNumberConstraintValidatorTest {

  @Autowired
  private TestValidationHelper testValidationHelper;

  @ParameterizedTest
  @ValueSource(strings = {
      "0123456789",
      "01 23 45 67 89",
      "01.23.45.67.89",
      "0123 45.67.89",
      "0033 123-456-789",
      "+33-1.23.45.67.89",
      "+33 - 123 456 789",
      "+33(0) 123 456 789",
      "+33 (0)123 45 67 89",
      "+33 (0)1 2345-6789",
      "+33(0) - 123456789"})
  void testWithValidValue(final String phoneNumber) {
    final List<ExpectedTestViolation> expectedTestViolations = Collections.emptyList();

    final UserResource user = getMinimalUser()
        .setPhoneNumber(phoneNumber);

    final Set<ConstraintViolation<UserResource>> validate = testValidationHelper
        .getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(validate, expectedTestViolations);
  }

  private UserResource getMinimalUser() {
    return new UserResource()
        .setName("name")
        .setCountry("france")
        .setBirthDate(LocalDate.of(2000, 1, 1));
  }

  @Test
  void testWithInvalidValue() {
    final List<ExpectedTestViolation> expectedTestViolations = Collections.singletonList(
        new ExpectedTestViolation("phoneNumber", "must have a valid phone number"));

    final UserResource user = getMinimalUser()
        .setPhoneNumber("0123456874 5412 54");

    final Set<ConstraintViolation<UserResource>> validate = testValidationHelper
        .getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(validate, expectedTestViolations);
  }

}
