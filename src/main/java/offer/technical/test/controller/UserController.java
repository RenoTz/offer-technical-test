package offer.technical.test.controller;

import lombok.RequiredArgsConstructor;
import offer.technical.test.errors.AlreadyExistsException;
import offer.technical.test.errors.ApiError;
import offer.technical.test.model.UserResource;
import offer.technical.test.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /**
   * Retrieve user by username
   *
   * @param name username
   * @return user
   */
  @GetMapping("/{name}")
  public ResponseEntity<UserResource> getUser(@PathVariable("name") String name) {
    return Optional.ofNullable(userService.getUser(name))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Register a user. Throw an AlreadyExistException if already exists in database.
   *
   * @param user a {@link UserResource} to be registered
   * @return a {@link UserResource} registered
   */
  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Validated UserResource user) {
    try {
      return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    } catch (AlreadyExistsException e) {
      return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, e.getMessage()));
    }
  }

}
