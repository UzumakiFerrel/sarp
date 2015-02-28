package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.TipoServicos;

@Repository
public class TipoServicosDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(TipoServicos tipoServicos) throws DAOException {

		try {
			em.merge(tipoServicos);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(TipoServicos tipoServicos) {

		try {
TipoServicos tipoServicosexc=buscarPorId(tipoServicos.getCd_tpservicos());
			em.remove(tipoServicosexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoServicos> buscarTodos() {
		Query consulta = em.createQuery("select u from TipoServicos u");

		return consulta.getResultList();
	}

	@Transactional
	public TipoServicos buscarPorId(int id) {

		return em.find(TipoServicos.class, id);
	}

}
