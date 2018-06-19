package pl.myprivatepocket.models.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ArticleShareEntity {
    @Id
    private String id;
    @Column
    private String userMail;
    @Column
    private long articleId;
}
