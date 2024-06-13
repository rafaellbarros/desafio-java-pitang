package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.core.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserAuthenticatedService {

    public User geLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User user = (User)  authentication.getPrincipal();
        log.info("geLogged() => {}", user);
        return user;
    }
}
