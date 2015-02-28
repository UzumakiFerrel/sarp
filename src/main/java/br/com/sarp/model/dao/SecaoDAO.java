package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Secao;

@Repository
public class SecaoDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Secao secao) throws DAOException {

		try {
			em.merge(secao);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Secao secao) {

		try {
Secao secaoexc=buscarPorId(secao.getCd_secao());
			em.remove(secaoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Secao> buscarTodos() {
		Query consulta = em.createQuery("select s from Secao s");

		return consulta.getResultList();
	}

	@Transactional
	public Secao buscarPorId(int id) {

		return em.find(Secao.class, id);
	}

}
