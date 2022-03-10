package offer.technical.test.services

import offer.technical.test.errors.AlreadyExistsException
import offer.technical.test.mapper.UserMapper
import offer.technical.test.model.UserResource
import offer.technical.test.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(var repository: UserRepository, var mapper: UserMapper) {

    /**
     * Retrieve a user by username
     *
     * @param name
     * @return user
     */
    fun getUser(name: String?): UserResource? {
        return mapper.userEntityUserResource(repository.findOneByName(name))
    }

    /**
     * Register a user
     *
     * @param user
     * @return user registered
     */
    @kotlin.Throws(AlreadyExistsException::class)
    fun create(user: UserResource?): UserResource? {
        return mapper.userEntityUserResource(mapper.userResourceToUserEntity(user)?.let { repository.create(it) })
    }

}