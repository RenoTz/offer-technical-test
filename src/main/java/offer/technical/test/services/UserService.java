package offer.technical.test.services;

import java.util.List;
import offer.technical.test.model.User;
import offer.technical.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> getAll() {
    return repository.findAll();
  }

  public User create(final User user) {
    return repository.create(user);
  }

}
