package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.TipoEmbarcacoes;

@Repository
public class TipoEmbarcacoesDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(TipoEmbarcacoes tipoEmbarcacoes) throws DAOException {

		try {
			em.merge(tipoEmbarcacoes);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	
	@Transactional
	public void excluir(TipoEmbarcacoes tipoEmbarcacoes) {

		try {
TipoEmbarcacoes tipoEmbarcacoesexc=buscarPorId(tipoEmbarcacoes.getCd_tpemb());
			em.remove(tipoEmbarcacoesexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoEmbarcacoes> buscarTodos() {
		Query consulta = em.createQuery("select u from TipoEmbarcacoes u");

		return consulta.getResultList();
	}

	@Transactional
	public TipoEmbarcacoes buscarPorId(int id) {

		return em.find(TipoEmbarcacoes.class, id);
	}

}
