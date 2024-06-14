package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.backend.endpoint.mapper.UserMapper;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserAuthenticatedService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDTO geLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User user = (User)  authentication.getPrincipal();
        final User userLogged = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + user.getId()));
        final UserDTO userDTO = userMapper.toDTO(userLogged);
        return userDTO;
    }
}
