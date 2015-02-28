package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.SituacaoRebocador;

@Repository
public class SituacaoRebocadorDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(SituacaoRebocador situacaoRebocador) throws DAOException {

		try {
			em.merge(situacaoRebocador);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(SituacaoRebocador situacaoRebocador) {

		try {
SituacaoRebocador situacaoRebocadorexc=buscarPorId(situacaoRebocador.getCd_sitrb());
			em.remove(situacaoRebocadorexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<SituacaoRebocador> buscarTodos() {
		Query consulta = em.createQuery("select u from SituacaoRebocador u");

		return consulta.getResultList();
	}

	@Transactional
	public SituacaoRebocador buscarPorId(int id) {

		return em.find(SituacaoRebocador.class, id);
	}

}
