package br.com.rafaellbarros.core.exception;



import br.com.rafaellbarros.core.message.IMessageProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final List<IMessageProperty> messages = new ArrayList<>();

    public BusinessException() {
    }

    /** @deprecated */
    @Deprecated
    public BusinessException(final String message) {
        super(message);
    }

    /** @deprecated */
    @Deprecated
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /** @deprecated */
    @Deprecated
    public BusinessException(final Throwable cause) {
        super(cause);
    }

    /** @deprecated */
    @Deprecated
    public BusinessException(final String message, final Throwable cause, final boolean enableSuppression,
                             final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessException(final IMessageProperty message) {
        super(message.message());
        this.messages.add(message);
    }

    public BusinessException(final IMessageProperty... messages) {
        super(Arrays.toString(messages));
        this.messages.addAll(Arrays.asList(messages));
    }

    public BusinessException(final IMessageProperty message, final Throwable cause) {
        super(message.message(), cause);
        this.messages.add(message);
    }

    public List<IMessageProperty> getMessages() {
        return this.messages;
    }
}
