package offer.technical.test.repositories;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import offer.technical.test.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

  @InjectMocks
  private UserRepository userRepository;

  @Mock
  private MongoTemplate mongoTemplate;

  @Test
  void findAll() {
    final List<UserEntity> users = Collections.singletonList(getUser());
    when(mongoTemplate.findAll(UserEntity.class)).thenReturn(users);

    assertThat(users).isEqualTo(userRepository.findAll());
    verify(mongoTemplate, only()).findAll(UserEntity.class);
    verify(mongoTemplate, times(1)).findAll(UserEntity.class);
  }

  @Test
  void create() {
    final UserEntity user = getUser();
    when(mongoTemplate.insert(user)).thenReturn(user);

    Assertions.assertThat(user).isEqualTo(userRepository.create(user));
    verify(mongoTemplate, only()).insert(user);
    verify(mongoTemplate, times(1)).insert(user);
  }

  private UserEntity getUser() {
    return new UserEntity()
        .setName("Pierre Dupont")
        .setCountry("france")
        .setGender("M")
        .setBirthDate(LocalDate.of(2000, 4, 12))
        .setPhoneNumber("0123456789");
  }
}
