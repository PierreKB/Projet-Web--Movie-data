package com.ms.moviegenre.exception;

public class RequestException extends RuntimeException {
	
	public static final int ID_NOT_FOUND = 1;
	private static final long serialVersionUID = 1L;
	public int errorCode;
	
	
	public RequestException(int errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}
	
}