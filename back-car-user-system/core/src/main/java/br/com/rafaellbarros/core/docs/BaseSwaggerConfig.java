package br.com.rafaellbarros.core.docs;

import br.com.rafaellbarros.core.properties.AppProperties;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

public class BaseSwaggerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(BaseSwaggerConfig.class);
    private static final String API_KEY_NAME = "JWT";
    private static final String SECURITY_CONTEXT_PATH_CARS = "/**/cars/**";
    private static final String SECURITY_CONTEXT_PATH_USER_IFORMATION = "/**/me/**";
    private final AppProperties.Documentation properties;

    public BaseSwaggerConfig(final AppProperties.Documentation properties) {
        this.properties = properties;
    }

    private ApiInfo apiInfo() {
        final StringBuilder version = new StringBuilder();
        version.append(this.properties.getVersion());
        final String env = System.getenv("ENV");
        if (StringUtils.isNotBlank(env)) {
            version.append("-").append(env);
        }

        final String buildNumber = System.getenv("BUILD_NUMBER");
        if (StringUtils.isNotBlank(buildNumber)) {
            version.append(".").append(buildNumber);
        }

        LOG.info(String.format("Info do Swagger: %s@%s", this.properties.getTitle(), version));
        return (new ApiInfoBuilder())
                .title(this.properties.getTitle())
                .description(this.properties.getDescription())
                .version(version.toString())
                .contact(new Contact("Rafael Barros Silva", "https://github.com/rafaellbarros", "rafaelbarros.softwareengineer@gmail.com"))
                .license(" MIT License (MIT)  stuff bro, belongs to DevDusCorre")
                .licenseUrl("https://github.com/rafaellbarros")
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }


    private ApiKey apiKey() {
        return new ApiKey(API_KEY_NAME, HttpHeaders.AUTHORIZATION, In.HEADER.name());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(CustomPathSelectors.multiplePaths(SECURITY_CONTEXT_PATH_CARS, SECURITY_CONTEXT_PATH_USER_IFORMATION))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference(API_KEY_NAME, authorizationScopes));
    }
}

