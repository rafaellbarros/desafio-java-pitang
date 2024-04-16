package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.core.model.dto.TokenApiDTO;
import br.com.rafaellbarros.security.token.converter.TokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenApiService {

    private final TokenConverter tokenConverter;
    public TokenApiDTO getDecryptedToken(final TokenApiDTO token) {
        final TokenApiDTO tokenApiDTO = validationBearer(token);
        return new TokenApiDTO(tokenConverter.decryptToken(tokenApiDTO
                .getEncryptedToken()));
    }

    private TokenApiDTO validationBearer(final TokenApiDTO token) {
        if (token.getEncryptedToken().contains("Bearer")) {
            String tokenWithoutBearer = token.getEncryptedToken().replace("Bearer ", "").trim();
            token.setEncryptedToken(tokenWithoutBearer);
            return token;
        }
        return token;
    }
}
