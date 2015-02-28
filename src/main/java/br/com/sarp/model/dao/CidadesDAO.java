package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Cidades;
import br.com.sarp.model.entidades.Estados;

@Repository
public class CidadesDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Cidades cidades) throws DAOException {

		try {
			em.merge(cidades);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Cidades cidades) {

		try {
Cidades cidadesexc=buscarPorId(cidades.getCd_cid());
			em.remove(cidadesexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cidades> buscarTodos() {
		Query consulta = em.createQuery("select c from Cidades c");

		return consulta.getResultList();
	}

	@Transactional
	public Cidades buscarPorId(Integer id) {

		return em.find(Cidades.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cidades> buscarCidades(Estados estado) {
		Query consulta = em.createQuery("select c from Cidades c where c.estado=:est"); //JPQL
		consulta.setParameter("est", estado);
		return consulta.getResultList();
	}
	
	
}
