package offer.technical.test.model

import offer.technical.test.model.validation.AdultConstraintKt
import offer.technical.test.model.validation.CountryConstraintKt
import offer.technical.test.model.validation.GenderConstraintKt
import offer.technical.test.model.validation.PhoneConstraintKt
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserResourceKt (

    @field:NotBlank
    val name: String,

    @field:AdultConstraintKt
    @field:NotNull
    val birthDate: LocalDate,

    @field:CountryConstraintKt
    @field:NotBlank
    val country: String,

    @field:PhoneConstraintKt
    val phoneNumber: String,

    @field:GenderConstraintKt
    val gender: String

)