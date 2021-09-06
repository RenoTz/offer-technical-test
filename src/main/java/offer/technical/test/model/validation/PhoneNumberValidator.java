package offer.technical.test.model.validation;

import java.util.Objects;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements
    ConstraintValidator<PhoneNumberConstraint, String> {

  private static final String REGEX_PHONE_NUMBER = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";
  private Pattern pattern;

  @Override
  public void initialize(final PhoneNumberConstraint constraint) {
    pattern = Pattern.compile(REGEX_PHONE_NUMBER);
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    return Objects.isNull(value) || pattern.matcher(value).matches();
  }

}
