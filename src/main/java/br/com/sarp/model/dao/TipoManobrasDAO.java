package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.TipoManobras;

@Repository
public class TipoManobrasDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(TipoManobras tipoMove) throws DAOException {

		try {
			em.merge(tipoMove);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(TipoManobras tipoMove) {

		try {
TipoManobras tipoMoveexc=buscarPorId(tipoMove.getCd_tpmanobras());
			em.remove(tipoMoveexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoManobras> buscarTodos() {
		Query consulta = em.createQuery("select t from TipoManobras t");

		return consulta.getResultList();
	}

	@Transactional
	public TipoManobras buscarPorId(int id) {

		return em.find(TipoManobras.class, id);
	}

}
