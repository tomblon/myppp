package pl.myprivatepocket.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.myprivatepocket.models.entity.ArticleShareEntity;

import java.util.Optional;

@Repository
public interface ArticleShareRepository extends CrudRepository<ArticleShareEntity,String>{
    Optional<ArticleShareEntity> getByArticleIdAndUserMail(long articleId, String userMail);
}
