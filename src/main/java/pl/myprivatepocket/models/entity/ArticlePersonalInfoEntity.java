package pl.myprivatepocket.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class ArticlePersonalInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"))
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "article_id"))
    private ArticleEntity articleEntity;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "article_tag",
            joinColumns = @JoinColumn(name = "article_personal_info_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Collection<TagEntity> tags = new ArrayList<>();

    @Column(length = 400)
    private String comment;

    public void addTag(TagEntity tag) {
        tags.add(tag);
    }

    public void removeTag(TagEntity tag) {
        tags.remove(tag);
    }
}
