package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.CadastroInsumo;

@Repository
public class CadastroInsumoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(CadastroInsumo cadastroInsumo) throws DAOException {

		try {
			em.merge(cadastroInsumo);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(CadastroInsumo cadastroInsumo) throws DAOException {

		try {
			CadastroInsumo cadastroInsumoexc = buscarPorId(cadastroInsumo
					.getCd_insumo());
			em.remove(cadastroInsumoexc);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("Nao foi possivel excluir Item", erro);

		}


	} 

	@SuppressWarnings("unchecked")
	@Transactional
	public List<CadastroInsumo> buscarTodos() {
		Query consulta = em.createQuery("select u from CadastroInsumo u");

		return consulta.getResultList();
	}

	@Transactional
	public CadastroInsumo buscarPorId(int id) {

		return em.find(CadastroInsumo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<CadastroInsumo> buscaQueryDescricao(String query) {

		query = "'%" + query + "'";
		Query consulta = em.createQuery("select c from CadastroInsumo c");

		return consulta.getResultList();
	}


}
