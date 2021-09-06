package offer.technical.test.model;


import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import offer.technical.test.helper.ExpectedTestViolation;
import offer.technical.test.helper.TestValidationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

  @Autowired
  private TestValidationHelper testValidationHelper;

  @Test
  void validateEmpty() {
    final List<ExpectedTestViolation> expectedViolations = Arrays.asList(
        new ExpectedTestViolation("name", TestValidationHelper.NOT_BLANK_MESSAGE),
        new ExpectedTestViolation("birthDate", TestValidationHelper.NOT_NULL_MESSAGE),
        new ExpectedTestViolation("country", TestValidationHelper.NOT_BLANK_MESSAGE));

    final User user = new User();
    final Set<ConstraintViolation<User>> result = testValidationHelper.getValidator()
        .validate(user);

    testValidationHelper.containsExactlyExpectedViolations(result, expectedViolations);
  }

}
