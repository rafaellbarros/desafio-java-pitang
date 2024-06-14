package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.backend.endpoint.mapper.CarMapper;
import br.com.rafaellbarros.backend.utils.CarCreator;
import br.com.rafaellbarros.backend.utils.UserCreator;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.model.entity.Car;
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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.doNothing;

@SpringJUnitConfig
@ExtendWith(SpringExtension.class)
class CarServiceTest {

    @InjectMocks
    private CarService service;

    @Mock
    private CarRepository carRepositoryMock;

    @Mock
    private CarMapper carMapperMock;

    @Mock
    private UserAuthenticatedService authenticatedServiceMock;

    private final CarDTO carDTOMock = CarCreator.createValidCarDTO();
    private final Car carMock = CarCreator.createValidCar();
    private final UserDTO userDTOMock = UserCreator.createValidUserDTO();

    @BeforeEach
    void setUp() {
        BDDMockito.when(authenticatedServiceMock.geLogged())
                .thenReturn(userDTOMock);

        BDDMockito.when(carMapperMock.toDTO(carRepositoryMock.findCarsByUserId(ArgumentMatchers.anyLong())))
                .thenReturn(singletonList(carDTOMock));

        BDDMockito.when(carMapperMock.toDTO(carRepositoryMock.save(ArgumentMatchers.any(Car.class))))
                .thenReturn(carDTOMock);

        BDDMockito.when(carMapperMock.toDTO(carMock))
                .thenReturn(carDTOMock);

        BDDMockito.when(carRepositoryMock.findByIdAndUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(carMock));

        doNothing().when(carRepositoryMock).delete(ArgumentMatchers.any(Car.class));
    }

    @Test
    void findAllByUserLogged() {
        List<CarDTO> carsDTO = service.findAllByUserLogged();
        Assertions.assertThat(carsDTO)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void testCreateByUserLogged() {
        final CarDTO carDTOtoBeSaved = CarCreator.createCarDTOtoBeSaved();
        carDTOtoBeSaved.setUser(userDTOMock);

        BDDMockito.when(carMapperMock.toEntity(carDTOtoBeSaved))
                .thenReturn(carMock);

        BDDMockito.when(carRepositoryMock.save(carMock))
                .thenReturn(carMock);

        final CarDTO carDTO = service.createByUserLogged(carDTOtoBeSaved);

        Assertions.assertThat(carDTO).isNotNull().isEqualTo(carDTOMock);
    }

    @Test
    void testFindByIdUserLogged() {
        final Long expectedId = carDTOMock.getId();
        final CarDTO carDTO = service.findByIdUserLogged(1L);

        Assertions.assertThat(carDTO).isNotNull();
        Assertions.assertThat(carDTO.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    void testDeleteByIdUserLogged() {
        Assertions.assertThatCode(() -> service.deleteByIdUserLogged(1L))
                .doesNotThrowAnyException();
    }

    @Test
    void testUpdateByUserLogged() {
        final CarDTO validUpdateCarDTO = CarCreator.createValidUpdateCarDTO();

        BDDMockito.when(carMapperMock.toEntity(validUpdateCarDTO))
                .thenReturn(carMock);

        BDDMockito.when(carRepositoryMock.save(carMock))
                .thenReturn(carMock);

        final CarDTO carDTO = service.updateByUserLogged(1L, validUpdateCarDTO);

        Assertions.assertThat(carDTO).isNotNull().isEqualTo(validUpdateCarDTO);
    }
}
