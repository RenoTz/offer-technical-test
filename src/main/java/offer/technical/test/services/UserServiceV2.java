package offer.technical.test.services;

import lombok.RequiredArgsConstructor;
import offer.technical.test.errors.AlreadyExistsException;
import offer.technical.test.mapper.UserMapper;
import offer.technical.test.model.UserEntity;
import offer.technical.test.model.UserEntityV2;
import offer.technical.test.model.UserResource;
import offer.technical.test.model.UserResourceV2;
import offer.technical.test.repositories.UserRepository;
import offer.technical.test.repositories.UserRepositoryV2;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceV2 {

    private final UserRepositoryV2 userRepository;
    private final UserMapper userMapper;

    public UserResourceV2 create(UserResourceV2 userResource) {

        UserEntityV2 entity = userMapper.userResourceToUserEntityV2(userResource);
        return userMapper.userEntityToUserResourceV2(userRepository.create(entity));
    }

}
