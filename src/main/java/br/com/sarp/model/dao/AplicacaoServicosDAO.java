package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.AplicacaoServicos;

@Repository
public class AplicacaoServicosDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(AplicacaoServicos aplicacaoServicos) throws DAOException {

		try {
			em.merge(aplicacaoServicos);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(AplicacaoServicos aplicacaoServicos) {

		try {
AplicacaoServicos aplicacaoServicosexc=buscarPorId(aplicacaoServicos.getCd_aplicacao());
			em.remove(aplicacaoServicosexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<AplicacaoServicos> buscarTodos() {
		Query consulta = em.createQuery("select u from AplicacaoServicos u");

		return consulta.getResultList();
	}

	@Transactional
	public AplicacaoServicos buscarPorId(Integer id) {

		return em.find(AplicacaoServicos.class, id);
	}

}
