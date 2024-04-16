package br.com.rafaellbarros.backend.endpoint.controller;

import br.com.rafaellbarros.backend.endpoint.service.TokenApiService;
import br.com.rafaellbarros.core.model.dto.TokenApiDTO;
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

@Api(tags = "ApiToken")
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class ApiTokenController {

    private final TokenApiService tokenApiService;

    @ApiOperation("Get Token Decrypted")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenApiDTO> getTokenDecrypted(@Valid @RequestBody final TokenApiDTO tokenApiDTO) {
        return ResponseEntity.ok(tokenApiService.getDecryptedToken(tokenApiDTO));
    }

}
