package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Embarcacoes;

@Repository
public class EmbarcacoesDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Embarcacoes embarcacoes) throws DAOException {

		try {
			em.merge(embarcacoes);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Embarcacoes embarcacoes) {

		try {
Embarcacoes embarcacoesexc=buscarPorId(embarcacoes.getCd_embarcacoes());
			em.remove(embarcacoesexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Embarcacoes> buscarTodos() {
		Query consulta = em.createQuery("select t from Embarcacoes t");

		return consulta.getResultList();
	}

	@Transactional
	public Embarcacoes buscarPorId(int id) {

		return em.find(Embarcacoes.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Embarcacoes> buscarAutoCompletar(String query) {
		
		Query consulta = em.createQuery("select e from Embarcacoes e where e.nome like '%query%' ");
		
		consulta.setParameter("query", query);
		
		return consulta.getResultList();
		
	
	}

}
