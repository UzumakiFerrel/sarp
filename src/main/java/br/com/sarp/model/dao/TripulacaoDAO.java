package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Tripulacao;

@Repository
public class TripulacaoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Tripulacao tripulacao) throws DAOException {

		try {
			em.merge(tripulacao);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
		}
	}

	@Transactional
	public void excluir(Tripulacao tripulacao) {

		try {
			Tripulacao tripulacaoexc = buscarPorId(tripulacao
					.getCd_tripulacao());
			em.remove(tripulacaoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tripulacao> buscarTodos() {
		Query consulta = em.createQuery("select u from Tripulacao u");

		return consulta.getResultList();
	}

	@Transactional
	public Tripulacao buscarPorId(int id) {

		return em.find(Tripulacao.class, id);
	}

}
