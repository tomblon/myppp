package pl.myprivatepocket.models.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.myprivatepocket.models.dto.ArticleDto;
import pl.myprivatepocket.models.entity.ArticlePersonalInfoEntity;

@Component
public class ArticlePersonalInfoConverter implements BaseConverter<ArticlePersonalInfoEntity, ArticleDto> {
    @Autowired
    private TagConverter tagConverter;

    @Override
    public ArticleDto convertFromEntity(ArticlePersonalInfoEntity entity) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(entity.getId());
        articleDto.setTitle(entity.getArticleEntity().getTitle());
        articleDto.setText(entity.getArticleEntity().getText());
        articleDto.setUrl(entity.getArticleEntity().getUrl());
        articleDto.setComment(entity.getComment());
        articleDto.setTags(tagConverter.convertFromEntities(entity.getTags()));
        return articleDto;
    }

    @Override
    public ArticlePersonalInfoEntity convertFromDto(ArticleDto dto) {
        ArticlePersonalInfoEntity articlePersonalInfoEntity = new ArticlePersonalInfoEntity();
        articlePersonalInfoEntity.setComment(dto.getComment());
        articlePersonalInfoEntity.setTags(tagConverter.convertFromDtos(dto.getTags()));
        return articlePersonalInfoEntity;
    }
}
