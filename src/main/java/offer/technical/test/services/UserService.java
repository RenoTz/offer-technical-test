package offer.technical.test.services;

import java.util.List;
import offer.technical.test.mapper.UserMapper;
import offer.technical.test.model.UserResource;
import offer.technical.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private UserMapper mapper;

  public List<UserResource> getAll() {
    return mapper.listUserEntityToListUserResource(repository.findAll());
  }

  public UserResource create(final UserResource user) {
    return mapper.userEntityUserResource(repository.create(mapper.userResourceToUserEntity(user)));
  }

}
