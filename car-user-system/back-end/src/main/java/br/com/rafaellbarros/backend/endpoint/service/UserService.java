package br.com.rafaellbarros.backend.endpoint.service;


import br.com.rafaellbarros.backend.endpoint.mapper.UserMapper;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
}
