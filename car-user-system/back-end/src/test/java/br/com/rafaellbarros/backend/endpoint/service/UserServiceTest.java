package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.backend.endpoint.mapper.UserMapper;
import br.com.rafaellbarros.backend.utils.UserCreator;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.repository.UserRepository;
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
import java.util.Optional;

import static java.util.Collections.singletonList;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repositoryMock;
    @Mock
    private UserMapper mapperMock;
    private final UserDTO userDTOMock = UserCreator.createValidUserDTO();

    private final User userMock = UserCreator.createValidUser();

    @BeforeEach
    void setUp() {

        BDDMockito.when(mapperMock.toDTO(repositoryMock.findAll()))
                .thenReturn(singletonList(userDTOMock));

        BDDMockito.when(mapperMock.toDTO(repositoryMock.save(ArgumentMatchers.any(User.class))))
                .thenReturn(userDTOMock);

        BDDMockito.when(mapperMock.toDTO(userMock))
                .thenReturn(userDTOMock);
        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(userMock));

        BDDMockito.doNothing().when(repositoryMock).delete(ArgumentMatchers.any(User.class));


    }

    @Test
    void testFindAll() {

        List<UserDTO> usersDTO = service.findAll();

        Assertions.assertThat(usersDTO)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void testCreate() {
        final UserDTO userDTO = service.create(UserCreator.createUserDTOtoBeSaved());

        Assertions.assertThat(userDTO).isNotNull().isEqualTo(userDTOMock);
    }

    @Test
    void testFindById() {
        final Long expectedId = userDTOMock.getId();

        final UserDTO userDTO = service.findById(1L);

        Assertions.assertThat(userDTO).isNotNull();

        Assertions.assertThat(userDTO.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    void testDeleteById() {
        Assertions.assertThatCode(() -> service.delete(1L))
                .doesNotThrowAnyException();
    }
}
