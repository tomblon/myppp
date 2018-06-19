package pl.myprivatepocket.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class FriendDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @Size(max = 50)
    private String name;

    @JsonProperty("email")
    @Size(max = 100)
    private String email;
}
