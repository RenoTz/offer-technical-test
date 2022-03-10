package offer.technical.test.repositories

import mu.KotlinLogging
import offer.technical.test.errors.AlreadyExistsException
import offer.technical.test.model.UserEntity
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class UserRepository(private var mongoTemplate: MongoTemplate) {

    private val log = KotlinLogging.logger {}

    /**
     * Retrieve a user by username
     *
     * @param name
     * @return UserEntityKt
     */
    open fun findOneByName(name: String?): UserEntity? {
        val query = Query()
        query.addCriteria(Criteria.where("name").`is`(name))
        log.info("Attempting to find user with username : {}", name)
        val userEntity: UserEntity? = mongoTemplate.findOne(query, UserEntity::class.java)
        if (Objects.nonNull(userEntity)) {
            log.info("User found : {} - {} - {}", userEntity?.name, userEntity?.birthDate, userEntity?.country)
        } else {
            log.info("No user found with the username : {}", name)
        }
        return userEntity
    }

    /**
     * Register a user
     *
     * @param user
     * @return userEntity registered
     */
    @Throws(AlreadyExistsException::class)
    open fun create(user: UserEntity): UserEntity? {
        val newUser: UserEntity?
        log.info("Attempting to create user")
        if (Objects.isNull(findOneByName(user.name))) {
            newUser = mongoTemplate.insert(user)
            log.info("User created : {} - {} - {}", user.name, user.birthDate, user.country)
        } else {
            log.error("User already exists in database with username : {}", user.name)
            throw AlreadyExistsException("User already exists in database")
        }
        return newUser
    }
}