package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Insumos;

@Repository
public class InsumosDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Insumos insumos) throws DAOException {

		try {
			em.merge(insumos);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Insumos insumos) {

		try {
Insumos insumosexc=buscarPorId(insumos.getCd_insumos());
			em.remove(insumosexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Insumos> buscarTodos() {
		Query consulta = em.createQuery("select u from Insumos u");

		return consulta.getResultList();
	}

	@Transactional
	public Insumos buscarPorId(int id) {

		return em.find(Insumos.class, id);
	}

}
