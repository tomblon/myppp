package pl.myprivatepocket.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class ArticleDto {

    @JsonProperty("id")
    @ApiModelProperty(notes = "The article id number")
    private long id;

    @JsonProperty("title")
    @Size(min = 4, max = 50)
    @ApiModelProperty(notes = "The title of a article. It has to hav min 4 to max 50 chars.")
    private String title;

    @JsonProperty("url")
    @Size(min = 4, max = 100)
    @ApiModelProperty(notes = "The title of a article. It has to hav min 4 to max 100 chars.")
    private String url;

    @JsonProperty("text")
    @ApiModelProperty(notes = "The text of a article. It's Lob.")
    private String text;

    @JsonProperty("comment")
    @Size(max = 400)
    @ApiModelProperty(notes = "The comment of a article. Optional max 400 chars.")
    private String comment;

    @JsonProperty("tags")
    @ApiModelProperty(notes = "The list of  tags.")
    private Collection<TagDto> tags = new ArrayList<>();

    public void addTag(TagDto tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

}
