package br.com.rafaellbarros.auth.security.config;


import br.com.rafaellbarros.core.property.JwtConfiguration;
import br.com.rafaellbarros.security.token.converter.TokenConverter;
import br.com.rafaellbarros.security.token.creator.TokenCreator;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SecurityCredentialsConfigBuilder {
    private JwtConfiguration jwtConfiguration;
    private UserDetailsService userDetailsService;
    private TokenCreator tokenCreator;
    private TokenConverter tokenConverter;

    public SecurityCredentialsConfigBuilder setJwtConfiguration(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
        return this;
    }

    public SecurityCredentialsConfigBuilder setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        return this;
    }

    public SecurityCredentialsConfigBuilder setTokenCreator(TokenCreator tokenCreator) {
        this.tokenCreator = tokenCreator;
        return this;
    }

    public SecurityCredentialsConfigBuilder setTokenConverter(TokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
        return this;
    }

    public SecurityCredentialsConfig createSecurityCredentialsConfig() {
        return new SecurityCredentialsConfig(jwtConfiguration, userDetailsService, tokenCreator, tokenConverter);
    }
}