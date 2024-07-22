package com.ShopifyLite.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exceptionHandler(Exception e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundException(NoHandlerFoundException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<MyErrorDetails> dataIntegrityException(DataIntegrityViolationException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArguementNotValidException(MethodArgumentNotValidException e){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), "Validation Error", e.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userException(UserException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> productException(ProductException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SizeException.class)
	public ResponseEntity<MyErrorDetails> sizeException(SizeException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> orderException(OrderException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> loginException(LoginException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> cartException(CartException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AmountException.class)
	public ResponseEntity<MyErrorDetails> amountException(AmountException e,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), e.getMessage(), r.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<MyErrorDetails> handleAccessDeniedException(AccessDeniedException ex, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MyErrorDetails> myIllegalArgumentException(IllegalArgumentException ee,WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ee.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	
}
