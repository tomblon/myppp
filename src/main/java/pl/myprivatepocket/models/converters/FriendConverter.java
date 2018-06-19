package pl.myprivatepocket.models.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.myprivatepocket.models.dto.FriendDto;
import pl.myprivatepocket.models.entity.FriendEntity;

@Component
public class FriendConverter implements BaseConverter<FriendEntity, FriendDto> {

    @Autowired
    UserConverter userConverter;

    @Override
    public FriendDto convertFromEntity(FriendEntity entity) {
        FriendDto friendDto = new FriendDto();
        friendDto.setId(entity.getId());
        friendDto.setName(entity.getName());
        friendDto.setEmail(entity.getEmail());
        return friendDto;
    }

    @Override
    public FriendEntity convertFromDto(FriendDto dto) {
        FriendEntity friendEntity = new FriendEntity();
        friendEntity.setName(dto.getName());
        friendEntity.setEmail(dto.getEmail());
        return friendEntity;
    }
}
