package br.com.rafaellbarros.backend.endpoint.validation;

import br.com.rafaellbarros.backend.config.properties.MessageProperty;
import br.com.rafaellbarros.core.exception.BusinessException;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository repository;

    public void existsFields(final UserDTO dto) {
        existsEmail(dto);
        existsLogin(dto);
    }

    private void existsEmail(final UserDTO dto) {
        boolean existsEmail = repository.existsByEmail(dto.getEmail());
        if (existsEmail) {
            throw new BusinessException(MessageProperty.EMAIL_ALREADY_EXISTS);
        }
    }

    private void existsLogin(final UserDTO dto) {
        boolean existsLogin = repository.existsByLogin(dto.getLogin());
        if (existsLogin) {
            throw new BusinessException(MessageProperty.LOGIN_ALREADY_EXISTS);
        }
    }
}
