package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.backend.endpoint.mapper.CarMapper;
import br.com.rafaellbarros.backend.utils.CarCreator;
import br.com.rafaellbarros.backend.utils.UserCreator;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.model.entity.Car;
import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.repository.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Collections.singletonList;

@ExtendWith(SpringExtension.class)
class CarServiceTest {

    @InjectMocks
    private CarService service;
    @Mock
    private CarRepository carRepositoryMock;

    @Mock
    private CarMapper carMapperMock;

    private final CarDTO carDTOMock = CarCreator.createValidCarDTO();

    private final Car carMock = CarCreator.createValidCar();

    private final User userMock = UserCreator.createValidUser();

    private final UserDTO userDTOMock = UserCreator.createValidUserDTO();


    @BeforeEach
    void setUp() {

        BDDMockito.when(carMapperMock.toDTO(carRepositoryMock.findCarsByUserId(ArgumentMatchers.anyLong())))
                .thenReturn(singletonList(carDTOMock));

        BDDMockito.when(carMapperMock.toDTO(carRepositoryMock.save(ArgumentMatchers.any(Car.class))))
                .thenReturn(carDTOMock);

        BDDMockito.when(carMapperMock.toDTO(carMock))
                .thenReturn(carDTOMock);

        BDDMockito.doNothing().when(carRepositoryMock).delete(ArgumentMatchers.any(Car.class));



    }

    @Test
    void findAllByUserLogged() {

        List<CarDTO>  carsDTO = service.findAllByUserLogged(userMock.getId());

        Assertions.assertThat(carsDTO)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void testCreateCarByUserLogged() {
        final CarDTO carDTOtoBeSaved = CarCreator.createCarDTOtoBeSaved();
        carDTOtoBeSaved.setUser(userDTOMock);

        final CarDTO carDTO = service.createByUserLogged(carDTOtoBeSaved);

        Assertions.assertThat(carDTO).isNotNull().isEqualTo(carDTOMock);
    }
}
