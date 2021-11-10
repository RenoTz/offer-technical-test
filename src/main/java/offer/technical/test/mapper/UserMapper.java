package offer.technical.test.mapper;

import offer.technical.test.model.UserEntity;
import offer.technical.test.model.UserEntityV2;
import offer.technical.test.model.UserResource;
import offer.technical.test.model.UserResourceV2;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

      UserResource userEntityToUserResource(UserEntity userEntity);

      UserEntity userResourceToUserEntity(UserResource userResource);

      UserResourceV2 userEntityToUserResourceV2(UserEntityV2 userEntity);

      UserEntityV2 userResourceToUserEntityV2(UserResourceV2 userResource);
}
