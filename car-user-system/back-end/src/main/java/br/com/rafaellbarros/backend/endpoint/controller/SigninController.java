package br.com.rafaellbarros.backend.endpoint.controller;

import br.com.rafaellbarros.backend.endpoint.service.SigninService;
import br.com.rafaellbarros.core.model.dto.SigninDTO;
import br.com.rafaellbarros.core.model.dto.TokenResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Signin")
@RestController
@RequestMapping("${api-pre-fix}/signin")
@RequiredArgsConstructor
public class SigninController {

    private final SigninService service;
    @ApiOperation("Get Token")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseDTO> getToken(@Valid @RequestBody final SigninDTO signinDTO) {
        return ResponseEntity.ok(service.signin(signinDTO));
    }
}
