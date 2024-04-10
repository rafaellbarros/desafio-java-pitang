package br.com.rafaellbarros.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -5122051060452034654L;

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private LocalDate birthday;
    private String login;
    private String password;
    private String phone;
    private String role;

}
