package pl.myprivatepocket.models.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.myprivatepocket.models.dto.ArticleDto;
import pl.myprivatepocket.models.dto.TagDto;
import pl.myprivatepocket.models.entity.ArticleEntity;
import pl.myprivatepocket.models.entity.TagEntity;
import pl.myprivatepocket.repositories.TagRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TagConverter implements BaseConverter<TagEntity, TagDto> {
/*
    private final ArticleConverter articleConverter;

    TagConverter(ArticleConverter articleConverter) {
        this.articleConverter = articleConverter;
    }*/

    @Autowired
    TagRepository tagRepository;

    @Override
    public TagDto convertFromEntity(TagEntity entity) {
        TagDto tagDto = new TagDto();
        tagDto.setName(entity.getName());
        return tagDto;
    }

    @Override
    public TagEntity convertFromDto(TagDto dto) {
        Optional<TagEntity> tagEntity = tagRepository.findByName(dto.getName());
        if(tagEntity.isPresent())
            return tagEntity.get();
        else {
            TagEntity newTagEntity = new TagEntity();
            newTagEntity.setName(dto.getName());
            return newTagEntity;
        }
    }
}
