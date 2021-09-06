package offer.technical.test.model.validation;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderConstraintValidator implements
    ConstraintValidator<GenderConstraint, String> {

  @Override
  public void initialize(final GenderConstraint constraint) {
    // No particular behaviour
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    return Objects.isNull(value)
        || (value.equalsIgnoreCase("m") || value.equalsIgnoreCase("f"));
  }

}
