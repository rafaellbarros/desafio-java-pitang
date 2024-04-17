package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.backend.endpoint.mapper.UserMapper;
import br.com.rafaellbarros.backend.utils.UserCreator;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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

    @BeforeEach
    void setUp() {
        BDDMockito.when(mapperMock.toDTO(repositoryMock.findAll()))
                .thenReturn(singletonList(userDTOMock));
    }

    @Test
    void testFindAll() {

        List<UserDTO> usersDTO = service.findAll();

        Assertions.assertThat(usersDTO)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }
}
