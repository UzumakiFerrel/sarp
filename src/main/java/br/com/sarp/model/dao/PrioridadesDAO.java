package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Prioridades;

@Repository
public class PrioridadesDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Prioridades prioridades) throws DAOException {

		try {
			em.merge(prioridades);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);	
		}
	}

	@Transactional
	public void excluir(Prioridades prioridades) {

		try {
Prioridades prioridadesexc=buscarPorId(prioridades.getCd_prioridade());
			em.remove(prioridadesexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Prioridades> buscarTodos() {
		Query consulta = em.createQuery("select u from Prioridades u");

		return consulta.getResultList();
	}

	@Transactional
	public Prioridades buscarPorId(int id) {

		return em.find(Prioridades.class, id);
	}

}
