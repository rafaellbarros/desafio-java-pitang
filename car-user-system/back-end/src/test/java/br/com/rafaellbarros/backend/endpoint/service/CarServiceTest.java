package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.backend.endpoint.mapper.CarMapper;
import br.com.rafaellbarros.backend.utils.CarCreator;
import br.com.rafaellbarros.core.model.dto.CarDTO;
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
    private CarRepository repositoryMock;

    @Mock
    private CarMapper mapperMock;

    private final CarDTO carDTOMock = CarCreator.createValidCarDTO();

    private final Car carMock = CarCreator.createValidCar();


    @BeforeEach
    void setUp() {

        BDDMockito.when(mapperMock.toDTO(carMock))
                .thenReturn(carDTOMock);

        BDDMockito.when(mapperMock.toDTO(repositoryMock.findCarsByUserId(ArgumentMatchers.anyLong())))
                .thenReturn(singletonList(carDTOMock));
    }

    @Test
    void findAllByUserLogged() {

        User user = new User();
        user.setId(1L);
        List<CarDTO>  carsDTO = service.findAllByUserLogged(user);

        Assertions.assertThat(carsDTO)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }
}
