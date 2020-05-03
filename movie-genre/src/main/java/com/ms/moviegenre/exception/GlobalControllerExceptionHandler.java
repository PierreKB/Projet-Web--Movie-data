package com.ms.moviegenre.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler(RequestException.class)
	void handleBadRequests(RequestException exception, HttpServletResponse response) throws IOException {
		
		int responseCode = (exception.errorCode == RequestException.ID_NOT_FOUND) ?
				HttpStatus.NOT_FOUND.value() : HttpStatus.BAD_REQUEST.value();
		
		response.sendError(responseCode, exception.errorCode + ":" + exception.getMessage());
	}
}