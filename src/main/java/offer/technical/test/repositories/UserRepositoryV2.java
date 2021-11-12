package offer.technical.test.repositories;

import lombok.RequiredArgsConstructor;
import offer.technical.test.model.UserEntityV2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryV2 {

  private final MongoTemplate mongoTemplate;

  public UserEntityV2 create(UserEntityV2 user) {
    return mongoTemplate.save(user);
  }

  public UserEntityV2 getUserByName(String name) {
    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(name));
    return mongoTemplate.findOne(query, UserEntityV2.class);
  }
}
