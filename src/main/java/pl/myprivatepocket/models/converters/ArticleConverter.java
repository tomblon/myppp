package pl.myprivatepocket.models.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.myprivatepocket.models.dto.ArticleDto;
import pl.myprivatepocket.models.dto.TagDto;
import pl.myprivatepocket.models.entity.ArticleEntity;
import pl.myprivatepocket.models.entity.TagEntity;
import pl.myprivatepocket.repositories.ArticleRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ArticleConverter implements BaseConverter<ArticleEntity, ArticleDto> {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public ArticleDto convertFromEntity(ArticleEntity entity) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(entity.getId());
        articleDto.setTitle(entity.getTitle());
        articleDto.setText(entity.getText());
        articleDto.setUrl(entity.getUrl());
        /*articleDto.setComment(entity.getComment());
        articleDto.setTags(tagConverter.convertFromEntities(entity.getTags()));*/
        return articleDto;
    }

    @Override
    public ArticleEntity convertFromDto(ArticleDto dto) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(dto.getTitle());
        articleEntity.setText(dto.getText());
        articleEntity.setUrl(dto.getUrl());
        /*articleEntity.setComment(dto.getComment());
        articleEntity.setTags(tagConverter.convertFromDtos(dto.getTags()));*/
        return articleEntity;
    }
}
