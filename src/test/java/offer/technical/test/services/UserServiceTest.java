package offer.technical.test.services;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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
  void getAll() {
    final List<UserEntity> userEntities = Collections.singletonList(getUserEntity());
    final List<UserResource> userResources = Collections.singletonList(getUserResource());
    when(userRepository.findAll()).thenReturn(userEntities);
    when(userMapper.listUserEntityToListUserResource(anyList())).thenReturn(userResources);

    assertThat(userResources).isEqualTo(userService.getAll());
    verify(userRepository, only()).findAll();
    verify(userRepository, times(1)).findAll();
  }

  @Test
  void create() {
    final UserEntity userEntity = getUserEntity();
    final UserResource userResource = getUserResource();
    when(userRepository.create(userEntity)).thenReturn(userEntity);
    when(userMapper.userEntityUserResource(userEntity)).thenReturn(userResource);
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
