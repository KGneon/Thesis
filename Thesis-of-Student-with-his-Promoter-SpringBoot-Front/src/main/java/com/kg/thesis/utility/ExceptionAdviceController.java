package com.kg.thesis.utility;

import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kg.thesis.exception.ThesisException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionAdviceController {
	
	private static final Log LOGGER = LogFactory.getLog(ExceptionAdviceController.class);
	
	@Autowired
	private Environment environment;
	
	@ExceptionHandler(ThesisException.class)
	public ResponseEntity<ErrorInfo> thesisExceptionHandler(ThesisException exception) {
		LOGGER.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorId(ErrorInfo.count.incrementAndGet());
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(ThesisException exception) {
		LOGGER.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorId(ErrorInfo.count.incrementAndGet());
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
		String errorMsg;
		if(exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException manvException = (MethodArgumentNotValidException) exception;
			errorMsg = manvException.getBindingResult()
					.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(", "));
		} else {
			ConstraintViolationException cvException = (ConstraintViolationException) exception;
			errorMsg = cvException.getConstraintViolations()
					.stream()
					.map(ConstraintViolation::getMessage)
					.collect(Collectors.joining(", "));
		}
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorId(ErrorInfo.count.incrementAndGet());
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setMessage(errorMsg);
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
}
