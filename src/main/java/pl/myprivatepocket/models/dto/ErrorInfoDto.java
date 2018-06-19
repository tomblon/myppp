package pl.myprivatepocket.models.dto;

import lombok.Data;

@Data
public class ErrorInfoDto {
    private String message;

    public ErrorInfoDto(String message) {
        this.message = message;
    }

    public ErrorInfoDto() {
    }
}
