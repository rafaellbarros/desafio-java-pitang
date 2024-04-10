package br.com.rafaellbarros.core.exception.handler;

import br.com.rafaellbarros.core.exception.InfraException;

import br.com.rafaellbarros.core.exception.BusinessException;
import br.com.rafaellbarros.core.exception.ResourceNotFoundException;
import br.com.rafaellbarros.core.exception.SecurityValidationException;


import br.com.rafaellbarros.core.message.CoreMessageProperty;
import br.com.rafaellbarros.core.message.ErrorMessage;
import br.com.rafaellbarros.core.message.IMessageProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDenied(final AccessDeniedException ex, final WebRequest request) {
        final List<ErrorMessage> erros = new ArrayList<>();
        erros.add(new ErrorMessage(CoreMessageProperty.API_ACCESS_DENIED));
        LOG.debug(ex.getMessage(), ex);
        return this.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<Object> handleSecurity(final BusinessException ex, final WebRequest request) {
        final List<ErrorMessage> erros =  new ArrayList<>();
        LOG.debug(ex.getMessage(), ex);
        if (ex.getMessages().isEmpty()) {
            erros.add(new ErrorMessage(ex.getCause().getClass().getName(), ex.getMessage()));
        } else {
            ex.getMessages().iterator().forEachRemaining(msg -> erros.add(new ErrorMessage(msg)));
            if (erros.isEmpty()) {
                erros.add(new ErrorMessage(CoreMessageProperty.API_UNDENTIFIED_ERROR));
            }
        }
        return this.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFound(final ResourceNotFoundException ex,
                                                            final WebRequest request) {
        final IMessageProperty msg = ex.getMessageField().orElse(CoreMessageProperty.API_RESOURCE_NOTFOUND);
        final List<ErrorMessage> erros = new ArrayList<>();
        erros.add(new ErrorMessage(msg));
        LOG.debug(ex.getMessage(), ex);
        return this.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({SecurityValidationException.class})
    protected ResponseEntity<Object> handleSecurity(final SecurityValidationException ex, final WebRequest request) {
        final IMessageProperty msg = ex.getMessageField().orElse(CoreMessageProperty.API_ACCESS_DENIED);
        final List<ErrorMessage> erros = new ArrayList<>();
        erros.add(new ErrorMessage(msg));
        LOG.debug(ex.getMessage(), ex);
        return this.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({InfraException.class})
    protected ResponseEntity<Object> handleSecurity(final InfraException ex, final WebRequest request) {
        final IMessageProperty msg = ex.getMessageField().orElse(CoreMessageProperty.API_UNDENTIFIED_ERROR);
        final List<ErrorMessage> erros = new ArrayList<>();
        erros.add(new ErrorMessage(msg));
        LOG.debug(ex.getMessage(), ex);
        return this.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        final List<ErrorMessage> erros = new ArrayList<>();
        erros.add(new ErrorMessage(ex.getParameter().getParameterName(), ex.getBindingResult().toString()));
        LOG.debug(ex.getMessage(), ex);
        return this.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}

