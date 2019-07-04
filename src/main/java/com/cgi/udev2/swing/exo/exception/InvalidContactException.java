package com.cgi.udev2.swing.exo.exception;

public class InvalidContactException extends RuntimeException {

	private static final long serialVersionUID = -4792194206157232000L;
	
	public InvalidContactException(String message) {
		super(message);
	}
	
	public InvalidContactException(String message, Exception e) {
		super(message, e);
	}
	
}
