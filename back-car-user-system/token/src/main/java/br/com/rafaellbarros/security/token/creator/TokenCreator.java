package br.com.rafaellbarros.security.token.creator;


import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.property.JwtConfiguration;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenCreator {

    private final JwtConfiguration jwtConfiguration;

    @SneakyThrows
    public SignedJWT createSignedJWT(Authentication auth) {
        log.info("Starting to create the signed JWT");

        final User user = (User) auth.getPrincipal();

        final JWTClaimsSet jwtClaimSet = createJWTClaimSet(auth, user);

        final KeyPair rsaKey = generateKeyPair();

        log.info("Building JWK from the RSA Keys");

        final JWK jwk = new RSAKey.Builder((RSAPublicKey) rsaKey.getPublic()).keyID(UUID.randomUUID().toString()).build();

        final SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
                .jwk(jwk)
                .type(JOSEObjectType.JWT)
                .build(), jwtClaimSet);

        log.info("Signing the token with the private RSA Key");

        final RSASSASigner signer = new RSASSASigner(rsaKey.getPrivate());

        signedJWT.sign(signer);

        log.info("Serealized token '{}'", signedJWT.serialize());

        return signedJWT;
    }

    private JWTClaimsSet createJWTClaimSet(Authentication auth, User user) {
        log.info("Creating the JwtClaimSet Object for '{}'", user);
        return new JWTClaimsSet.Builder()
                .subject(user.getLogin())
                .claim("userId", user.getId())
                .claim("login", user.getLogin())
                .issuer("https://github.com/rafaellbarros")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000)))
                .build();

    }

    @SneakyThrows
    private KeyPair generateKeyPair() {
        log.info("Generating RSA 2048 bits Keys");

        final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);

        return generator.genKeyPair();
    }

    public String encryptToken(SignedJWT signedJWT) throws JOSEException {
        log.info("Starting the encryptToken method");

        final DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());

        final JWEObject jweObject = new JWEObject(
                new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                        .contentType("JWT")
                        .build(), new Payload(signedJWT)
        );

        log.info("Encrypting token with systems's private key");

        jweObject.encrypt(directEncrypter);

        log.info("Token encrypted");

        return jweObject.serialize();

    }
}
