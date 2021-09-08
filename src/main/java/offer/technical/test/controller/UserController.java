package offer.technical.test.controller;

import java.util.Optional;
import offer.technical.test.errors.AlreadyExistsException;
import offer.technical.test.errors.ApiError;
import offer.technical.test.model.UserResource;
import offer.technical.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  UserController(@Autowired final UserService userService) {
    this.userService = userService;
  }

  /**
   * Retrieve user by username
   *
   * @param name
   * @return user
   */
  @GetMapping("/{name}")
  public ResponseEntity<UserResource> getUser(@PathVariable("name") final String name) {
    return Optional.ofNullable(this.userService.getUser(name))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Register a user. Throw an AlreadyExistException if already exists in database.
   *
   * @param user
   * @return user registered
   */
  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Validated final UserResource user) {
    try {
      return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
    } catch (final AlreadyExistsException e) {
      return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, e.getMessage()));
    }
  }

}
