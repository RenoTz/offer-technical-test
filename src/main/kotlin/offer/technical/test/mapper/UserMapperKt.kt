package offer.technical.test.mapper

import offer.technical.test.model.UserEntityKt
import offer.technical.test.model.UserResourceKt
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UserMapperKt {

    fun userEntityUserResource(userEntity: UserEntityKt?): UserResourceKt? {
        if (userEntity == null) {
            return null
        }

        return UserResourceKt(userEntity.name, userEntity.birthDate, userEntity.country, userEntity.phoneNumber, userEntity.gender)
    }

    fun userResourceToUserEntity(userResource: UserResourceKt?): UserEntityKt? {
        if (userResource == null) {
            return null
        }

        return UserEntityKt(userResource.name, userResource.birthDate, userResource.country, userResource.phoneNumber, userResource.gender)
    }

}