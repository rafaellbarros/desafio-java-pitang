package br.com.rafaellbarros.domain.core.message;

import java.util.Arrays;

public enum CoreMessageProperty implements IMessageProperty {
    API_ACCESS_DENIED("api.access-denied"),
    API_UNDENTIFIED_ERROR("api.unidentified-error"),
    API_RESOURCE_NOTFOUND("api.resource-notfound"),
    API_SORT_NOT_MAPPING("api.sort-sort-not-mapping");

    private String key;
    private String[] args = {};

    CoreMessageProperty(final String paramKey) {
        this.key = paramKey;
    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public String message() {
        return MessageSource.get().message(key, args);
    }

    @Override
    public IMessageProperty bind(final String... paramArgs) {
        if (paramArgs == null)  {
            this.args = new String[0];
        } else {
            this.args = Arrays.copyOf(paramArgs, paramArgs.length);
        }
        return this;
    }
}

