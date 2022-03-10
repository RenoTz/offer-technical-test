package offer.technical.test.model.validation

import java.time.LocalDate
import java.util.*
import java.util.regex.Pattern
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [AdultConstraintValidator::class])
annotation class AdultConstraint(
    val message: String = "must be an adult",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class AdultConstraintValidator : ConstraintValidator<AdultConstraint, LocalDate> {
    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext?): Boolean {
        val today = LocalDate.now()
        return Objects.isNull(value) || today.minusYears(18) >= value
    }
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [CountryConstraintValidator::class])
annotation class CountryConstraint(
    val message: String = "must be a french resident",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class CountryConstraintValidator : ConstraintValidator<CountryConstraint, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return Objects.isNull(value)
                || (value.equals("france", ignoreCase = true)
                || value.equals("french", ignoreCase = true))
    }
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [GenderConstraintValidator::class])
annotation class GenderConstraint(
    val message: String = "must be a male or female",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class GenderConstraintValidator : ConstraintValidator<GenderConstraint, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return Objects.isNull(value)
                || value.equals("m", ignoreCase = true) || value.equals("f", ignoreCase = true)
    }
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [PhoneConstraintValidator::class])
annotation class PhoneConstraint(
    val message: String = "must have a valid phone number",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class PhoneConstraintValidator : ConstraintValidator<PhoneConstraint, String> {
    private var REGEX_PHONE_NUMBER = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$"
    private var pattern: Pattern? = null

    override fun initialize(constraint: PhoneConstraint?) {
        pattern = Pattern.compile(REGEX_PHONE_NUMBER)
    }
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return Objects.isNull(value) || pattern!!.matcher(value).matches()
    }
}