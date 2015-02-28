package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Estados;

@Repository
public class EstadosDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Estados estados) throws DAOException {

		try {
			em.merge(estados);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Estados estados) {

		try {
Estados estadosexc=buscarPorId(estados.getCd_uf());
			em.remove(estadosexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Estados> buscarTodos() {
		Query consulta = em.createQuery("select e from Estados e");

		return consulta.getResultList();
	}

	@Transactional
	public Estados buscarPorId(Integer integer) {

		return em.find(Estados.class, integer);
	}

}
