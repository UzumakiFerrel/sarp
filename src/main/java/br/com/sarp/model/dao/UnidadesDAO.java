package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Unidades;

@Repository
public class UnidadesDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Unidades unidades) throws DAOException {

		try {
			em.merge(unidades);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Unidades unidades) {

		try {
Unidades unidadesexc=buscarPorId(unidades.getCd_unidades());
			em.remove(unidadesexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Unidades> buscarTodos() {
		Query consulta = em.createQuery("select t from Unidades t");

		return consulta.getResultList();
	}

	@Transactional
	public Unidades buscarPorId(int id) {

		return em.find(Unidades.class, id);
	}

}
