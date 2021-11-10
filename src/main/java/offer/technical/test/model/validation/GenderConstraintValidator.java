package offer.technical.test.model.validation;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderConstraintValidator implements
    ConstraintValidator<GenderConstraint, String> {

  @Override
  public void initialize(GenderConstraint constraint) {
    // No particular behaviour
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return Objects.isNull(value)
        || ("m".equalsIgnoreCase(value) || "f".equalsIgnoreCase(value));
  }

}
