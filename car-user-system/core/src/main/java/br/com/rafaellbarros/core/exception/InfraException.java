package br.com.rafaellbarros.core.exception;



import br.com.rafaellbarros.core.message.IMessageProperty;

import java.util.Optional;

public class InfraException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private IMessageProperty message;

    public InfraException(final Throwable cause) {
        super(cause);
    }

    public InfraException(final IMessageProperty message) {
        super(message.message());
        this.message = message;
    }

    public InfraException(final IMessageProperty message, final Throwable cause) {
        super(message.message(), cause);
        this.message = message;
    }

    public Optional<IMessageProperty> getMessageField() {
        return Optional.ofNullable(this.message);
    }
}
