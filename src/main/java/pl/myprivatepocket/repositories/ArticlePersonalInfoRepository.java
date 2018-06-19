package pl.myprivatepocket.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.myprivatepocket.models.entity.ArticleEntity;
import pl.myprivatepocket.models.entity.ArticlePersonalInfoEntity;
import pl.myprivatepocket.models.entity.UserEntity;

import java.util.Optional;

@Repository
public interface ArticlePersonalInfoRepository extends CrudRepository<ArticlePersonalInfoEntity, Long> {
    Optional<ArticlePersonalInfoEntity> findByIdAndUserEntity(long id, UserEntity userEntity);
    Optional<ArticlePersonalInfoEntity> findByArticleEntityAndUserEntity(ArticleEntity articleEntity, UserEntity userEntity);
    Iterable<ArticlePersonalInfoEntity> findAllByArticleEntity(ArticleEntity articleEntity);
    Iterable<ArticlePersonalInfoEntity> findAllByUserEntity(UserEntity userEntity);

}
