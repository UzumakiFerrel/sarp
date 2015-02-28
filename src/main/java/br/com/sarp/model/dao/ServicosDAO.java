package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Servicos;

@Repository
public class ServicosDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Servicos servicos) throws DAOException {

		try {
			em.merge(servicos);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(Servicos servicos) throws DAOException {

		try {
			Servicos servicosexc = buscarPorId(servicos.getCd_servicos());
			em.remove(servicosexc);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("Nao foi possivel excluir Item", erro);

		}


	} 

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Servicos> buscarTodos() {
		Query consulta = em.createQuery("select u from Servicos u");

		return consulta.getResultList();
	}

	@Transactional
	public Servicos buscarPorId(int id) {

		return em.find(Servicos.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Servicos> buscaQueryDescricao(String query) {

		query = "'%" + query + "'";
		Query consulta = em.createQuery("select c from Servicos c");

		return consulta.getResultList();
	}


}
