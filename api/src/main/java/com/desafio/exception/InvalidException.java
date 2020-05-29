package com.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidException(String mensagem) {
		super(mensagem);
	}

}
