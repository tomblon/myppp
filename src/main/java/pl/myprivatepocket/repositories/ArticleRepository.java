package pl.myprivatepocket.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.myprivatepocket.models.entity.ArticleEntity;
import pl.myprivatepocket.models.entity.UserEntity;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
/*

   Iterable<ArticleEntity> findAllByUserEntity(UserEntity userEntity);

   Optional<ArticleEntity> findByIdAndUserEntity(long id, UserEntity userEntity);
*/

   Optional<ArticleEntity> findByUrl(String url);
}
