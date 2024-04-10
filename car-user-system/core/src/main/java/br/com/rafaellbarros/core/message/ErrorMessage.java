package br.com.rafaellbarros.core.message;

public class ErrorMessage {
    private String error;
    private String message;

    public ErrorMessage(final IMessageProperty messageProperty) {
        this.error = messageProperty.key();
        this.message = messageProperty.message();
    }

    public ErrorMessage(final String error, final String message) {
        this.error = error;
        this.message = message;
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

}

