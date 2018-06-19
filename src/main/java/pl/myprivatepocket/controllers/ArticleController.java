package pl.myprivatepocket.controllers;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.myprivatepocket.models.converters.ArticleInfoConverter;
import pl.myprivatepocket.models.converters.ArticlePersonalInfoConverter;
import pl.myprivatepocket.models.converters.ArticleShareConverter;
import pl.myprivatepocket.models.dto.ArticleDto;
import pl.myprivatepocket.models.dto.ArticleShareDto;
import pl.myprivatepocket.models.entity.ArticleEntity;
import pl.myprivatepocket.models.entity.ArticlePersonalInfoEntity;
import pl.myprivatepocket.models.entity.ArticleShareEntity;
import pl.myprivatepocket.models.entity.UserEntity;
import pl.myprivatepocket.repositories.ArticlePersonalInfoRepository;
import pl.myprivatepocket.repositories.ArticleRepository;
import pl.myprivatepocket.repositories.ArticleShareRepository;
import pl.myprivatepocket.repositories.UserDataRepository;
import pl.myprivatepocket.services.ArticleEditService;
import pl.myprivatepocket.services.ArticlePersonalInfoService;
import pl.myprivatepocket.services.MailService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/articles")
@Transactional
@Api(description="Operations connected with artiles management")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private ArticlePersonalInfoRepository articlePersonalInfoRepository;

    @Autowired
    private ArticleInfoConverter articleInfoConverter;

    @Autowired
    private ArticleEditService articleEditService;

    @Autowired
    private ArticlePersonalInfoConverter articlePersonalInfoConverter;

    @Autowired
    private ArticlePersonalInfoService articlePersonalInfoService;

    @Autowired
    private ArticleShareConverter articleShareConverter;

    @Autowired
    private ArticleShareRepository articleShareRepository;

    @Autowired
    private MailService mailService;

    @GetMapping
    @Transactional
    public ResponseEntity<?> getArticles() {
        return ResponseEntity.ok(articleInfoConverter
                .convertFromEntities(Lists.newArrayList(
                        articlePersonalInfoRepository.findAllByUserEntity(getUserEntity())
                )));
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Get a article by id", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
        }
    )
    public ResponseEntity<?> getArticleById(@PathVariable long id) throws NotFoundException {
        Optional<ArticlePersonalInfoEntity> articlePersonalInfoEntity = articlePersonalInfoRepository.findByIdAndUserEntity(id, getUserEntity());
        if (articlePersonalInfoEntity.isPresent()) {
            ArticleDto articleDto = articlePersonalInfoConverter.convertFromEntity(articlePersonalInfoEntity.get());
            return ResponseEntity.ok(articleDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> addArticle(@RequestBody ArticleDto articleDto) throws IOException {
        ArticleEntity articleEntity = articlePersonalInfoService.getByDtoOrCreate(articleDto);
        UserEntity userEntity = getUserEntity();
        Optional<ArticlePersonalInfoEntity> optionalArticlePersonalInfoEntity = articlePersonalInfoRepository.findByArticleEntityAndUserEntity(articleEntity, userEntity);
        if (optionalArticlePersonalInfoEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Article already in your collection");
        }
        ArticlePersonalInfoEntity newArticlePersonalInfoEntity = articlePersonalInfoService.create(articleDto, articleEntity, userEntity);
        articlePersonalInfoRepository.save(newArticlePersonalInfoEntity);
        return ResponseEntity
                .ok(articlePersonalInfoConverter.convertFromEntity(newArticlePersonalInfoEntity));
    }

    @PostMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<?> editArticle(@PathVariable long id, @RequestBody List<String> tagDtos) throws IOException {
        Optional<ArticlePersonalInfoEntity> articlePersonalInfoEntity = articlePersonalInfoRepository.findById(id);
        if (articlePersonalInfoEntity.isPresent()) {
            ArticlePersonalInfoEntity articlePersonalInfoEntityNew = articleEditService.articleTagEdit(articlePersonalInfoEntity.get(), tagDtos);
            articlePersonalInfoRepository.save(articlePersonalInfoEntityNew);
            return ResponseEntity.ok(articlePersonalInfoConverter.convertFromEntity(articlePersonalInfoEntityNew));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<?> deleteArticle(@PathVariable long id) {
        Optional<ArticlePersonalInfoEntity> articlePersonalInfoEntity = articlePersonalInfoRepository.findById(id);
        if (articlePersonalInfoEntity.isPresent()) {
            articlePersonalInfoRepository.deleteById(id);
            if (Iterables.size(articlePersonalInfoRepository.findAllByArticleEntity(articlePersonalInfoEntity.get().getArticleEntity())) == 0) {
                articleRepository.delete(articlePersonalInfoEntity.get().getArticleEntity());
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/share")
    @Transactional
    public ResponseEntity shareArticle(@PathVariable long id, @RequestBody ArticleShareDto articleShareDto) {
        long articleId = articlePersonalInfoRepository.findById(id)
                .get()
                .getArticleEntity()
                .getId();
        Optional<ArticleShareEntity> optionalArticleShareEntity = articleShareRepository
                .getByArticleIdAndUserMail(articleId, articleShareDto.getUserMail());
        if (optionalArticleShareEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("invitation already sent!");
        }
        ArticleShareEntity articleShareEntity = articleShareConverter.convertFromDto(articleShareDto);
        articleShareEntity.setArticleId(articleId);
        articleShareRepository.save(articleShareEntity);
        String message = "http://localhost:4200/#/share_link/" + articleShareEntity.getId();
        mailService.sendSimpleMessage(articleShareDto.getUserMail(), message);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/share/{id}")
    @Transactional
    public ResponseEntity<?> acceptShared(@PathVariable String id) {
        Optional<ArticleShareEntity> optionalArticleShareEntity =
                articleShareRepository.findById(id);
        if (!optionalArticleShareEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No invitation found");
        }
        ArticleShareEntity articleShareEntity = optionalArticleShareEntity.get();
        Optional<ArticleEntity> articleEntityOptional =
                articleRepository.findById(articleShareEntity.getArticleId());
        if (!articleEntityOptional.isPresent()) {
            articleShareRepository.delete(articleShareEntity);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No article found");
        }
        ArticleEntity articleEntity = articleEntityOptional.get();
        Optional<ArticlePersonalInfoEntity> optionalArticlePersonalInfoEntity =
                articlePersonalInfoRepository.findByArticleEntityAndUserEntity(articleEntity, getUserEntity());
        if (optionalArticlePersonalInfoEntity.isPresent()) {
            articleShareRepository.delete(articleShareEntity);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("user has given article");
        }
        if (!articleShareEntity.getUserMail().equals(getUserEntity().getUserMail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user emails does not match");
        }
        ArticlePersonalInfoEntity articlePersonalInfoEntity = new ArticlePersonalInfoEntity();
        articlePersonalInfoEntity.setUserEntity(getUserEntity());
        articlePersonalInfoEntity.setArticleEntity(articleEntity);
        articlePersonalInfoRepository.save(articlePersonalInfoEntity);
        articleShareRepository.delete(articleShareEntity);
        return ResponseEntity.status(HttpStatus.OK).body(articlePersonalInfoConverter.convertFromEntity(articlePersonalInfoEntity));
    }

    public UserEntity getUserEntity() {
        String userMail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDataRepository.findFirstByUserMail(userMail);
    }
}
