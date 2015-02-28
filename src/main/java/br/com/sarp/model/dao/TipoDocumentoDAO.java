package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.TipoDocumento;

@Repository
public class TipoDocumentoDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(TipoDocumento tipoDocumento) throws DAOException {

		try {
			em.merge(tipoDocumento);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(TipoDocumento tipoDocumento) {

		try {
TipoDocumento tipoDocumentoexc=buscarPorId(tipoDocumento.getCd_tpdocumento());
			em.remove(tipoDocumentoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoDocumento> buscarTodos() {
		Query consulta = em.createQuery("select u from TipoDocumento u");

		return consulta.getResultList();
	}

	@Transactional
	public TipoDocumento buscarPorId(int id) {

		return em.find(TipoDocumento.class, id);
	}

}
