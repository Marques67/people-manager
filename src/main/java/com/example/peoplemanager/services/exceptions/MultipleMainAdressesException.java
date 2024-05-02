package com.example.peoplemanager.services.exceptions;

public class MultipleMainAdressesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MultipleMainAdressesException(String msg) {
		super(msg);
	}
}