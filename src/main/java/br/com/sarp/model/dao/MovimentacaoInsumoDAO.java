package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.CadastroInsumo;
import br.com.sarp.model.entidades.MovimentacaoInsumo;

@Repository
public class MovimentacaoInsumoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(MovimentacaoInsumo movimentacaoInsumo)
			throws DAOException {

		try {
			em.merge(movimentacaoInsumo);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(MovimentacaoInsumo movimentacaoInsumo) {

		try {
			MovimentacaoInsumo movimentacaoInsumoexc = buscarPorId(movimentacaoInsumo
					.getCd_movinsumo());
			em.remove(movimentacaoInsumoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<MovimentacaoInsumo> buscarTodos() {
		Query consulta = em.createQuery("select u from MovimentacaoInsumo u");

		return consulta.getResultList();
	}

	@Transactional
	public MovimentacaoInsumo buscarPorId(int id) {

		return em.find(MovimentacaoInsumo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<MovimentacaoInsumo> buscarMovimentacaoInsumo(
			CadastroInsumo cadastroInsumo) {

		Query consulta = em
				.createQuery("select u from MovimentacaoInsumo u where u.insumo=:cadastroInsumo");
		consulta.setParameter("cadastroInsumo", cadastroInsumo);

		return consulta.getResultList();
	}

}
