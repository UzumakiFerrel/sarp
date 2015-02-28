package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Bairros;
import br.com.sarp.model.entidades.Cidades;

@Repository
public class BairrosDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Bairros bairros) throws DAOException {

		try {
			em.merge(bairros);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(Bairros bairros) {

		try {
			Bairros bairrosexc = buscarPorId(bairros.getCd_bair());
			em.remove(bairrosexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Bairros> buscarTodos() {
		Query consulta = em.createQuery("select b from Bairros b");

		return consulta.getResultList();
	}

	@Transactional
	public Bairros buscarPorId(int id) {

		return em.find(Bairros.class, id);
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Bairros> buscarBairros(Cidades cidade) {
		Query consulta = em
				.createQuery("select c from Bairros c where c.cidade=:est"); // JPQL
		consulta.setParameter("est", cidade);
		return consulta.getResultList();
	}

}
