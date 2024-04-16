package br.com.rafaellbarros.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenApiDTO {

    @NotNull
    private String encryptedToken;
    private String dencryptedToken;

    public TokenApiDTO(final String dencryptedToken) {
        this.dencryptedToken = "Bearer " + dencryptedToken;
    }

}
