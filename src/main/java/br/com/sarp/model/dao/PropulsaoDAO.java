package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Propulsao;

@Repository
public class PropulsaoDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Propulsao propulsao) throws DAOException {

		try {
			em.merge(propulsao);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Propulsao propulsao) {

		try {
Propulsao propulsaoexc=buscarPorId(propulsao.getCd_propulsao());
			em.remove(propulsaoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Propulsao> buscarTodos() {
		Query consulta = em.createQuery("select u from Propulsao u");

		return consulta.getResultList();
	}

	@Transactional
	public Propulsao buscarPorId(Integer id) {

		return em.find(Propulsao.class, id);
	}

}
