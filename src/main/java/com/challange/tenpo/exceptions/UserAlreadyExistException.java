package com.challange.tenpo.exceptions;

public class UserAlreadyExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String message) {
        super(message);
    }
}
