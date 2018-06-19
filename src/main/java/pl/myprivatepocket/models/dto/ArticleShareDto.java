package pl.myprivatepocket.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ArticleShareDto {
    @JsonProperty
    private String userMail;
}
