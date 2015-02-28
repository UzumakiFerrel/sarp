package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.TipoHistoricoRebocador;

@Repository
public class TipoHistoricoRebocadorDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(TipoHistoricoRebocador tipoHistoricoRebocador) throws DAOException {

		try {
			em.merge(tipoHistoricoRebocador);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(TipoHistoricoRebocador tipoHistoricoRebocador) {

		try {
TipoHistoricoRebocador tipoHistoricoRebocadorexc=buscarPorId(tipoHistoricoRebocador.getCd_tphistoricorb());
			em.remove(tipoHistoricoRebocadorexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoHistoricoRebocador> buscarTodos() {
		Query consulta = em.createQuery("select t from TipoHistoricoRebocador t");

		return consulta.getResultList();
	}

	@Transactional
	public TipoHistoricoRebocador buscarPorId(int id) {

		return em.find(TipoHistoricoRebocador.class, id);
	}

}
