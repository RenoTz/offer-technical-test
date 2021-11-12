package offer.technical.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offer.technical.test.model.UserResource;
import offer.technical.test.model.UserResourceV2;
import offer.technical.test.services.UserServiceV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/v2")
public class UserControllerV2 {

    private final UserServiceV2 userService;

    @PostMapping
    public UserResourceV2 create(UserResourceV2 userResource) {
        log.info("Tentative d'insertion d'un utilisateur : {} - {} - {}",
                userResource.getName(), userResource.getBirthDate(), userResource.getCountry());
        return userService.create(userResource);
    }

    @GetMapping("name")
    public UserResourceV2 getUser(String name) {
        UserResourceV2 user = userService.getUser(name);
        log.info("Récuperation d'un utilisateur : {} - {} - {}",
                user.getName(), user.getBirthDate(), user.getCountry());
        return user;
    }


}
