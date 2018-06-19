package pl.myprivatepocket.models.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.myprivatepocket.models.dto.UserDto;
import pl.myprivatepocket.models.entity.UserEntity;

@Component
public class UserConverter implements BaseConverter<UserEntity, UserDto> {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto convertFromEntity(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity convertFromDto(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userEntity.setUserMail(dto.getUserMail());
        return userEntity;
    }
}
