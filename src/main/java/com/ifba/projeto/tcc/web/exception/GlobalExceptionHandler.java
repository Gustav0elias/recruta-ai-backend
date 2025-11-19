package com.ifba.projeto.tcc.web.exception;

import com.ifba.projeto.tcc.application.dto.response.ErrorResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.FieldErrorDTO;
import com.ifba.projeto.tcc.domain.exception.BusinessException;
import com.ifba.projeto.tcc.domain.exception.ForbiddenException;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import com.ifba.projeto.tcc.domain.exception.UnauthorizedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        log.warn("Recurso não encontrado: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        log.warn("Entidade não encontrada: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Entidade não encontrada",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(
            BusinessException ex, WebRequest request) {
        log.warn("Erro de negócio: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de negócio",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorizedException(
            UnauthorizedException ex, WebRequest request) {
        log.warn("Não autorizado: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.UNAUTHORIZED.value(),
                "Não autorizado",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponseDTO> handleForbiddenException(
            ForbiddenException ex, WebRequest request) {
        log.warn("Acesso negado: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.FORBIDDEN.value(),
                "Acesso negado",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        log.warn("Erro de autenticação: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.UNAUTHORIZED.value(),
                "Erro de autenticação",
                "Credenciais inválidas ou token expirado",
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentialsException(
            BadCredentialsException ex, WebRequest request) {
        log.warn("Credenciais inválidas: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.UNAUTHORIZED.value(),
                "Credenciais inválidas",
                "Email ou senha incorretos",
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        log.warn("Acesso negado: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.FORBIDDEN.value(),
                "Acesso negado",
                "Você não tem permissão para acessar este recurso",
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {
        log.warn("Erro de validação: {}", ex.getMessage());
        
        List<FieldErrorDTO> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorDTO(
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue()
                ))
                .collect(Collectors.toList());

        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Dados de entrada inválidos",
                request.getDescription(false).replace("uri=", ""),
                fieldErrors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        log.warn("Erro de validação de constraint: {}", ex.getMessage());
        
        List<FieldErrorDTO> fieldErrors = ex.getConstraintViolations()
                .stream()
                .map(violation -> new FieldErrorDTO(
                        getFieldName(violation),
                        violation.getMessage(),
                        violation.getInvalidValue()
                ))
                .collect(Collectors.toList());

        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Dados de entrada inválidos",
                request.getDescription(false).replace("uri=", ""),
                fieldErrors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.warn("Erro de tipo de argumento: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de tipo",
                String.format("O parâmetro '%s' deve ser do tipo %s", 
                        ex.getName(), 
                        ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido"),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.warn("Argumento ilegal: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Argumento inválido",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {
        log.warn("Estado ilegal: {}", ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Estado inválido",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(
            RuntimeException ex, WebRequest request) {
        log.error("Erro inesperado: {}", ex.getMessage(), ex);
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(
            Exception ex, WebRequest request) {
        log.error("Erro genérico não tratado: {}", ex.getMessage(), ex);
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private String getFieldName(ConstraintViolation<?> violation) {
        String propertyPath = violation.getPropertyPath().toString();
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
    }
}

