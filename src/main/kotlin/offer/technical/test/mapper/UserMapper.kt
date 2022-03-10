package offer.technical.test.mapper

import offer.technical.test.model.UserEntity
import offer.technical.test.model.UserResource
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun userEntityUserResource(userEntity: UserEntity?): UserResource? {
        if (userEntity == null) {
            return null
        }

        return UserResource(userEntity.name, userEntity.birthDate, userEntity.country, userEntity.phoneNumber, userEntity.gender)
    }

    fun userResourceToUserEntity(userResource: UserResource?): UserEntity? {
        if (userResource == null) {
            return null
        }

        return UserEntity(userResource.name, userResource.birthDate, userResource.country, userResource.phoneNumber, userResource.gender)
    }

}