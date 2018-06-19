package pl.myprivatepocket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myprivatepocket.models.converters.ArticleConverter;
import pl.myprivatepocket.models.converters.ArticlePersonalInfoConverter;
import pl.myprivatepocket.models.dto.ArticleDto;
import pl.myprivatepocket.models.entity.ArticleEntity;
import pl.myprivatepocket.models.entity.ArticlePersonalInfoEntity;
import pl.myprivatepocket.models.entity.UserEntity;
import pl.myprivatepocket.repositories.ArticleRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class ArticlePersonalInfoService {
    @Autowired
    ArticlePersonalInfoConverter articlePersonalInfoConverter;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleConverter articleConverter;

    @Autowired
    UrlExtractService urlExtractService;

    public ArticlePersonalInfoEntity create(ArticleDto articleDto, ArticleEntity articleEntity, UserEntity userEntity) {
        ArticlePersonalInfoEntity newArticlePersonalInfoEntity = articlePersonalInfoConverter.convertFromDto(articleDto);
        newArticlePersonalInfoEntity.setArticleEntity(articleEntity);
        newArticlePersonalInfoEntity.setUserEntity(userEntity);
        return newArticlePersonalInfoEntity;
    }

    public ArticleEntity getByDtoOrCreate(ArticleDto articleDto) throws IOException {
        ArticleEntity articleEntity;
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findByUrl(articleDto.getUrl());
        if (optionalArticleEntity.isPresent()) {
            articleEntity = optionalArticleEntity.get();
        } else {
            articleEntity = articleConverter.convertFromDto(articleDto);
            articleEntity.setText(urlExtractService.extractHtmlFromUrl(articleDto.getUrl(), false));
            articleRepository.save(articleEntity);
        }
        return articleEntity;
    }
}
