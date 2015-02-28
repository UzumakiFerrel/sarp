package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Rebocador;

@Repository
public class RebocadorDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;
	
	// gerenciador de transacoes do spring

	@Transactional
	public void salvar(Rebocador rebocador) throws DAOException {

		try {
			em.merge(rebocador);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Rebocador rebocador) {

		try {
Rebocador rebocadorexc=buscarPorId(rebocador.getCd_rebocador());
			em.remove(rebocadorexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Rebocador> buscarTodos() {
		Query consulta = em.createQuery("select u from Rebocador u");

		return consulta.getResultList();
	}

	@Transactional
	public Rebocador buscarPorId(Integer id) {

		return em.find(Rebocador.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Rebocador> buscarTodosBase(Base base) {

		Query consulta = em
				.createQuery("select c from Rebocador c where c.base=:base");
		consulta.setParameter("base", base);
		return consulta.getResultList();
		
	}

}
