package offer.technical.test.errors

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class CustomRestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(request: HttpServletRequest, ex: MethodArgumentNotValidException): ResponseEntity<ApiError> {

        val errors = ex.bindingResult.fieldErrors
            .stream()
            .map { error: FieldError -> error.field + ": " + error.defaultMessage }
            .collect(Collectors.toList())
        return ResponseEntity<ApiError>(ApiError(HttpStatus.BAD_REQUEST, ex.parameter.method?.name, errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleAlreadyExistsExceptionKt(request: HttpServletRequest, ex: AlreadyExistsException): ResponseEntity<ApiError> {

        return ResponseEntity<ApiError>(ApiError(HttpStatus.BAD_REQUEST, ex.javaClass.simpleName, listOf(ex.message)), HttpStatus.BAD_REQUEST);
    }
}