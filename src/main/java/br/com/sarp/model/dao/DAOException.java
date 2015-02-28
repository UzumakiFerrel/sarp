package br.com.sarp.model.dao;


@SuppressWarnings("serial")
public class DAOException extends Exception {

	public DAOException(String msg, Exception erro) {
		super(msg,erro);
	}

}
