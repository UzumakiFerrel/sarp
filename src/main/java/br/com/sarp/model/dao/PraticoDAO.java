package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Pratico;

@Repository
public class PraticoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Pratico pratico) throws DAOException {

		try {
			em.merge(pratico);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(Pratico pratico) {

		try {
			Pratico praticoexc = buscarPorId(pratico.getCd_pratico());
			em.remove(praticoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Pratico> buscarTodos() {
		Query consulta = em.createQuery("select u from Pratico u");

		return consulta.getResultList();
	}

	@Transactional
	public Pratico buscarPorId(Integer id) {

		return em.find(Pratico.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Pratico> buscarTodosBase(Base base) {
		Query consulta = em.createQuery("select u from Pratico u where u.base=:base");
				consulta.setParameter("base", base);
		
		return consulta.getResultList();
		
	}
	
}
