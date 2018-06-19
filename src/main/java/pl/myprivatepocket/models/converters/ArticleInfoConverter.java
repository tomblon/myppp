package pl.myprivatepocket.models.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.myprivatepocket.models.dto.ArticleInfoDto;
import pl.myprivatepocket.models.entity.ArticlePersonalInfoEntity;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ArticleInfoConverter implements BaseConverter<ArticlePersonalInfoEntity, ArticleInfoDto> {

    @Autowired
    private TagConverter tagConverter;

    @Override
    public ArticleInfoDto convertFromEntity(ArticlePersonalInfoEntity entity) {
        ArticleInfoDto articleInfoDto = new ArticleInfoDto();
        articleInfoDto.setTitle(entity.getArticleEntity().getTitle());
        articleInfoDto.setId(entity.getId());
        articleInfoDto.setTags(tagConverter.convertFromEntities(entity.getTags()));
        return articleInfoDto;
    }

    @Override
    public ArticlePersonalInfoEntity convertFromDto(ArticleInfoDto dto) {
        return null;
    }

    @Override
    public Collection<ArticleInfoDto> convertFromEntities(Collection<ArticlePersonalInfoEntity> articleEntities) {
        return articleEntities.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }
}
