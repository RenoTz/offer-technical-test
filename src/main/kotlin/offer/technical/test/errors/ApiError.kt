package offer.technical.test.errors

import org.springframework.http.HttpStatus

data class ApiError(
    var status: HttpStatus,
    var message: String? = null,
    var errors: List<String?>
)

