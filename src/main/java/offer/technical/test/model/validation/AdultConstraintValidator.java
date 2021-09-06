package offer.technical.test.model.validation;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdultConstraintValidator implements
    ConstraintValidator<AdultConstraint, LocalDate> {

  @Override
  public void initialize(final AdultConstraint constraint) {
    // No particular behaviour
  }

  @Override
  public boolean isValid(final LocalDate date, final ConstraintValidatorContext context) {
    final LocalDate today = LocalDate.now();
    return Objects.nonNull(date) && (today.minusYears(18).compareTo(date) >= 0);
  }

}
