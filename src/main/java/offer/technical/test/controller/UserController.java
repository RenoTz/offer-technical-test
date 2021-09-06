package offer.technical.test.controller;

import java.util.List;
import offer.technical.test.model.User;
import offer.technical.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(this.userService.getAllUsers());
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody @Validated final User user) {
    return ResponseEntity.ok(this.userService.createUser(user));
  }

}
