package pl.backendbscthesis.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@AllArgsConstructor
@Setter
@Getter
public class EmailDto {
    @Email
    private String email;
    private String subject;
    private String text;
    private Boolean isHtmlContent;
}
