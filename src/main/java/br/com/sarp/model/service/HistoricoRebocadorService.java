package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.HistoricoRebocadorDAO;
import br.com.sarp.model.entidades.HistoricoRebocador;
import br.com.sarp.model.entidades.Rebocador;

@Service
public class HistoricoRebocadorService {

	@Inject
	private HistoricoRebocadorDAO historicoRebocadorDao;

	public void salvar(HistoricoRebocador historicoRebocador) throws ServiceException {

		/*
		 * validacao das regras de negocio a base,setor,funcao,situacao devem
		 * existir no cadastro nenhum campo pode ser vazio *

		if ((historicoRebocador.getUsuario() == null)
				&& (historicoRebocador.getNome() == null || historicoRebocador.getNome() == "")) {
			throw new ServiceException(
					"Informar Tripulante ou Visitante para Rebocador!");
		}
		if ((historicoRebocador.getUsuario() != null)
				&& (historicoRebocador.getNome() != null && historicoRebocador.getNome() != "")) {
			throw new ServiceException(
					"Informar Tripulante ou Visitante Apenas!");
		}

		if ((historicoRebocador.getLocalembarque() == null)
				|| (historicoRebocador.getHrembarque() == null || historicoRebocador
						.getHrembarque() == "")
				|| (historicoRebocador.getRebocador() == null || historicoRebocador.getRebocador() == "")) {
			throw new ServiceException("Informa Rebocador , Data e Hora!");

		}
		

		 */
		// validacao da regra exemplo
	


		try {
			historicoRebocadorDao.salvar(historicoRebocador);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !", causa); 

		}

	}

	public List<HistoricoRebocador> buscaTodos() {
		List<HistoricoRebocador> lista = historicoRebocadorDao.buscarTodos();
		return lista;
	}

	public void excluir(HistoricoRebocador embarque) {
		// validacao a exclusao
		historicoRebocadorDao.excluir(embarque);
	}

	public HistoricoRebocador buscarId(Integer intid) {
		return historicoRebocadorDao.buscarPorId(intid);
	}

	public List<HistoricoRebocador> buscaTodosRebocador(Rebocador rbd) {

		List<HistoricoRebocador> lista = historicoRebocadorDao.buscarTodosRebocador(rbd);

		return lista;
	}

}
