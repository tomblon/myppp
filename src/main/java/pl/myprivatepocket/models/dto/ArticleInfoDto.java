package pl.myprivatepocket.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class ArticleInfoDto {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("tags")
    private Collection<TagDto> tags = new ArrayList<>();

    public void addTag(TagDto tag) {
        tags.add(tag);
    }

    public void removeTag(TagDto tag) {
        tags.remove(tag);
    }
}
