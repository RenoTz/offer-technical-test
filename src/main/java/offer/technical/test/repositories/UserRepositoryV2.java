package offer.technical.test.repositories;

import lombok.RequiredArgsConstructor;
import offer.technical.test.model.UserEntityV2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryV2 {

  private final MongoTemplate mongoTemplate;

  public UserEntityV2 create(UserEntityV2 user) {
    return mongoTemplate.save(user);
  }

}
