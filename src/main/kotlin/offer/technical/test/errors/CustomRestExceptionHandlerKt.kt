package offer.technical.test.errors

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.function.Consumer
import java.util.stream.Collectors

class CustomRestExceptionHandlerKt: ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any?>? {
        val errors = ex.bindingResult.fieldErrors
            .stream()
            .map { error: FieldError -> error.field + ": " + error.defaultMessage }
            .collect(Collectors.toList())
        ex.bindingResult.globalErrors
            .forEach(Consumer { error: ObjectError -> errors.add(error.objectName + ": " + error.defaultMessage) })
        val apiError = ApiErrorKt(HttpStatus.BAD_REQUEST, ex.localizedMessage, errors)
        return handleExceptionInternal(
            ex, apiError, headers, apiError.status, request
        )
    }
}