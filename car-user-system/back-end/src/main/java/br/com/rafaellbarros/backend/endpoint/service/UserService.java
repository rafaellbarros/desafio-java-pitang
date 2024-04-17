package br.com.rafaellbarros.backend.endpoint.service;


import br.com.rafaellbarros.backend.config.properties.MessageProperty;
import br.com.rafaellbarros.backend.endpoint.mapper.UserMapper;
import br.com.rafaellbarros.backend.endpoint.validation.UserValidation;
import br.com.rafaellbarros.core.exception.BusinessException;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS)
public class UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    private final UserValidation validation;

    public List<UserDTO> findAll() {
        log.info("Listing all users");
        return mapper.toDTO(repository.findAll());
    }

    public UserDTO create(final UserDTO userDTO) {
        log.info("Create user: {}", userDTO);
        validation.existsFields(userDTO);
        userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        return mapper.toDTO(repository.save(mapper.toEntity(userDTO)));
    }

    public UserDTO findById(final Long id) {
        final User user = fiindEntityById(id);
        return mapper.toDTO(user);
    }

    public void delete(Long id) {
        final User user = fiindEntityById(id);
        repository.delete(user);
    }

    public UserDTO update(final UserDTO userDTO) {
        final User user = fiindEntityById(userDTO.getId());
        mapper.fromDTO(userDTO, user);
        return  mapper.toDTO(repository.save(user));
    }

    private User fiindEntityById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(MessageProperty.USER_NOT_FOUND));
    }
}
