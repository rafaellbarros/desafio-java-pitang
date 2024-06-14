package br.com.rafaellbarros.auth.security.user;


import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) {
        log.info("Searching in the DB the user by username '{}'", login);

        User user = repository.findByLogin(login);

        log.info("User found '{}'", user);

        if (user == null)
            throw new UsernameNotFoundException(String.format("User '%s' not found", login));
        return new CustomUserDetails(user);
    }

    private static final class CustomUserDetails extends User implements UserDetails {
        private static final long serialVersionUID = -8003571362645655686L;

        private final User user;

        CustomUserDetails(@NotNull User user) {
            super(user);
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return new ArrayList<>();
        }

        @Override
        public String getUsername() {
            return this.user.getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}