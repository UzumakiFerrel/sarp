package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.HistoricoEmbarqueDAO;
import br.com.sarp.model.entidades.HistoricoEmbarque;
import br.com.sarp.model.entidades.Rebocador;

@Service
public class HistoricoEmbarqueService {

	@Inject
	private HistoricoEmbarqueDAO historicoEmbarqueDao;

	public void salvar(HistoricoEmbarque historicoEmbarque) throws ServiceException {

		/*
		 * validacao das regras de negocio a base,setor,funcao,situacao devem
		 * existir no cadastro nenhum campo pode ser vazio *

		if ((historicoEmbarque.getUsuario() == null)
				&& (historicoEmbarque.getNome() == null || historicoEmbarque.getNome() == "")) {
			throw new ServiceException(
					"Informar Tripulante ou Visitante para Embarque!");
		}
		if ((historicoEmbarque.getUsuario() != null)
				&& (historicoEmbarque.getNome() != null && historicoEmbarque.getNome() != "")) {
			throw new ServiceException(
					"Informar Tripulante ou Visitante Apenas!");
		}

		if ((historicoEmbarque.getLocalembarque() == null)
				|| (historicoEmbarque.getHrembarque() == null || historicoEmbarque
						.getHrembarque() == "")
				|| (historicoEmbarque.getEmbarque() == null || historicoEmbarque.getEmbarque() == "")) {
			throw new ServiceException("Informa Embarque , Data e Hora!");

		}
		

		 */
		// validacao da regra exemplo
	


		try {
			historicoEmbarqueDao.salvar(historicoEmbarque);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !", causa); 

		}

	}

	public List<HistoricoEmbarque> buscaTodos() {
		List<HistoricoEmbarque> lista = historicoEmbarqueDao.buscarTodos();
		return lista;
	}

	public void excluir(HistoricoEmbarque embarque) {
		// validacao a exclusao
		historicoEmbarqueDao.excluir(embarque);
	}

	public HistoricoEmbarque buscarId(Integer intid) {
		return historicoEmbarqueDao.buscarPorId(intid);
	}

	public List<HistoricoEmbarque> buscaTodosRebocador(Rebocador rbd) {

		List<HistoricoEmbarque> lista = historicoEmbarqueDao.buscarTodosRebocador(rbd);

		return lista;
	}

}
