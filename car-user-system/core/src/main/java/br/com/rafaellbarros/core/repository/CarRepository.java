package br.com.rafaellbarros.core.repository;

import br.com.rafaellbarros.core.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {

}
