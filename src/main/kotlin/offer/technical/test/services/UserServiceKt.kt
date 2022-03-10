package offer.technical.test.services

import offer.technical.test.errors.AlreadyExistsExceptionKt
import offer.technical.test.mapper.UserMapperKt
import offer.technical.test.model.UserResourceKt
import offer.technical.test.repositories.UserRepositoryKt
import org.springframework.stereotype.Service

@Service
class UserServiceKt(var repository: UserRepositoryKt, var mapper: UserMapperKt) {

    /**
     * Retrieve a user by username
     *
     * @param name
     * @return user
     */
    fun getUser(name: String?): UserResourceKt? {
        return mapper.userEntityUserResource(repository.findOneByName(name))
    }

    /**
     * Register a user
     *
     * @param user
     * @return user registered
     */
    @kotlin.Throws(AlreadyExistsExceptionKt::class)
    fun create(user: UserResourceKt?): UserResourceKt? {
        return mapper.userEntityUserResource(mapper.userResourceToUserEntity(user)?.let { repository.create(it) })
    }

}