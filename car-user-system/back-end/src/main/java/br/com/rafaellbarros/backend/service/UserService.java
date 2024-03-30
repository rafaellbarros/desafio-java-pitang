package br.com.rafaellbarros.backend.service;


import br.com.rafaellbarros.domain.repository.UserRepository;
import br.com.rafaellbarros.domain.model.dto.UserDTO;
import br.com.rafaellbarros.backend.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS)
public class UserService {

    private final UserRepository repository;

    private final UserMapper mapper;
    public List<UserDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public UserDTO create(final UserDTO dto) {
        dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }
}
