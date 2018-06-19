package pl.myprivatepocket.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.myprivatepocket.models.entity.FriendEntity;
import pl.myprivatepocket.models.entity.UserEntity;

import java.util.Optional;

public interface FriendRepository  extends CrudRepository<FriendEntity, Long> {
    Iterable<FriendEntity> findAllByUserEntity(UserEntity userEntity);

    Iterable<FriendEntity> findByNameAndUserEntity(String name, UserEntity userEntity);

    Optional<FriendEntity> findByEmailAndUserEntity(String email, UserEntity userEntity);
}
