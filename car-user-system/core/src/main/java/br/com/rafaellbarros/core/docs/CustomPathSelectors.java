package br.com.rafaellbarros.core.docs;


import com.google.common.base.Predicate;
import springfox.documentation.builders.PathSelectors;

public class CustomPathSelectors {
    public static Predicate<String> multiplePaths(String... paths) {
        return path -> {
            for (String p : paths) {
                if (PathSelectors.ant(p).apply(path)) {
                    return true;
                }
            }
            return false;
        };
    }
}