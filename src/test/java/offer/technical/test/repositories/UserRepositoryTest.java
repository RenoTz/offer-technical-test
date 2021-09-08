package offer.technical.test.repositories;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import offer.technical.test.errors.AlreadyExistsException;
import offer.technical.test.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

  @InjectMocks
  private UserRepository userRepository;

  @Mock
  private MongoTemplate mongoTemplate;

  @Test
  void findOneByName() {
    final UserEntity user = getUser();
    final Query query = new Query();
    query.addCriteria(Criteria.where("name").is(user.getName()));
    when(mongoTemplate.findOne(query, UserEntity.class)).thenReturn(user);

    assertThat(user).isEqualTo(userRepository.findOneByName(user.getName()));
    verify(mongoTemplate, only()).findOne(query, UserEntity.class);
    verify(mongoTemplate, times(1)).findOne(query, UserEntity.class);
  }

  @Test
  void create_ok() throws AlreadyExistsException {
    final UserEntity user = getUser();
    final Query query = new Query();
    query.addCriteria(Criteria.where("name").is(user.getName()));
    when(mongoTemplate.findOne(query, UserEntity.class)).thenReturn(null);
    when(mongoTemplate.insert(user)).thenReturn(user);

    Assertions.assertThat(user).isEqualTo(userRepository.create(user));
    verify(mongoTemplate, times(1)).findOne(query, UserEntity.class);
    verify(mongoTemplate, times(1)).insert(user);
  }

  @Test
  void create_alreadyExistsException() {
    final UserEntity user = getUser();
    final Query query = new Query();
    query.addCriteria(Criteria.where("name").is(user.getName()));
    when(mongoTemplate.findOne(query, UserEntity.class)).thenReturn(user);

    Assertions.assertThatExceptionOfType(AlreadyExistsException.class)
        .isThrownBy(() -> userRepository.create(user))
        .withMessage("User already exists in database");

    verify(mongoTemplate, times(1)).findOne(query, UserEntity.class);
    verify(mongoTemplate, times(0)).insert(user);
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
