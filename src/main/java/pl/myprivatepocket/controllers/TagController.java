package pl.myprivatepocket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.myprivatepocket.models.converters.ArticleConverter;
import pl.myprivatepocket.models.converters.ArticleInfoConverter;
import pl.myprivatepocket.models.entity.UserEntity;
import pl.myprivatepocket.repositories.ArticleRepository;
import pl.myprivatepocket.repositories.TagRepository;
import pl.myprivatepocket.repositories.UserDataRepository;

@RestController
@RequestMapping(path = "/api/tags")
@Transactional
public class TagController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private TagRepository tagRepository;


    @Autowired
    private ArticleInfoConverter articleInfoConverter;

    @Autowired
    private ArticleConverter articleConverter;

    @GetMapping
    public ResponseEntity<?> getAllTags(){
        return ResponseEntity.ok("tag");
    }

    public UserEntity getUserEntity() {
        String userMail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDataRepository.findFirstByUserMail(userMail);
    }

}
