package br.com.rafaellbarros.backend.endpoint.controller;


import br.com.rafaellbarros.backend.endpoint.service.CarService;
import br.com.rafaellbarros.backend.endpoint.service.UserAuthenticatedService;
import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        final User user = userAuthenticatedService.geLogged();
        return ResponseEntity.ok(service.findAllByUserLogged(user));
    }
}
