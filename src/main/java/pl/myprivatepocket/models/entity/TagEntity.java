package pl.myprivatepocket.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 300)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<ArticlePersonalInfoEntity> articles = new ArrayList<>();

    public void addArticle(ArticlePersonalInfoEntity article) {
        articles.add(article);
    }

    public void removeArticle(ArticlePersonalInfoEntity article) {
        articles.remove(article);
    }

}