package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.HistoricoRebocador;
import br.com.sarp.model.entidades.Rebocador;

@Repository
public class HistoricoRebocadorDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(HistoricoRebocador historicoRebocador) throws DAOException {

		try {
			em.merge(historicoRebocador);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(HistoricoRebocador historicoRebocador) {

		try {
			HistoricoRebocador historicoRebocadorexc = buscarPorId(historicoRebocador.getCd_historico());
			em.remove(historicoRebocadorexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<HistoricoRebocador> buscarTodos() {
		Query consulta = em.createQuery("select u from HistoricoRebocador u");

		return consulta.getResultList();
	}

	@Transactional
	public HistoricoRebocador buscarPorId(int id) {

		return em.find(HistoricoRebocador.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<HistoricoRebocador> buscarTodosRebocador(Rebocador rebocador) {
		Query consulta = em
				.createQuery("select c from HistoricoRebocador c where c.rebocador=:rebocador");
		consulta.setParameter("rebocador", rebocador);
		return consulta.getResultList();

	}

}
