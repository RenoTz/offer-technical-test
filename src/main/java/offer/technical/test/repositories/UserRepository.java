package offer.technical.test.repositories;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import offer.technical.test.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<UserEntity> findAll() {
    return mongoTemplate.findAll(UserEntity.class);
  }

  public UserEntity create(final UserEntity user) {
    log.info("Attempting to create user");
    final UserEntity newUser = mongoTemplate.insert(user);
    log.info("User created : {} - {} - {}", user.getName(), user.getBirthDate(), user.getCountry());
    return newUser;
  }

}
