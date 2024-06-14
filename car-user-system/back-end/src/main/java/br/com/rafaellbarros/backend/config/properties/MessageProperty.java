package br.com.rafaellbarros.backend.config.properties;

import br.com.rafaellbarros.core.message.IMessageProperty;
import br.com.rafaellbarros.core.message.MessageSource;

import java.util.Arrays;

public enum MessageProperty  implements IMessageProperty {

    USER_NOT_FOUND("user-not-found"),
    CARS_NOT_FOUND("cars-not-found"),
    CAR_NOT_FOUND("car-not-found"),
    EMAIL_ALREADY_EXISTS("email-already-exists"),
    LOGIN_ALREADY_EXISTS("login-already-exists"),
    UNAUTHORIZED_ACCESS("unauthorized-access"),
    HTTP_CLIENT_ERROR_EXCEPTION("http-client-error-exception"),
    UNEXPECTED_ERROR("unexpected-error");
    private String key;
    private String[] args = {};


    MessageProperty(final String keyValue) {
        this.key = keyValue;
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