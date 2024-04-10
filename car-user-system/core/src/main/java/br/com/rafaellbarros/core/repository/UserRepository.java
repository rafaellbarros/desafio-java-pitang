package br.com.rafaellbarros.core.repository;

import br.com.rafaellbarros.core.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
