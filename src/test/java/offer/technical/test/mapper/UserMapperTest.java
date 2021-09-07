package offer.technical.test.mapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import offer.technical.test.model.UserEntity;
import offer.technical.test.model.UserResource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  void listUserEntityToListUserResource() {

    final List<UserResource> userResources = Collections.singletonList(getUserResource());
    final List<UserEntity> userEntities = Collections.singletonList(getUserEntity());

    Assertions.assertThat(userMapper.listUserEntityToListUserResource(userEntities))
        .isNotEmpty()
        .isEqualTo(userResources);
  }

  @Test
  void userEntityUserResource() {

    final UserResource userResource = getUserResource();
    final UserEntity userEntity = getUserEntity();

    Assertions.assertThat(userMapper.userEntityUserResource(userEntity))
        .isNotNull()
        .isEqualTo(userResource);
  }

  @Test
  void userResourceToUserEntity() {

    final UserResource userResource = getUserResource();
    final UserEntity userEntity = getUserEntity();

    Assertions.assertThat(userMapper.userResourceToUserEntity(userResource))
        .isNotNull()
        .isEqualTo(userEntity);
  }

  private UserEntity getUserEntity() {
    return new UserEntity()
        .setName("Pierre Dupont")
        .setCountry("france")
        .setGender("M")
        .setBirthDate(LocalDate.of(2000, 4, 12))
        .setPhoneNumber("0123456789");
  }

  private UserResource getUserResource() {
    return new UserResource()
        .setName("Pierre Dupont")
        .setCountry("france")
        .setGender("M")
        .setBirthDate(LocalDate.of(2000, 4, 12))
        .setPhoneNumber("0123456789");
  }

}
