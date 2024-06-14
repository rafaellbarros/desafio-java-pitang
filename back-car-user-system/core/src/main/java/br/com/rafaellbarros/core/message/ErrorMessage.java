package br.com.rafaellbarros.core.message;

import lombok.ToString;

@ToString
public class ErrorMessage {
    private String error;
    private String message;
    private int errorCode;

    public ErrorMessage(final IMessageProperty messageProperty) {
        this.error = messageProperty.key();
        this.message = messageProperty.message();
    }

    public ErrorMessage(final IMessageProperty messageProperty, final int errorCode) {
        this.error = messageProperty.key();
        this.message = messageProperty.message();
        this.errorCode = errorCode;
    }

    public ErrorMessage(final String error, final String message) {
        this.error = error;
        this.message = message;
    }

    public ErrorMessage(final String error, final String message, final int errorCode) {
        this.error = error;
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getError() {
        return this.error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

