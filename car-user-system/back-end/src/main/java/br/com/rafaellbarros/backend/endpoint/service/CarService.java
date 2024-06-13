package br.com.rafaellbarros.backend.endpoint.service;


import br.com.rafaellbarros.backend.endpoint.mapper.CarMapper;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.entity.Car;
import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.repository.CarRepository;
import br.com.rafaellbarros.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS)
public class CarService {

    private final CarRepository repository;

    private final UserRepository userRepository;

    private final CarMapper mapper;

    public CarDTO createByUserLogged(final Long userId, final CarDTO carDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
        Car car = mapper.toEntity(carDTO);
        car.setUser(user);
        Car carSave = repository.save(car);
        CarDTO carDTOSaved = mapper.toDTO(carSave);
        log.info("createByUserLogged() => {}", carDTOSaved);
        return carDTOSaved;
    }

    public List<CarDTO> findAllByUserLogged(final User user) {
        List<Car> cars = findAllEntitiesByUserId(user.getId());
        return mapper.toDTO(cars);
    }

    private List<Car> findAllEntitiesByUserId(final Long userId) {
        return repository.findCarsByUserId(userId);
    }
}
