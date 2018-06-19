package pl.myprivatepocket.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class TagDto {

    @JsonProperty("name")
    @Size(min = 4, max = 300)
    private String name;

    @JsonIgnore
    private List<ArticleDto> articles = new ArrayList<>();

    public List<ArticleDto> getArticles() {
        return articles;
    }

    public void addArticle(ArticleDto article) {
        articles.add(article);
    }

    public void deleteArticle(ArticleDto article) {
        articles.remove(article);
    }
}
