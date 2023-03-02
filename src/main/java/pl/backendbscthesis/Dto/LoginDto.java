package pl.backendbscthesis.Dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Data
public class LoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
