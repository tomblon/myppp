package pl.myprivatepocket.models.converters;

import org.springframework.stereotype.Component;
import pl.myprivatepocket.models.dto.ArticleShareDto;
import pl.myprivatepocket.models.entity.ArticleShareEntity;

import java.util.UUID;

@Component
public class ArticleShareConverter implements BaseConverter<ArticleShareEntity, ArticleShareDto> {
    @Override
    public ArticleShareDto convertFromEntity(ArticleShareEntity entity) {
        ArticleShareDto articleShareDto = new ArticleShareDto();
        articleShareDto.setUserMail(entity.getUserMail());
        return articleShareDto;
    }

    @Override
    public ArticleShareEntity convertFromDto(ArticleShareDto dto) {
        ArticleShareEntity articleShareEntity = new ArticleShareEntity();
        articleShareEntity.setId(UUID.randomUUID().toString());
        articleShareEntity.setUserMail(dto.getUserMail());
        return articleShareEntity;

    }
}
