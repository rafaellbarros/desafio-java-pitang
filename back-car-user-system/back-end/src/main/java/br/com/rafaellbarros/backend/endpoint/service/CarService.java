package br.com.rafaellbarros.backend.endpoint.service;


import br.com.rafaellbarros.backend.config.properties.MessageProperty;
import br.com.rafaellbarros.backend.endpoint.mapper.CarMapper;
import br.com.rafaellbarros.core.exception.BusinessException;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.dto.UserDTO;
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

    private final UserAuthenticatedService authenticatedService;

    public CarDTO createByUserLogged(final CarDTO carDTO) {
        final UserDTO userLogged = authenticatedService.geLogged();
        carDTO.setUser(userLogged);
        return mapper.toDTO(repository.save(mapper.toEntity(carDTO)));
    }

    public List<CarDTO> findAllByUserLogged() {
        final UserDTO userLogged = authenticatedService.geLogged();
        List<Car> cars = findAllEntitiesByUserId(userLogged.getId());
        return mapper.toDTO(cars);
    }

    private List<Car> findAllEntitiesByUserId(final Long userId) {
        return repository.findCarsByUserId(userId);
    }

    public CarDTO findByIdUserLogged(final Long id) {
        final UserDTO userLogged = authenticatedService.geLogged();
        final Car car = fiindEntityByIdAndUserId(id, userLogged.getId());
        return mapper.toDTO(car);
    }

    public void deleteByIdUserLogged(final Long id) {
        final UserDTO userLogged = authenticatedService.geLogged();
        final Car car = fiindEntityByIdAndUserId(id, userLogged.getId());
        repository.delete(car);
    }

    public CarDTO updateByUserLogged(final Long id, final CarDTO carDTO) {
        final UserDTO userLogged = authenticatedService.geLogged();
        carDTO.setId(id);
        carDTO.setUser(userLogged);
        final Car car = fiindEntityByIdAndUserId(carDTO.getId(), userLogged.getId());

        mapper.fromDTO(carDTO, car);
        return  mapper.toDTO(repository.save(repository.save(car)));
    }

    private Car fiindEntityByIdAndUserId(final Long id, final Long userId) {
        return repository.findByIdAndUserId(id, userId).orElseThrow(() -> new BusinessException(MessageProperty.CAR_NOT_FOUND));
    }
}
