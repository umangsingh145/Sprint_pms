package com.pms.in.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(PensionerDetailsNotFoundException.class)
	public ResponseEntity<Object> handlePensionerDetailsNotFoundException() {
		LOG.error("handleEmployeeNotFoundException");
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "This employee is NOT available in the database.");
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PensionerAlreadyExistsException.class)
	public ResponseEntity<Object> handleSomeOtherException() {
		LOG.error("handelSome Other Exception");
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "This Employee Already Exits");
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BankNotAddedException.class)
	public ResponseEntity<Object> handleBankNotAddedException() {
		LOG.error("handleBankAlreadyAddedException");
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "This Bank is NOT available in the database.");
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<Object> handleAccountNotFoundException() {
		LOG.error("handleBankAlreadyAddedException");
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "This Bank is NOT available in the database.");
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}
//	
//	@ExceptionHandler(DepartmentNotFoundException.class)
//	public ResponseEntity<Object> handleDepartmentNotFoundException() {
//		LOG.error("handleEmployeeNotFoundException");
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("message", "Entered Did is not available in the Database.");
//		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
//	}
}
