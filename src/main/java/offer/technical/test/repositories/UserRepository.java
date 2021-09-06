package offer.technical.test.repositories;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import offer.technical.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<User> findAll() {
    return mongoTemplate.findAll(User.class);
  }

  public User create(final User user) {
    log.info("Attempting to create user");
    final User newUser = mongoTemplate.insert(user);
    log.info("User created : {} - {} - {}", user.getName(), user.getBirthDate(), user.getCountry());
    return newUser;
  }

}
