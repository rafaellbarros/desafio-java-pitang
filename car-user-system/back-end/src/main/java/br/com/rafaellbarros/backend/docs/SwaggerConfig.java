package br.com.rafaellbarros.backend.docs;


import br.com.rafaellbarros.core.docs.BaseSwaggerConfig;
import br.com.rafaellbarros.core.properties.AppProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    public SwaggerConfig(AppProperties.Documentation properties) {
        super(properties);
    }
}