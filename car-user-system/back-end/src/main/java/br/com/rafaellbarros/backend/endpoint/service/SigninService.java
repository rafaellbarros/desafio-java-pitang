package br.com.rafaellbarros.backend.endpoint.service;


import br.com.rafaellbarros.core.model.dto.SigninDTO;
import br.com.rafaellbarros.core.model.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SigninService {

    @Value("${url-auth-login}")
    private String urlAuthLogin;

    private final RestTemplate restTemplate;

    public TokenResponseDTO signin(final SigninDTO signinDTO) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<SigninDTO> request = new HttpEntity<>(signinDTO, headers);

        final ResponseEntity<String> response = restTemplate.postForEntity(urlAuthLogin, request, String.class);

        final String token = Optional.ofNullable(response.getHeaders().getFirst("Authorization"))
                .orElseGet(() -> "StatusCode: " + response.getStatusCode() + " Token not found in response.");

        log.info("GetToken() {}", token);
        return new TokenResponseDTO(token);
    }
}
