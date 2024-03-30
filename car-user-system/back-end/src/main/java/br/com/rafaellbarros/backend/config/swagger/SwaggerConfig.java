package br.com.rafaellbarros.backend.config.swagger;


import br.com.rafaellbarros.domain.core.config.BaseSwaggerConfig;
import br.com.rafaellbarros.domain.core.config.properties.AppProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    public SwaggerConfig(AppProperties.Documentation properties) {
        super(properties);
    }
}