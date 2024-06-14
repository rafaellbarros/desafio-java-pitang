package br.com.rafaellbarros.security.config;

import br.com.rafaellbarros.core.property.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITE_LIST = {
            "/**/**/signin/**",
            "/**/**/users/**",
            "/**/**/swagger-ui.html",
            "/**/**/swagger-resources/**",
            "/**/**/webjars/springfox-swagger-ui/**",
            "/**/**/v2/api-docs/**",

    };

    private static final String[] AUTH_BLACK_LIST = {
            "/**/**/**/cars/**",
            "/**/**/**/me/**",
    };

    protected final JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers(jwtConfiguration.getLoginUrl()).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(AUTH_WHITE_LIST).permitAll()
                .antMatchers(AUTH_BLACK_LIST).authenticated();
    }
}
