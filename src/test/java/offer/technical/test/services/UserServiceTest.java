package offer.technical.test.services;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import offer.technical.test.errors.AlreadyExistsException;
import offer.technical.test.mapper.UserMapper;
import offer.technical.test.model.UserEntity;
import offer.technical.test.model.UserResource;
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

  @Mock
  private UserMapper userMapper;

  @Test
  void getUser() {
    final UserEntity userEntity = getUserEntity();
    final UserResource userResource = getUserResource();
    when(userRepository.findOneByName(userEntity.getName())).thenReturn(userEntity);
    when(userMapper.userEntityToUserResource(userEntity)).thenReturn(userResource);

    assertThat(userResource).isEqualTo(userService.getUser(userResource.getName()));
    verify(userRepository, only()).findOneByName(userEntity.getName());
    verify(userRepository, times(1)).findOneByName(userEntity.getName());
  }

  @Test
  void create() throws AlreadyExistsException {
    final UserEntity userEntity = getUserEntity();
    final UserResource userResource = getUserResource();
    when(userRepository.create(userEntity)).thenReturn(userEntity);
    when(userMapper.userEntityToUserResource(userEntity)).thenReturn(userResource);
    when(userMapper.userResourceToUserEntity(userResource)).thenReturn(userEntity);

    Assertions.assertThat(userResource).isEqualTo(userService.create(userResource));
    verify(userRepository, only()).create(userEntity);
    verify(userRepository, times(1)).create(userEntity);
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
