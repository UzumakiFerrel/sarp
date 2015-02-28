package br.com.sarp.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.ItemRequisicao;
import br.com.sarp.model.service.MovimentacaoInsumoService;

@Repository
public class ItemSolicitacaoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	@Inject
	private MovimentacaoInsumoService movimentacaoInsumoService;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(ItemRequisicao itemRequisicao) throws DAOException {

		try {
			em.merge(itemRequisicao);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	public void salvarList(List<ItemRequisicao> itemRequisicaoList)
			throws DAOException {

		for (ItemRequisicao item : itemRequisicaoList) {

			try {
				em.merge(item);
			} catch (Exception erro) {// captura e excecao no banco de dados
				throw new DAOException("nao foi possivel salvar", erro);

			}

		}
	}

	/*
	 * @Transactional public void excluir(ItemRequisicao itemRequisicao) {
	 * 
	 * try { ItemRequisicao
	 * itemRequisicaoexc=buscarPorId(itemRequisicao.getInsumo().getCd_insumo());
	 * em.remove(itemRequisicaoexc); } catch (Exception e) {// captura e excecao
	 * no banco de dados //e.printStackTrace();
	 * System.out.println("Erro ao excluir! "+e.getMessage()); }
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public List<ItemRequisicao> buscarTodos() {
		Query consulta = em.createQuery("select u from ItemRequisicao u");

		return consulta.getResultList();
	}

	@Transactional
	public ItemRequisicao buscarPorId(int id) {

		return em.find(ItemRequisicao.class, id);
	}

	@Transactional
	public boolean verificaItem(Integer cd_insumo, Integer cd_centroCusto,
			Integer cd_aplicacao) {
		Integer status = 1;
		Query consulta = em
				.createQuery("select u from ItemRequisicao u where u.insumo.cd_insumo=:insumo and sitInsumo.cd_tpmove=:status");
		consulta.setParameter("insumo", cd_insumo);
		consulta.setParameter("status", status);
		@SuppressWarnings({ "unchecked" })
		List<ItemRequisicao> itens = consulta.getResultList();

		for (ItemRequisicao item : itens) {
			if ((item.getRequisicao().getcCustoOrigem().getCd_custo() == cd_centroCusto)
					&& (item.getAplicacao().getCd_aplicacao() == cd_aplicacao)) {
				return true;
			}
		}

		return false;
	}

	public void excluir(ItemRequisicao itemRequisicao) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<ItemRequisicao> buscarItensSituacao(Integer numreq,
			Integer situacao) {

		Query consulta = em
				.createQuery("select u from ItemRequisicao u where u.requisicao.num_req=:numreq AND u.sitInsumo.cd_tpmove=:situacao ");
		consulta.setParameter("numreq", numreq);
		consulta.setParameter("situacao", situacao);
		return consulta.getResultList();
	}

	public MovimentacaoInsumoService getMovimentacaoInsumoService() {
		return movimentacaoInsumoService;
	}

	public void setMovimentacaoInsumoService(
			MovimentacaoInsumoService movimentacaoInsumoService) {
		this.movimentacaoInsumoService = movimentacaoInsumoService;
	}

}
