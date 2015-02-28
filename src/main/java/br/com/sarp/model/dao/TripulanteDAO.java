package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Tripulacao;
import br.com.sarp.model.entidades.Tripulante;

@Repository
public class TripulanteDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Tripulante tripulante) throws DAOException {

		try {
			tripulante.setiD(tripulante.getUsuario().getMatricula());
			em.merge(tripulante);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(Tripulante tripulante) {

		try {
			Tripulante tripulanteexc = buscarPorId(tripulante.getUsuario()
					.getMatricula());
			em.remove(tripulanteexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tripulante> buscarTodos() {
		Query consulta = em.createQuery("select u from Tripulante u");

		return consulta.getResultList();
	}

	@Transactional
	public Tripulante buscarPorId(Integer id) {

		return em.find(Tripulante.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tripulante> buscarPorTripulacao(Tripulacao tripulacao) {
		Query consulta = em
				.createQuery("select t from Tripulante t where t.tripulacao=:tripulacao");
		consulta.setParameter("tripulacao", tripulacao);

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tripulante> buscarTodosBase(Base base) throws DAOException {

		try {

			Query consulta = em
					.createQuery("select t from Tripulante t where t.usuario.base=:base");
			consulta.setParameter("base", base);

			return consulta.getResultList();

		} catch (Exception erro) {// captura e excecao no banco de dados

			throw new DAOException(
					"Nao foi possivel Realizar pesquisa (TripulanteDao)", erro);
		}
	}

}
