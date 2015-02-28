package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.PassagemComandoDAO;
import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.PassagemComando;
import br.com.sarp.model.entidades.Rebocador;

@Service
public class PassagemComandoService {

	private PassagemComando passagem = new PassagemComando();

	@Inject
	private PassagemComandoDAO passagemComandoDao;

	public void salvar(PassagemComando passagemComando) throws ServiceException {

		/*
		 * validacao das regras de negocio a base,setor,funcao,situacao devem
		 * existir no cadastro nenhum campo pode ser vazio *
		 */
		// validacao da regra exemplo
		
		setPassagem(null);

		passagem = passagemComandoDao
				.buscarPassagemComandoRebocador(passagemComando.getRebocador());

		// Verifica existencia de passagem pendente
		if (getPassagem()!= null) {
			throw new ServiceException(
					"Existe Passagem de Comando Pendente ! Numero: "
							+ passagem.getCd_passagemComando());

		}else 
		if (passagemComando.getComandanteOut() == null) {
			throw new ServiceException("Passagem de Comando sem Comandante!");
		}

		try {
			passagemComandoDao.salvar(passagemComando);
			
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !", causa);

		}

	}

	public List<PassagemComando> buscaTodos() {

		List<PassagemComando> lista = passagemComandoDao.buscarTodos();
		return lista;

	}

	public void excluir(PassagemComando passagemComando) {
		// validacao a exclusao
		passagemComandoDao.excluir(passagemComando);

	}

	public List<PassagemComando> buscarTodosBase(Base base) {

		// validacao da pesquisa
		List<PassagemComando> lista = passagemComandoDao.buscarTodosBase(base);
		return lista;
	}

	public PassagemComando buscarId(Integer intid) {
		// TODO Auto-generated method stub
		PassagemComando usu = new PassagemComando();
		usu = passagemComandoDao.buscarPorId(intid);

		return usu;
	}

	public PassagemComando buscarPassagemComandoRebocador(Rebocador rebocador) {

		return passagemComandoDao.buscarPassagemComandoRebocador(rebocador);

	}

	public PassagemComando getPassagem() {
		return passagem;
	}

	public void setPassagem(PassagemComando passagem) {
		this.passagem = passagem;
	}

}
