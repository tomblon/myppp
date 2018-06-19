package pl.myprivatepocket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myprivatepocket.models.converters.TagConverter;
import pl.myprivatepocket.models.dto.TagDto;
import pl.myprivatepocket.models.entity.ArticleEntity;
import pl.myprivatepocket.models.entity.ArticlePersonalInfoEntity;
import pl.myprivatepocket.models.entity.TagEntity;
import pl.myprivatepocket.repositories.ArticleRepository;
import pl.myprivatepocket.repositories.TagRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleEditService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TagConverter tagConverter;


    ArticleEditService(){}

    public ArticlePersonalInfoEntity articleTagEdit(ArticlePersonalInfoEntity articleEntity, List<String> tags){
        Collection<TagEntity> tagEntities = articleEntity.getTags();
        List<String> tagEntityNames = tagEntities.stream().map(tagEntity -> tagEntity.getName()).collect(Collectors.toList());
        for (String tagEntityName: tagEntityNames) {
            if(!tags.contains(tagEntityName)){
               TagDto tagDto = new TagDto();
               tagDto.setName(tagEntityName);
                articleEntity.removeTag(tagConverter.convertFromDto(tagDto));
            }
        }
        List<String> tagsLeft = tagEntities.stream().map(tagEntity -> tagEntity.getName()).collect(Collectors.toList());
        for (String tag: tags) {
            if(!tagsLeft.contains(tag)){
                addTagToArticle(articleEntity, tag);
            }
        }
        return articleEntity;
    }

    private void addTagToArticle(ArticlePersonalInfoEntity articleEntity, String tag){
        Optional<TagEntity> tagEntity = tagRepository.findByName(tag);
        if(tagEntity.isPresent()) {
            articleEntity.addTag(tagEntity.get());
        }
        else{
            TagEntity newTagEntity = new TagEntity();
            newTagEntity.setName(tag);
            articleEntity.addTag(newTagEntity);

        }
    }

    public ArticlePersonalInfoEntity editComment(ArticlePersonalInfoEntity articleEntity, String comment){
        articleEntity.setComment(comment);
        return articleEntity;
    }


    public ArticleEntity editTitle(ArticleEntity articleEntity, String title){
        articleEntity.setTitle(title);
        return articleEntity;
    }

}
