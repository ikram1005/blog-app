package com.blog.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> getDetails(UserException userException,WebRequest webRequest){
		MyErrorDetails err=new MyErrorDetails();
		err.setMessage(userException.getMessage());
		err.setLocalDateTime(LocalDateTime.now());
		err.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(MethodArgumentNotValidException nhf, WebRequest rq) {
		MyErrorDetails err = new MyErrorDetails();
		err.setLocalDateTime(LocalDateTime.now());
		nhf.getBindingResult().getAllErrors().forEach((e)->{
			String field=((FieldError)e).getField();
			err.setMessage(field+":"+e.getDefaultMessage());
		});
		err.setDetails(rq.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<MyErrorDetails> myHandler(MethodArgumentTypeMismatchException nhf, WebRequest rq) {
		MyErrorDetails err = new MyErrorDetails();
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(nhf.getMessage());
		err.setDetails(rq.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}
}
