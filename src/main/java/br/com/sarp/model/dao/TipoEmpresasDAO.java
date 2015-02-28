package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.TipoEmpresas;

@Repository
public class TipoEmpresasDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(TipoEmpresas tipoEmpresas) throws DAOException {

		try {
			em.merge(tipoEmpresas);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(TipoEmpresas tipoEmpresas) {

		try {
TipoEmpresas tipoEmpresasexc=buscarPorId(tipoEmpresas.getTp_empresa());
			em.remove(tipoEmpresasexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TipoEmpresas> buscarTodos() {
		Query consulta = em.createQuery("select u from TipoEmpresas u");

		return consulta.getResultList();
	}

	@Transactional
	public TipoEmpresas buscarPorId(int id) {

		return em.find(TipoEmpresas.class, id);
	}

}
