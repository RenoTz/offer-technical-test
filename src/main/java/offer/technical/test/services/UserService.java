package offer.technical.test.services;

import lombok.RequiredArgsConstructor;
import offer.technical.test.errors.AlreadyExistsException;
import offer.technical.test.mapper.UserMapper;
import offer.technical.test.model.UserResource;
import offer.technical.test.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final UserMapper mapper;

  /**
   * Retrieve a user by username
   *
   * @param name username
   * @return a {@link UserResource} founded
   */
  public UserResource getUser(String name) {
    return mapper.userEntityToUserResource(repository.findOneByName(name));
  }

  /**
   * Register a user
   *
   * @param user a {@link UserResource} instance
   * @return a {@link UserResource} registered instance
   * @throws AlreadyExistsException if user already exists in database
   */
  public UserResource create(UserResource user) throws AlreadyExistsException {
    return mapper.userEntityToUserResource(repository.create(mapper.userResourceToUserEntity(user)));
  }

}
