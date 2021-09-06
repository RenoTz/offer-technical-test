package offer.technical.test.model.validation;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryConstraintValidator implements
    ConstraintValidator<CountryConstraint, String> {

  @Override
  public void initialize(final CountryConstraint constraint) {
    // No particular behaviour
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    return Objects.isNull(value)
        || (value.equalsIgnoreCase("france")
        || (value.equalsIgnoreCase("french")));
  }

}
