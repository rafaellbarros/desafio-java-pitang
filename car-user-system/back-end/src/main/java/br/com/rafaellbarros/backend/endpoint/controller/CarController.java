package br.com.rafaellbarros.backend.endpoint.controller;


import br.com.rafaellbarros.backend.endpoint.service.CarService;
import br.com.rafaellbarros.backend.endpoint.service.UserAuthenticatedService;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Api(tags = "Car")
@RestController
@RequestMapping("${apiPrefix}/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService service;
    private final UserAuthenticatedService userAuthenticatedService;

    @ApiOperation("Find all cars by id user logged")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CarDTO>> findAllByUserLogged() {
        final UserDTO userLogged = userAuthenticatedService.geLogged();
        return ResponseEntity.ok(service.findAllByUserLogged(userLogged.getId()));
    }

    @ApiOperation("Create car by id user logged")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDTO> createByUserLogged(@Valid @RequestBody final CarDTO carDTO) {
        final UserDTO userLogged = userAuthenticatedService.geLogged();
        carDTO.setUser(userLogged);
        return new ResponseEntity<>(service.createByUserLogged(carDTO), HttpStatus.CREATED);
    }

    @ApiOperation("Find car by id user logged")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDTO> findByIdUserLogged(final @PathVariable Long id) {
        final UserDTO userLogged = userAuthenticatedService.geLogged();
        return ResponseEntity.ok(service.findByIdUserLogged(id, userLogged.getId()));
    }
}
