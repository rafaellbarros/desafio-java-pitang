package br.com.rafaellbarros.backend.controller;

import br.com.rafaellbarros.backend.service.CarService;
import br.com.rafaellbarros.domain.model.dto.CarDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "Car")
@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService service;

    @GetMapping
    @ApiOperation("findAll all cars")
    public ResponseEntity<List<CarDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
