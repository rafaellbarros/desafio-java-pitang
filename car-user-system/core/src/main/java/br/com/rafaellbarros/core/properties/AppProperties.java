package br.com.rafaellbarros.core.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 29/03/2024
 */

@Component
public final class AppProperties {

    private AppProperties() {

    }

    @Component
    @ConfigurationProperties(prefix = "app-documentation")
    @PropertySource({"classpath:application.yml"})
    public static class Documentation {

        @Value("${api-title}")
        private String title;
        @Value("${description}")
        private String description;
        @Value("${version}")
        private String version;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getVersion() {
            return version;
        }

    }
}

