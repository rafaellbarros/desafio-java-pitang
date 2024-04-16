package br.com.rafaellbarros.security.config;

import br.com.rafaellbarros.core.property.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    private static final String[] PERMIST_ALL_MATCHERS = {
            "/**/token/**",
            "/**/users/**",
            "/**/swagger-ui.html",
            "/**/swagger-resources/**",
            "/**/webjars/springfox-swagger-ui/**",
            "/**/v2/api-docs/**",
    };

    protected final JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers(jwtConfiguration.getLoginUrl()).permitAll()
                .antMatchers(PERMIST_ALL_MATCHERS).permitAll()
                .anyRequest().authenticated();
    }
}
