package offer.technical.test.helper;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class TestValidationHelper {

  public static final String NOT_NULL_MESSAGE = "must not be null";
  public static final String NOT_BLANK_MESSAGE = "must not be blank";

  @Autowired
  private Validator validator;

  public Validator getValidator() {
    return validator;
  }

  public <T> void containsExactlyExpectedViolations(
      final Set<? extends ConstraintViolation<? extends T>> result,
      final List<ExpectedTestViolation> expectedViolations) {
    if (CollectionUtils.isEmpty(expectedViolations)) {
      Assertions.assertThat(result).isEmpty();
    } else {
      Assertions.assertThat(result)
          .isNotEmpty()
          .hasSize(expectedViolations.size())
          .allMatch(violation -> hasMatchingViolation(expectedViolations, violation));
    }
  }

  private <T> boolean hasMatchingViolation(final List<ExpectedTestViolation> expectedViolations,
      final ConstraintViolation<T> violation) {
    return expectedViolations.stream().anyMatch(expectedV -> match(violation, expectedV));
  }

  private <T> boolean match(final ConstraintViolation<T> violation,
      final ExpectedTestViolation expectedViolation) {
    return StringUtils.equals(expectedViolation.getStringPath(), violation.getPropertyPath().toString())
        && StringUtils.equals(expectedViolation.getMessage(), violation.getMessage());
  }

}
