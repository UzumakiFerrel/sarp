package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.TripulanteDAO;
import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Tripulacao;
import br.com.sarp.model.entidades.Tripulante;

@Service
public class TripulanteService {

	@Inject
	private TripulanteDAO tripulanteDao;

	public void salvar(Tripulante tripulante) throws ServiceException {

		/*
		 * validacao das regras de negocio a base,setor,funcao,situacao devem
		 * existir no cadastro nenhum campo pode ser vazio *
		 */
		// validacao da regra exemplo
		if (tripulante.getUsuario().getNome() == null
				|| tripulante.getUsuario().getNome() == "") {
			throw new ServiceException(
					"Campo nome tripulante nao pode ser vazio!");
		}

		try {
			tripulanteDao.salvar(tripulante);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !", causa);

		}

	}

	public List<Tripulante> buscaTodos() {

		List<Tripulante> lista = tripulanteDao.buscarTodos();
		return lista;

	}

	public Tripulante buscarPorId(Integer matricula) {
		return tripulanteDao.buscarPorId(matricula);
	}

	public void excluir(Tripulante tripulante) {
		// validacao a exclusao
		tripulanteDao.excluir(tripulante);

	}

	public TripulanteDAO getTripulanteDao() {
		return tripulanteDao;
	}

	public void setTripulanteDao(TripulanteDAO tripulanteDao) {
		this.tripulanteDao = tripulanteDao;
	}

	public List<Tripulante> buscarPorTripulacao(Tripulacao tripulacao) {

		List<Tripulante> tripulanteList = tripulanteDao
				.buscarPorTripulacao(tripulacao);
		return tripulanteList;
	}

	public List<Tripulante> buscaTodosBase(Base base) throws ServiceException {
		List<Tripulante> tripulanteList = null;

		try {
			tripulanteList = tripulanteDao.buscarTodosBase(base);

		} catch (DAOException causa) {

			throw new ServiceException("Nao foi realizar Busca (TripulanteService) !", causa);
		}

		return tripulanteList;

	}

}
