package br.com.rafaellbarros.backend.endpoint.controller;


import br.com.rafaellbarros.backend.endpoint.service.CarService;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Api(tags = "Car")
@RestController
@RequestMapping("${api-pre-fix}/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService service;

    @ApiOperation("Find all cars by id user logged")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CarDTO>> findAllByUserLogged() {
        return ResponseEntity.ok(service.findAllByUserLogged());
    }

    @ApiOperation("Create car by id user logged")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDTO> createByUserLogged(@Valid @RequestBody final CarDTO carDTO) {
        return new ResponseEntity<>(service.createByUserLogged(carDTO), HttpStatus.CREATED);
    }

    @ApiOperation("Find car by id user logged")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDTO> findByIdUserLogged(final @PathVariable Long id) {
        return ResponseEntity.ok(service.findByIdUserLogged(id));
    }

    @ApiOperation("Delete car by id user logged")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteByIdUserLogged(@PathVariable final Long id) {
        service.deleteByIdUserLogged(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Update car by id user logged")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDTO> updateByUserLogged(@PathVariable final Long id, @Valid @RequestBody final CarDTO carDTO) {
        return ResponseEntity.ok(service.updateByUserLogged(id, carDTO));
    }
}
