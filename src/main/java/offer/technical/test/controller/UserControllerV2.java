package offer.technical.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offer.technical.test.model.UserResourceV2;
import offer.technical.test.services.UserServiceV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/v2")
public class UserControllerV2 {

    private final UserServiceV2 userService;

    @PostMapping
    public ResponseEntity<UserResourceV2> create(@RequestBody @Validated UserResourceV2 userResource) {
        log.info("Tentative d'insertion d'un utilisateur : {} - {} - {}",
                userResource.getName(), userResource.getBirthDate(), userResource.getCountry());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userResource));
    }

    @GetMapping("{name}")
    public ResponseEntity<UserResourceV2> getUserByName(@PathVariable("name") String name) {
        UserResourceV2 user = userService.getUser(name);
        log.info("Récuperation d'un utilisateur : {} - {} - {}",
                user.getName(), user.getBirthDate(), user.getCountry());
        return ResponseEntity.ok().body(user);
    }


}
