package br.com.sarp.model.service;

import br.com.sarp.model.dao.DAOException;

@SuppressWarnings({ "serial", "unused" })
public class ServiceException extends Exception {

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(String mensagem, Exception causa) {
		super(mensagem, causa);
	}


}
