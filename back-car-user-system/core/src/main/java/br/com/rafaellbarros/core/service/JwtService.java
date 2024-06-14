package br.com.rafaellbarros.core.service;

import br.com.rafaellbarros.core.property.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    protected final JwtConfiguration jwtConfiguration;
    public Claims extractPayload(String jwtToken) {
        String token = jwtToken.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();
        Jws<Claims> jws = Jwts.parser().parseClaimsJws(token);
        return jws.getBody();
    }
}
