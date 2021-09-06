package offer.technical.test.model.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderConstraintValidator.class)
public @interface GenderConstraint {

  String message() default "must be a male or female";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
