package br.com.rafaellbarros.backend.utils;

import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.dto.UserDTO;

import java.time.LocalDate;
import java.util.Collections;

public class UserCreator {

    private static final CarDTO carDTO  = CarCreator.createValidCarDTO();

    public static UserDTO createValidUserDTO() {
        return UserDTO.builder()
                .id(1L)
                .firstName("Hello")
                .lastName("World")
                .email("hello@world.com")
                .birthday(LocalDate.of(1990,5,1))
                .login("hello.world")
                .password("h3ll0")
                .phone("988888888")
                .cars(Collections.singletonList(carDTO))
                .build();
    }
}
