package offer.technical.test.mapper;

import offer.technical.test.model.UserEntity;
import offer.technical.test.model.UserResource;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

      UserResource userEntityUserResource(final UserEntity userEntity);

      UserEntity userResourceToUserEntity(final UserResource userResource);
}
