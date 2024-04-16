package br.com.rafaellbarros.backend.endpoint.service;

import br.com.rafaellbarros.core.model.dto.TokenApiDTO;
import br.com.rafaellbarros.core.property.JwtConfiguration;
import br.com.rafaellbarros.security.token.converter.TokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenApiService {

    private final TokenConverter tokenConverter;
    private final JwtConfiguration jwtConfiguration;
    public TokenApiDTO getDecryptedToken(final TokenApiDTO tokenApiDTO) {
        final TokenApiDTO tokenDTO = validationBearer(tokenApiDTO);
        final String decryptedToken = getDecyptedToken(tokenDTO);
        return new TokenApiDTO(decryptedToken);
    }

    private TokenApiDTO validationBearer(final TokenApiDTO tokenApiDTO) {
        if (tokenApiDTO.getEncryptedToken().contains(jwtConfiguration.getHeader().getPrefix())) {
            String tokenWithoutBearer = tokenApiDTO.getEncryptedToken().replace(jwtConfiguration.getHeader().getPrefix(), "").trim();
            tokenApiDTO.setEncryptedToken(tokenWithoutBearer);
            return tokenApiDTO;
        }
        return tokenApiDTO;
    }
    private String getDecyptedToken(final TokenApiDTO tokenDTO) {
        return  jwtConfiguration.getHeader().getPrefix() + tokenConverter.decryptToken(tokenDTO.getEncryptedToken());
    }
}
