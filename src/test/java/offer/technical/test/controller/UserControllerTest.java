package offer.technical.test.controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import offer.technical.test.model.UserResource;
import offer.technical.test.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerTest {

  private static final String TARGET_URI = "/users";

  @Autowired
  private UserController userController;

  @MockBean
  private UserService userService;

  @Autowired
  private WebTestClient webClient;

  @Test
  void getAll() {

    final UserResource user = getMinimalUser();
    final List<UserResource> users = Collections.singletonList(user);
    when(userService.getAll()).thenReturn(users);

    webClient.get()
        .uri(TARGET_URI)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(new ParameterizedTypeReference<UserResource>() {
        }).hasSize(1)
        .contains(user);

    verify(userService, only()).getAll();
    verify(userService, times(1)).getAll();
  }

  @Test
  void create_valid() {

    final UserResource user = getMinimalUser();
    when(userService.create(user)).thenReturn(user);

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(new ParameterizedTypeReference<UserResource>() {
        }).isEqualTo(user);

    verify(userService, only()).create(user);
    verify(userService, times(1)).create(user);
  }

  @Test
  void create_withBirthDateInvalid_badRequest() {

    final UserResource user = getMinimalUser()
        .setBirthDate(LocalDate.of(2015, 1, 1));

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();

    verify(userService, never()).create(user);
  }

  @Test
  void create_withPhoneNumberInvalid_badRequest() {

    final UserResource user = getMinimalUser()
        .setPhoneNumber("+123 54 87 644");

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();

    verify(userService, never()).create(user);
  }

  @Test
  void create_withGenderInvalid_badRequest() {

    final UserResource user = getMinimalUser()
        .setGender("invalid");

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();

    verify(userService, never()).create(user);
  }

  @Test
  void create_withCountryInvalid_badRequest() {

    final UserResource user = getMinimalUser()
        .setCountry("invalid");

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();

    verify(userService, never()).create(user);
  }

  private UserResource getMinimalUser() {
    return new UserResource()
        .setName("Pierre Dupont")
        .setCountry("france")
        .setBirthDate(LocalDate.of(2000, 4, 12));
  }

}
