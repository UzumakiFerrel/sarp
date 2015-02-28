package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.SituacaoRequisicao;

@Repository
public class SituacaoRequisicaoDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(SituacaoRequisicao situacaoRequisicao) throws DAOException {

		try {
			em.merge(situacaoRequisicao);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(SituacaoRequisicao situacaoRequisicao) {

		try {
SituacaoRequisicao situacaoRequisicaoexc=buscarPorId(situacaoRequisicao.getCd_sitrm());
			em.remove(situacaoRequisicaoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<SituacaoRequisicao> buscarTodos() {
		Query consulta = em.createQuery("select u from SituacaoRequisicao u");

		return consulta.getResultList();
	}

	@Transactional
	public SituacaoRequisicao buscarPorId(int id) {

		return em.find(SituacaoRequisicao.class, id);
	}

}
