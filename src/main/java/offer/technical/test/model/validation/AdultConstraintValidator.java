package offer.technical.test.model.validation;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdultConstraintValidator implements
    ConstraintValidator<AdultConstraint, LocalDate> {

  @Override
  public void initialize(AdultConstraint constraint) {
    // No particular behaviour
  }

  @Override
  public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
    LocalDate today = LocalDate.now();
    return Objects.isNull(date) || (today.minusYears(18).compareTo(date) >= 0);
  }

}
