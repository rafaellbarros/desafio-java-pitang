package br.com.rafaellbarros.domain.core.exception;

import br.com.rafaellbarros.domain.core.message.IMessageProperty;

import java.util.Optional;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("checkstyle:MutableException")
    private IMessageProperty message;

    public ResourceNotFoundException() {
    }

    /** @deprecated */
    @Deprecated
    public ResourceNotFoundException(final String message) {
    }

    /** @deprecated */
    @Deprecated
    public ResourceNotFoundException(final String message, final Throwable cause) {
    }

    /** @deprecated */
    @Deprecated
    public ResourceNotFoundException(final Throwable cause) {
    }

    /** @deprecated */
    @Deprecated
    public ResourceNotFoundException(final String message, final Throwable cause, final boolean enableSuppression,
                                     final boolean writableStackTrace) {
    }

    public ResourceNotFoundException(final IMessageProperty message) {
        super(message.message());
        this.message = message;
    }

    public ResourceNotFoundException(final IMessageProperty message, final Throwable cause) {
        super(message.message(), cause);
        this.message = message;
    }

    public Optional<IMessageProperty> getMessageField() {
        return Optional.ofNullable(this.message);
    }
}

