package offer.technical.test.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offer.technical.test.errors.AlreadyExistsException;
import offer.technical.test.model.UserEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepository {

  private final MongoTemplate mongoTemplate;

  /**
   * Retrieve a user by username
   *
   * @param name username
   * @return {@link UserEntity} instance
   */
  public UserEntity findOneByName(String name) {
    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(name));
    log.info("Attempting to find user with username : {}", name);
    UserEntity userEntity = mongoTemplate.findOne(query, UserEntity.class);
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
   * @param user a {@link UserEntity} instance
   * @return {@link UserEntity} registered instance
   * @throws AlreadyExistsException if user already exists in database
   */
  public UserEntity create(UserEntity user) throws AlreadyExistsException {
    UserEntity newUser;
    log.info("Attempting to create user");
    if (Objects.isNull(findOneByName(user.getName()))) {
      newUser = mongoTemplate.insert(user);
      log.info("User created : {} - {} - {}", user.getName(), user.getBirthDate(),
          user.getCountry());
    } else {
      log.error("User already exists in database with username : {}", user.getName());
      throw new AlreadyExistsException("User already exists in database");
    }
    return newUser;
  }

}
