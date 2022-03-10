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
@Constraint(validatedBy = [AdultConstraintValidatorKt::class])
annotation class AdultConstraintKt(
    val message: String = "must be an adult",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class AdultConstraintValidatorKt : ConstraintValidator<AdultConstraintKt, LocalDate> {
    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext?): Boolean {
        val today = LocalDate.now()
        return Objects.isNull(value) || today.minusYears(18) >= value
    }
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [CountryConstraintValidatorKt::class])
annotation class CountryConstraintKt(
    val message: String = "must be a french resident",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class CountryConstraintValidatorKt : ConstraintValidator<CountryConstraintKt, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return Objects.isNull(value)
                || (value.equals("france", ignoreCase = true)
                || value.equals("french", ignoreCase = true))
    }
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [GenderConstraintValidatorKt::class])
annotation class GenderConstraintKt(
    val message: String = "must be a male or female",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class GenderConstraintValidatorKt : ConstraintValidator<GenderConstraintKt, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return Objects.isNull(value)
                || value.equals("m", ignoreCase = true) || value.equals("f", ignoreCase = true)
    }
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [PhoneConstraintValidatorKt::class])
annotation class PhoneConstraintKt(
    val message: String = "must have a valid phone number",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class PhoneConstraintValidatorKt : ConstraintValidator<PhoneConstraintKt, String> {
    private var REGEX_PHONE_NUMBER = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$"
    private var pattern: Pattern? = null

    override fun initialize(constraint: PhoneConstraintKt?) {
        pattern = Pattern.compile(REGEX_PHONE_NUMBER)
    }
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return Objects.isNull(value) || pattern!!.matcher(value).matches()
    }
}