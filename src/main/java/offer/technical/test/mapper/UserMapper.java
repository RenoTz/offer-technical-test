package offer.technical.test.mapper;

import java.util.List;
import offer.technical.test.model.UserEntity;
import offer.technical.test.model.UserResource;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

      List<UserResource> listUserEntityToListUserResource(final List<UserEntity> userEntities);

      UserResource userEntityUserResource(final UserEntity userEntity);

      UserEntity userResourceToUserEntity(final UserResource userResource);
}
