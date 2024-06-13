package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.core.model.entity.Car;
import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.repository.CarRepository;
import br.com.rafaellbarros.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataInitializrService implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        final List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.info("[DataInitializr] Start...");
            final Car car1 = buildCar("PRETA", "KBF-5060", "GM/CHEVETTE MARAJO", 1987L);
            final User user1 = buildUser(LocalDate.of(1990, 5, 1)
                    , "xablau@gmail.com"
                    , "Xablau"
                    , "Silva"
                    , "xablau"
                    , "123"
                    , "988888888", Collections.singletonList(car1));
            final User user2 = buildUser(LocalDate.of(1988, 11, 29)
                    , "rafaelbarros.softwareengineer@gmail.com"
                    , "Rafael"
                    , "Silva"
                    , "rafaellbarros"
                    , "root@123"
                    , "977777777", new ArrayList<>());
            createUser(user1);
            createUser(user2);
        }

    }

    private void createUser(final User user) {
        userRepository.save(user);
        log.info("[DataInitializr] createUser() {}", user);
    }

    private User buildUser(final LocalDate birthday, final String email, final String firstName, final String lastName,
                           final String login, final String password, final String phone, final List<Car> cars) {
        return User.builder().birthday(birthday)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .login(login)
                .password(new BCryptPasswordEncoder().encode(password))
                .phone(phone)
                .cars(cars)
                .build();
    }

    private Car buildCar(final String color, final String licencePlate, final String model, final Long year) {
        return Car.builder()
                .color(color)
                .licensePlate(licencePlate)
                .model(model)
                .year(year)
                .build();
    }
}
