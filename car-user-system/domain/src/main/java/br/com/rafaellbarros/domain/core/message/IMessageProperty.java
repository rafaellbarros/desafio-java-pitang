package br.com.rafaellbarros.domain.core.message;

import br.com.rafaellbarros.domain.core.exception.BusinessException;
import br.com.rafaellbarros.domain.core.exception.InfraException;
import br.com.rafaellbarros.domain.core.exception.ResourceNotFoundException;
import br.com.rafaellbarros.domain.core.exception.SecurityValidationException;

import java.io.Serializable;

public interface IMessageProperty extends Serializable {
    String key();

    String message();

    IMessageProperty bind(final String... args);

    default BusinessException businessException() {
        return new BusinessException(this);
    }

    default BusinessException businessException(final Throwable cause) {
        return new BusinessException(this, cause);
    }

    default InfraException infraException() {
        return new InfraException(this);
    }

    default InfraException infraException(final Throwable cause) {
        return new InfraException(this, cause);
    }

    default ResourceNotFoundException resourceNotFoundException() {
        return new ResourceNotFoundException(this);
    }

    default ResourceNotFoundException resourceNotFoundException(final Throwable cause) {
        return new ResourceNotFoundException(this, cause);
    }

    default SecurityValidationException securityValidationException() {
        return new SecurityValidationException(this);
    }

    default SecurityValidationException securityValidationException(final Throwable cause) {
        return new SecurityValidationException(this, cause);
    }
}
