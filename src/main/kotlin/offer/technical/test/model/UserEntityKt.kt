package offer.technical.test.model

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "users")
data class UserEntityKt (

    val name: String,

    val birthDate: LocalDate,

    val country: String,

    val phoneNumber: String,

    var gender: String

)