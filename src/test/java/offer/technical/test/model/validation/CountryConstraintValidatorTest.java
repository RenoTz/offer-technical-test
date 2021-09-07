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
class CountryConstraintValidatorTest {

  @Autowired
  private TestValidationHelper testValidationHelper;

  @ParameterizedTest
  @ValueSource(strings = {"france", "FRANCE", "french", "FRENCH"})
  void testWithValidValue(final String country) {
    final List<ExpectedTestViolation> expectedTestViolations = Collections.emptyList();

    final UserResource user = new UserResource()
        .setName("name")
        .setCountry(country)
        .setBirthDate(LocalDate.of(2000, 1, 1));

    final Set<ConstraintViolation<UserResource>> validate = testValidationHelper
        .getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(validate, expectedTestViolations);
  }

  @Test
  void testWithInvalidValue() {
    final List<ExpectedTestViolation> expectedTestViolations = Collections.singletonList(
        new ExpectedTestViolation("country", "must be a french resident"));

    final UserResource user = new UserResource()
        .setName("name")
        .setCountry("invalid")
        .setBirthDate(LocalDate.of(2000, 1, 1));

    final Set<ConstraintViolation<UserResource>> validate = testValidationHelper
        .getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(validate, expectedTestViolations);
  }

}
