package io.wiklandia.flex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class})
  protected ResponseEntity<Object> handleIllegalArgument(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, Message.of(ex.getMessage()),
      new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor(staticName = "of")
  private static class Message {
    private String ex;
  }
}
