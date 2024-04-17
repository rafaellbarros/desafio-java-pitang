package br.com.rafaellbarros.backend.utils;

import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.model.entity.Car;
import br.com.rafaellbarros.core.model.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;

public class UserCreator {

    private static final CarDTO carDTO  = CarCreator.createValidCarDTO();
    private static final Car car  = CarCreator.createValidCar();

    public static UserDTO createUserDTOtoBeSaved() {

        return UserDTO.builder()
                .firstName("Hello")
                .lastName("World")
                .email("hello@world.com")
                .birthday(LocalDate.of(1990,5,1))
                .login("hello.world")
                .password(new BCryptPasswordEncoder().encode("h3ll0"))
                .phone("988888888")
                .build();
    }

    public static UserDTO createValidUserDTO() {
        return UserDTO.builder()
                .id(1L)
                .firstName("Hello")
                .lastName("World")
                .email("hello@world.com")
                .birthday(LocalDate.of(1990,5,1))
                .login("hello.world")
                .password(new BCryptPasswordEncoder().encode("h3ll0"))
                .phone("988888888")
                .cars(Collections.singletonList(carDTO))
                .build();
    }
    public static User createValidUser() {
        return User.builder()
                .id(1L)
                .firstName("Hello")
                .lastName("World")
                .email("hello@world.com")
                .birthday(LocalDate.of(1990,5,1))
                .login("hello.world")
                .password(new BCryptPasswordEncoder().encode("h3ll0"))
                .phone("988888888")
                .cars(Collections.singletonList(car))
                .build();
    }

}
