package com.ecommerce.exception.handling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.dto.Message;
import com.ecommerce.exceptions.UserNotFoundException;



// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionHandlerAdvice.
 *
 * @author shivam.rai
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {


	/**
	 * Handle validation exceptions.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
	Map<String, String> errors = new HashMap<>();
	ex.getBindingResult().getAllErrors().forEach((error) -> {
	String fieldName = ((FieldError) error).getField();
	String errorMessage = error.getDefaultMessage(); 
	errors.put(fieldName, errorMessage);
	});
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(ex.getLocalizedMessage()));
	}
	
	/**
	 * Handle exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleException(UserNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not available ");
    }
}
