package offer.technical.test.controller

import offer.technical.test.errors.AlreadyExistsExceptionKt
import offer.technical.test.errors.ApiErrorKt
import offer.technical.test.model.UserResourceKt
import offer.technical.test.services.UserServiceKt
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/users")
class UserControllerKt(var userService: UserServiceKt) {

    /**
     * Retrieve user by username
     *
     * @param name
     * @return user
     */
    @GetMapping("/{name}")
    fun getUser(@PathVariable("name") name: String?): ResponseEntity<UserResourceKt>? {
        return Optional.ofNullable(userService.getUser(name))
            .map<ResponseEntity<UserResourceKt>> { body: UserResourceKt? -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    /**
     * Register a user. Thrown an AlreadyExistException if already exists in database.
     *
     * @param user
     * @return user registered
     */
    @PostMapping
    fun create(@RequestBody @Validated user: UserResourceKt?): ResponseEntity<Any?>? {
        return try {
            ResponseEntity(userService.create(user), HttpStatus.CREATED)
        } catch (e: AlreadyExistsExceptionKt) {
            ResponseEntity.badRequest().body(e.message?.let { ApiErrorKt(HttpStatus.BAD_REQUEST, it) })
        }
    }
}