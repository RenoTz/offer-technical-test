package offer.technical.test.controller

import offer.technical.test.model.UserResource
import offer.technical.test.services.UserService
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
class UserController(var userService: UserService) {

    /**
     * Retrieve user by username
     *
     * @param name
     * @return user
     */
    @GetMapping("/{name}")
    fun getUser(@PathVariable("name") name: String?): ResponseEntity<UserResource>? {
        return Optional.ofNullable(userService.getUser(name))
            .map<ResponseEntity<UserResource>> { body: UserResource? -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    /**
     * Register a user. Thrown an AlreadyExistException if already exists in database.
     *
     * @param user
     * @return user registered
     */
    @PostMapping
    fun create(@RequestBody @Validated user: UserResource?): ResponseEntity<Any?> {
        return ResponseEntity(userService.create(user), HttpStatus.CREATED)
    }
}