package offer.technical.test.controller;

import offer.technical.test.model.UserResource;
import offer.technical.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @GetMapping
  public ResponseEntity<UserResource> getUser(@RequestParam final String name) {
    return ResponseEntity.ok(this.userService.getUser(name));
  }

  /**
   * Register a user
   *
   * @param user
   * @return user registered
   */
  @PostMapping
  public ResponseEntity<UserResource> create(@RequestBody @Validated final UserResource user) {
    return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
  }

}
