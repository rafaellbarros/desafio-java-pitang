package br.com.rafaellbarros.backend.endpoint.service;


import br.com.rafaellbarros.backend.endpoint.mapper.UserMapper;
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

    public List<UserDTO> findAll() {
        log.info("Listing all users");
        return mapper.toDTO(repository.findAll());
    }

    public UserDTO create(final UserDTO dto) {
        log.info("Create user: {}", dto);
        dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public UserDTO findById(final Long id) {
        final User user = fiindEntityById(id);
        return mapper.toDTO(user);
    }

    private User fiindEntityById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException("User not found."));
    }
}
