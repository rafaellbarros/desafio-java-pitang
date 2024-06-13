package br.com.rafaellbarros.backend.endpoint.service;


import br.com.rafaellbarros.backend.endpoint.mapper.CarMapper;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.entity.Car;
import br.com.rafaellbarros.core.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS)
public class CarService {

    private final CarRepository repository;

    private final CarMapper mapper;

    public CarDTO createByUserLogged(final CarDTO carDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(carDTO)));
    }

    public List<CarDTO> findAllByUserLogged(final Long userId) {
        List<Car> cars = findAllEntitiesByUserId(userId);
        return mapper.toDTO(cars);
    }

    private List<Car> findAllEntitiesByUserId(final Long userId) {
        return repository.findCarsByUserId(userId);
    }
}
