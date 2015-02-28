package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Funcao;

@Repository
public class FuncaoDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Funcao funcao) throws DAOException {

		try {
			em.merge(funcao);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Funcao funcao) {

		try {
Funcao funcaoexc=buscarPorId(funcao.getCd_funcao());
			em.remove(funcaoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Funcao> buscarTodos() {
		Query consulta = em.createQuery("select u from Funcao u");

		return consulta.getResultList();
	}

	@Transactional
	public Funcao buscarPorId(int id) {

		return em.find(Funcao.class, id);
	}

}
