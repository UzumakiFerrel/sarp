package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.Solicitacoes;

@Repository
public class SolicitacoesDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Solicitacoes solicitacoes) throws DAOException {

		try {
			
			em.merge(solicitacoes);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(Solicitacoes solicitacoes) {

		try {
			Solicitacoes solicitacoesexc = buscarPorId(solicitacoes
					.getCd_solicitacoes());
			em.remove(solicitacoesexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Solicitacoes> buscarTodos() {
		Query consulta = em.createQuery("select u from Solicitacoes u");

		return consulta.getResultList();
	}

	@Transactional
	public Solicitacoes buscarPorId(int id) {

		return em.find(Solicitacoes.class, id);
	}

	@Transactional
	public Solicitacoes buscarSolicitacao(Integer requisicao) {
		Query consulta = em
				.createQuery("select sl from Solicitacoes sl where sl.requisicao=:requisicao");
		consulta.setParameter("requisicao", requisicao);

		return (Solicitacoes) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Solicitacoes> buscarTodoscCusto(CentroCusto cCusto) {
		
		Query consulta = em
				.createQuery("select sl from Solicitacoes sl where sl.centrocusto=:ccusto");
		consulta.setParameter("ccusto", cCusto);
		
		return consulta.getResultList();

	}
	

}
