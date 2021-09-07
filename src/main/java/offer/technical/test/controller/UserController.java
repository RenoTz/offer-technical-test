package offer.technical.test.controller;

import java.util.List;
import offer.technical.test.model.UserResource;
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
  public ResponseEntity<List<UserResource>> getAll() {
    return ResponseEntity.ok(this.userService.getAll());
  }

  @PostMapping
  public ResponseEntity<UserResource> create(@RequestBody @Validated final UserResource user) {
    return ResponseEntity.ok(this.userService.create(user));
  }

}
