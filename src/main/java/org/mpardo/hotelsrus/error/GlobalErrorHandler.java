package org.mpardo.hotelsrus.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
		return new ResponseEntity<>(new Error("Error interno del servidor:", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
