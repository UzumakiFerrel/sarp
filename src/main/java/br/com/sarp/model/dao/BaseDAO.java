package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Base;

@Repository
public class BaseDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Base base) throws DAOException {

		try {
			em.merge(base);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	
	@Transactional
	public void excluir(Base base) {

		try {
Base baseexc=buscarPorId(base.getCd_base());
			em.remove(baseexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Base> buscarTodos() {
		Query consulta = em.createQuery("select b from Base b");

		return consulta.getResultList();
	}

	@Transactional
	public Base buscarPorId(int id) {

		return em.find(Base.class, id);
	}

	
}
