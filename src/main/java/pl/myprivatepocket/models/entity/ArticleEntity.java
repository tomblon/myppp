package pl.myprivatepocket.models.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(unique = true)
    private String url;

    @Lob
    private String text;

    /*@OneToMany
    private List<ArticlePersonalInfoEntity> articlePersonalInfoEntities;

    public void addArticlePersonalInfo(ArticlePersonalInfoEntity articlePersonalInfoEntity) {
        this.articlePersonalInfoEntities.add(articlePersonalInfoEntity);
    }

    public void removeArticlePersonalInfo(ArticlePersonalInfoEntity articlePersonalInfoEntity) {
        this.articlePersonalInfoEntities.remove(articlePersonalInfoEntity);
    }*/

    @Override
    public boolean equals(Object o) {
        return this == o
                || o instanceof ArticleEntity
                && id != null
                && id.equals(((ArticleEntity) o).id);
    }
}
