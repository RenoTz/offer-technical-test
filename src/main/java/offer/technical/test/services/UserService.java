package offer.technical.test.services;

import offer.technical.test.mapper.UserMapper;
import offer.technical.test.model.UserResource;
import offer.technical.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;
  private final UserMapper mapper;

  @Autowired
  UserService(final UserRepository repository, final UserMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  /**
   * Retrieve a user by username
   *
   * @param name
   * @return user
   */
  public UserResource getUser(final String name) {
    return mapper.userEntityUserResource(repository.findOneByName(name));
  }

  /**
   * Register a user
   *
   * @param user
   * @return user registered
   */
  public UserResource create(final UserResource user) {
    return mapper.userEntityUserResource(repository.create(mapper.userResourceToUserEntity(user)));
  }

}
