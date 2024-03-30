package br.com.rafaellbarros.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rafaellbarros.domain.model.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
