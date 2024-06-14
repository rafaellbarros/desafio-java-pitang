package br.com.rafaellbarros.backend.endpoint.service;


import br.com.rafaellbarros.backend.config.properties.MessageProperty;
import br.com.rafaellbarros.core.exception.BusinessException;
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
import org.springframework.web.client.HttpClientErrorException;
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

        try {
            final ResponseEntity<String> response = restTemplate.postForEntity(urlAuthLogin, request, String.class);
            final String token = Optional.ofNullable(response.getHeaders().getFirst("Authorization"))
                    .orElseGet(() -> "StatusCode: " + response.getStatusCode() + " Token not found in response.");
            log.info("GetToken() {}", token);
            return new TokenResponseDTO(token);
        } catch (HttpClientErrorException.Unauthorized e) {
            log.error("Unauthorized access: {}", e.getMessage());
            throw new BusinessException(MessageProperty.UNAUTHORIZED_ACCESS);
        } catch (HttpClientErrorException e) {
            log.error("HttpClientErrorException: {}", e.getMessage());
            throw new BusinessException(MessageProperty.HTTP_CLIENT_ERROR_EXCEPTION);
        } catch (Exception e) {
            log.error("An unexpected error occurred: {}", e.getMessage());
            throw new BusinessException(MessageProperty.UNEXPECTED_ERROR);
        }
    }
}

