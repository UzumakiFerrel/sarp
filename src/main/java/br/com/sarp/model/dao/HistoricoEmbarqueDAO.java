package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.HistoricoEmbarque;
import br.com.sarp.model.entidades.Rebocador;

@Repository
public class HistoricoEmbarqueDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;
	
	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(HistoricoEmbarque historicoEmbarque) throws DAOException {

		try {
			em.merge(historicoEmbarque);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("Nao foi possivel salvar (HistoricoEmbarqueDao)", erro);

		}

	}

	@Transactional
	public void excluir(HistoricoEmbarque historicoEmbarque) {

		try {
			HistoricoEmbarque historicoEmbarqueexc = buscarPorId(historicoEmbarque.getCd_embarque());
			em.remove(historicoEmbarqueexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<HistoricoEmbarque> buscarTodos() {
		Query consulta = em.createQuery("select u from HistoricoEmbarque u");

		return consulta.getResultList();
	}

	@Transactional
	public HistoricoEmbarque buscarPorId(int id) {

		return em.find(HistoricoEmbarque.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<HistoricoEmbarque> buscarTodosRebocador(Rebocador rebocador) {
				Query consulta = em
				.createQuery("select c from HistoricoEmbarque c where c.rebocador=:rebocador");
		consulta.setParameter("rebocador", rebocador);
		return consulta.getResultList();

	}

}
