package offer.technical.test.errors

import org.springframework.http.HttpStatus

data class ApiErrorKt(
    var status: HttpStatus,
    var message: String,
    var errors: List<String>? = null
)