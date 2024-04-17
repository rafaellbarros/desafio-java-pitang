package br.com.rafaellbarros.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SigninDTO {

    @NotNull
    private String login;

    @NotNull
    private String password;
}
