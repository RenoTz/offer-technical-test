package offer.technical.test.services;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import offer.technical.test.model.User;
import offer.technical.test.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Test
  void getAll() {
    final List<User> users = Collections.singletonList(getUser());
    when(userRepository.findAll()).thenReturn(users);

    Assertions.assertThat(users).isEqualTo(userService.getAll());
  }

  @Test
  void create() {
    final User user = getUser();
    when(userRepository.create(user)).thenReturn(user);

    Assertions.assertThat(user).isEqualTo(userService.create(user));
  }

  private User getUser() {
    return new User()
        .setName("Pierre Dupont")
        .setCountry("france")
        .setGender("M")
        .setBirthDate(LocalDate.of(2000, 4, 12))
        .setPhoneNumber("0123456789");
  }
}
