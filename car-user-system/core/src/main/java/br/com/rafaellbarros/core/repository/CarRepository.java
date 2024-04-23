package br.com.rafaellbarros.core.repository;

import br.com.rafaellbarros.core.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByUserId(final Long userId);
}
