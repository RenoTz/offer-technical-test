package offer.technical.test.model

import offer.technical.test.model.validation.AdultConstraint
import offer.technical.test.model.validation.CountryConstraint
import offer.technical.test.model.validation.GenderConstraint
import offer.technical.test.model.validation.PhoneConstraint
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserResource (

    @field:NotBlank
    val name: String,

    @field:AdultConstraint
    @field:NotNull
    val birthDate: LocalDate,

    @field:CountryConstraint
    @field:NotBlank
    val country: String,

    @field:PhoneConstraint
    val phoneNumber: String,

    @field:GenderConstraint
    val gender: String

)