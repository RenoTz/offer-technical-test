package offer.technical.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import offer.technical.test.errors.AlreadyExistsException;
import offer.technical.test.model.UserEntity;
import offer.technical.test.model.UserResource;
import offer.technical.test.repositories.UserRepository;
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
  private static final String TARGET_URI_GET = TARGET_URI + "/{name}";

  @Autowired
  private UserController userController;

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private WebTestClient webClient;

  @Test
  void getUser_found() {

    final UserResource user = getMinimalUser();
    final UserEntity userEntity = getMinimalUserEntity();
    when(userRepository.findOneByName("test")).thenReturn(userEntity);

    webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(TARGET_URI_GET)
            .build("test"))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(new ParameterizedTypeReference<UserResource>() {
        }).isEqualTo(user);

    verify(userRepository, only()).findOneByName(userEntity.getName());
    verify(userRepository, times(1)).findOneByName(userEntity.getName());
  }

  @Test
  void getUser_notFound() {

    when(userRepository.findOneByName("test")).thenReturn(null);

    webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(TARGET_URI_GET)
            .build("test"))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .attribute("name", "test")
        .exchange()
        .expectStatus()
        .isNotFound();

    verify(userRepository, only()).findOneByName("test");
    verify(userRepository, times(1)).findOneByName("test");
  }

  @Test
  void create_valid() throws AlreadyExistsException {

    final UserResource user = getMinimalUser();
    final UserEntity userEntity = getMinimalUserEntity();
    when(userRepository.findOneByName("test")).thenReturn(null);
    when(userRepository.create(userEntity)).thenReturn(userEntity);

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(new ParameterizedTypeReference<UserResource>() {
        }).isEqualTo(user);

    verify(userRepository, only()).create(userEntity);
    verify(userRepository, times(1)).create(userEntity);
  }

  @Test
  void create_withBirthDateInvalid_badRequest() throws AlreadyExistsException {

    final UserResource user = getMinimalUser()
        .setBirthDate(LocalDate.of(2015, 1, 1));

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();

    verify(userRepository, never()).create(any(UserEntity.class));
  }

  @Test
  void create_withPhoneNumberInvalid_badRequest() throws AlreadyExistsException {

    final UserResource user = getMinimalUser()
        .setPhoneNumber("+123 54 87 644");

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();

    verify(userRepository, never()).create(any(UserEntity.class));
  }

  @Test
  void create_withGenderInvalid_badRequest() throws AlreadyExistsException {

    final UserResource user = getMinimalUser()
        .setGender("invalid");

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();

    verify(userRepository, never()).create(any(UserEntity.class));
  }

  @Test
  void create_withCountryInvalid_badRequest() throws AlreadyExistsException {

    final UserResource user = getMinimalUser()
        .setCountry("invalid");

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();

    verify(userRepository, never()).create(any(UserEntity.class));
  }

  @Test
  void create_alreadyExistsException_badRequest() throws AlreadyExistsException {

    final UserResource user = getMinimalUser();
    final UserEntity userEntity = getMinimalUserEntity();
    doThrow(AlreadyExistsException.class).when(userRepository).create(userEntity);

    webClient.post()
        .uri(TARGET_URI)
        .body(BodyInserters.fromValue(user))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  private UserResource getMinimalUser() {
    return new UserResource()
        .setName("test")
        .setCountry("france")
        .setBirthDate(LocalDate.of(2000, 4, 12));
  }

  private UserEntity getMinimalUserEntity() {
    return new UserEntity()
        .setName("test")
        .setCountry("france")
        .setBirthDate(LocalDate.of(2000, 4, 12));
  }

}
