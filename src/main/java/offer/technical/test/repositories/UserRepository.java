package offer.technical.test.repositories;

import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import offer.technical.test.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepository {

  private final MongoTemplate mongoTemplate;

  UserRepository(@Autowired MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  /**
   * Retrieve a user by username
   *
   * @param name
   * @return userEntity
   */
  public UserEntity findOneByName(final String name) {
    final Query query = new Query();
    query.addCriteria(Criteria.where("name").is(name));
    log.info("Attempting to find user with username : {}", name);
    final UserEntity userEntity = mongoTemplate.findOne(query, UserEntity.class);
    if (Objects.nonNull(userEntity)) {
      log.info("User found : {} - {} - {}", userEntity.getName(), userEntity.getBirthDate(),
          userEntity.getCountry());
    } else {
      log.info("No user found with the username : {}", name);
    }
    return userEntity;
  }

  /**
   * Register a user
   *
   * @param userEntity
   * @return userEntity registered
   */
  public UserEntity create(final UserEntity user) {
    log.info("Attempting to create user");
    final UserEntity newUser = mongoTemplate.insert(user);
    log.info("User created : {} - {} - {}", user.getName(), user.getBirthDate(), user.getCountry());
    return newUser;
  }

}
