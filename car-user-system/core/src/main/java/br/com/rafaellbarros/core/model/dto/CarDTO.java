package br.com.rafaellbarros.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO implements Serializable {

    private static final long serialVersionUID = -4346508285559712310L;

    private Long id;
    private Long year;
    private String licensePlate;
    private String model;
    private String color;
    private UserDTO user;

}
