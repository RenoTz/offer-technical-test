package offer.technical.test.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BeginByWConstraintValidator implements
    ConstraintValidator<BeginByWConstraint, String> {

  @Override
  public void initialize(BeginByWConstraint constraint) {
    // No particular behaviour
  }

  @Override
  public boolean isValid(String name, ConstraintValidatorContext context) {
    return name != null && name.startsWith("W");
  }

}
