package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.ItemRemessa;
import br.com.sarp.model.entidades.RemessaMaterial;

@Repository
public class ItemRemessaDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(ItemRemessa itemRemessa) throws DAOException {

		try {
			em.merge(itemRemessa);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	public void salvarList(List<ItemRemessa> itemRemessaList)
			throws DAOException {

		for (ItemRemessa item : itemRemessaList) {

			try {
				em.merge(item);
			} catch (Exception erro) {// captura e excecao no banco de dados
				throw new DAOException("nao foi possivel salvar", erro);

			}

		}
	}

	/*
	 * @Transactional public void excluir(ItemRemessa itemRemessa) {
	 * 
	 * try { ItemRemessa
	 * itemRemessaexc=buscarPorId(itemRemessa.getInsumo().getCd_insumo());
	 * em.remove(itemRemessaexc); } catch (Exception e) {// captura e excecao
	 * no banco de dados //e.printStackTrace();
	 * System.out.println("Erro ao excluir! "+e.getMessage()); }
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public List<ItemRemessa> buscarTodos(RemessaMaterial remessaMaterial) {
		Query consulta = em.createQuery("select u from ItemRemessa u where u.remessa=:remessa");
		consulta.setParameter("remessa", remessaMaterial);

		return consulta.getResultList();
	}

	
	@Transactional
	public ItemRemessa buscarPorId(int id) {

		return em.find(ItemRemessa.class, id);
	}

	
	@Transactional
	public boolean verificaItem(Integer cd_insumo, Integer cd_centroCusto) {
		Query consulta = em
				.createQuery("select u from ItemRemessa u where u.insumo.cd_insumo=:insumo");
		consulta.setParameter("insumo", cd_insumo);

		@SuppressWarnings({ "unchecked" })
		List<ItemRemessa> itens = consulta.getResultList();

		for (ItemRemessa item : itens) {
			if ((item.getRemessa().getcCustoOrigem().getCd_custo() == cd_centroCusto)
				) {
				return true;
			}
		}

		return false;
	}

	
}
