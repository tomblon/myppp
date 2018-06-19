package pl.myprivatepocket.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.myprivatepocket.models.entity.UserEntity;

public interface UserDataRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findFirstByUserMail(String userMail);
}