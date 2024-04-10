package br.com.rafaellbarros.backend.endpoint.controller;


import br.com.rafaellbarros.backend.endpoint.service.CarService;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "Car")
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

    private final CarService service;

    @ApiOperation("findAll all cars")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CarDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
