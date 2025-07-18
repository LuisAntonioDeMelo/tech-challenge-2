package com.tech.challenge.fastfood.main;

import com.tech.challenge.fastfood.application.usecases.cliente.exceptions.ClienteExistenteException;
import com.tech.challenge.fastfood.application.usecases.cliente.exceptions.ClienteNotFoundException;
import com.tech.challenge.fastfood.application.usecases.cliente.exceptions.InvalidCpfException;
import com.tech.challenge.fastfood.application.usecases.pedidos.exceptions.PedidoNaoEncontratoException;
import com.tech.challenge.fastfood.application.usecases.produto.exceptions.ProdutoInexistenteException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@ControllerAdvice
@SuppressWarnings("ClassEscapesDefinedScope")
public class ExceptionHandlerAdvice {

    @Data
    @AllArgsConstructor
    private static class ErrorResponse{
        private int status;
        private String message;
        private LocalDateTime timestamp;
        private String details;
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFoundException(
            ClienteNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteExistenteException.class)
    public ResponseEntity<ErrorResponse> handleClienteExisteException(
            ClienteExistenteException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoInexistenteException.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFoundException(
            ProdutoInexistenteException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidCpfException.class})
    public ResponseEntity<ErrorResponse> handleInvalidCpfException(
            InvalidCpfException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PedidoNaoEncontratoException.class})
    public ResponseEntity<ErrorResponse> handleInvalidPedidofException(
            InvalidCpfException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}