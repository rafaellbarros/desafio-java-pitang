package br.com.rafaellbarros.domain.core.exception;

import br.com.rafaellbarros.domain.core.message.IMessageProperty;

import java.util.Optional;

public class SecurityValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private IMessageProperty message;

    public SecurityValidationException() {
    }

    /** @deprecated */
    @Deprecated
    public SecurityValidationException(final String message) {
    }

    /** @deprecated */
    @Deprecated
    public SecurityValidationException(final String message, final Throwable cause) {
    }

    /** @deprecated */
    @Deprecated
    public SecurityValidationException(final Throwable cause) {
    }

    /** @deprecated */
    @Deprecated
    public SecurityValidationException(final String message, final Throwable cause, final boolean enableSuppression,
                                       final boolean writableStackTrace) {
    }

    public SecurityValidationException(final IMessageProperty message) {
        super(message.message());
        this.message = message;
    }

    public SecurityValidationException(final IMessageProperty message, final Throwable cause) {
        super(message.message(), cause);
        this.message = message;
    }

    public Optional<IMessageProperty> getMessageField() {
        return Optional.ofNullable(this.message);
    }
}

