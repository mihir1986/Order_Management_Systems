package com.corp.orderservice.exception;

import java.util.Date;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class OrderServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<Object> handleOrderNotFound(OrderNotFoundException ex) {
		OrderMangementExceptionSchema exceptionReposne = new OrderMangementExceptionSchema(new Date(), ex.getMessage(),
				ex.toString());
		return new ResponseEntity<>(exceptionReposne, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<Object> handleFeignStatusException(FeignException e) {
		OrderMangementExceptionSchema exceptionReposne = new OrderMangementExceptionSchema(new Date(), e.getMessage(),
				e.contentUTF8());
		return new ResponseEntity<>(exceptionReposne, HttpStatus.NOT_FOUND);
	}

}