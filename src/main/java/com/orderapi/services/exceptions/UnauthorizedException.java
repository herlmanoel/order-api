package com.orderapi.services.exceptions;

//Usada para retornar o erro 401  :  ''Token inválido''
public class UnauthorizedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String msg) {
		super(msg);
	}

}
