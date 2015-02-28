package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.SituacaoFuncionario;

@Repository
public class SituacaoFuncionarioDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(SituacaoFuncionario situacaoFuncionario) throws DAOException {

		try {
			em.merge(situacaoFuncionario);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(SituacaoFuncionario situacaoFuncionario) {

		try {
SituacaoFuncionario situacaoFuncionarioexc=buscarPorId(situacaoFuncionario.getCd_sit());
			em.remove(situacaoFuncionarioexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<SituacaoFuncionario> buscarTodos() {
		Query consulta = em.createQuery("select u from SituacaoFuncionario u");

		return consulta.getResultList();
	}

	@Transactional
	public SituacaoFuncionario buscarPorId(int id) {

		return em.find(SituacaoFuncionario.class, id);
	}

}
