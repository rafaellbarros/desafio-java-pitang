package br.com.rafaellbarros.backend.endpoint.controller;

import br.com.rafaellbarros.backend.endpoint.service.UserAuthenticatedService;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User Information")
@RestController
@RequestMapping("${apiPrefix}/me")
@RequiredArgsConstructor
public class UserInformationController {

    private final UserAuthenticatedService userAuthenticatedService;

    @ApiOperation("Find user logged")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> findUserLogged() {
        return ResponseEntity.ok(userAuthenticatedService.geLogged());
    }

}
