package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Ocorrencias;
import br.com.sarp.model.entidades.Rebocador;

@Repository
public class OcorrenciasDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Ocorrencias ocorrencias) throws DAOException, PSQLException, DataIntegrityViolationException {

		try {
			em.merge(ocorrencias);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
		}

	}

	@Transactional
	public void excluir(Ocorrencias ocorrencias) {

		try {
			Ocorrencias ocorrenciasexc = buscarPorId(ocorrencias
					.getCd_Ocorrencias());
			em.remove(ocorrenciasexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Ocorrencias> buscarTodos() {
		Query consulta = em.createQuery("select u from Ocorrencias u");

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Ocorrencias> buscarTodosBase(Base base) {
		Query consulta = em
				.createQuery("select c from Ocorrencias c where c.base=:base");
		consulta.setParameter("base", base);
		return consulta.getResultList();
	}

	@Transactional
	public Ocorrencias buscarPorId(int id) {

		return em.find(Ocorrencias.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Ocorrencias> buscarOcorrenciasRebocador(Rebocador rebocador) {
		Query consulta = em
				.createQuery("select c from Ocorrencias c where c.rebocador=:rebocador");
		consulta.setParameter("rebocador", rebocador);

		return consulta.getResultList();

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Ocorrencias> buscaPendentes(Rebocador rebocador) {
		Integer situacao = 1;
		Query consulta = em
				.createQuery("select c from Ocorrencias c where c.rebocador=:rebocador and c.sitOcorrencia.cd_sitrm=:situacao");
		consulta.setParameter("rebocador", rebocador);
		consulta.setParameter("situacao", situacao);
		return consulta.getResultList();

	}

}
