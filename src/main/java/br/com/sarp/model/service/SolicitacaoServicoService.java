package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.ItemRequisicaoDAO;
import br.com.sarp.model.dao.MovimentacaoInsumoDAO;
import br.com.sarp.model.dao.SolicitacaoServicoDAO;
import br.com.sarp.model.dao.SituacaoRequisicaoDAO;
import br.com.sarp.model.dao.SolicitacoesDAO;
import br.com.sarp.model.dao.TipoMoveDAO;
import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.ItemRequisicao;
import br.com.sarp.model.entidades.MovimentacaoInsumo;
import br.com.sarp.model.entidades.SolicitacaoServico;
import br.com.sarp.model.entidades.SituacaoRequisicao;
import br.com.sarp.model.entidades.Solicitacoes;

@Service
public class SolicitacaoServicoService {

	private Solicitacoes solicitacoes = new Solicitacoes();
	private MovimentacaoInsumo movimentacaoInsumo;

	@Inject
	private SolicitacaoServicoDAO solicitacaoServicoDao;
	@Inject
	private ItemRequisicaoDAO itemRequisicaoDao;
	@Inject
	private SituacaoRequisicaoDAO situacaoRequisicaoDao;
	@Inject
	private SolicitacoesDAO solicitacoesDao;
	@Inject
	private TipoMoveDAO tipoMoveDao;
	@Inject
	private MovimentacaoInsumoDAO movimentacaoInsumoService;

	public void salvar(SolicitacaoServico solicitacaoServico,
			List<ItemRequisicao> itemRequisicaoList) throws ServiceException {

		/*
		 * validacao das regras de negocio Verificar se tem itens na requisicao
		 * verifcar e corrigir o numero se necessario validar os campos
		 */

		if (itemRequisicaoList.isEmpty() == false) { // verifica se requisicao
														// tem pelo menos um
			// verificar numero da requisicao
			Integer numreq = buscarUltimo();
			if (numreq == null) {
				numreq = 0;
			}
			numreq = numreq + 1;
			if (numreq != solicitacaoServico.getNum_sserv()) {
				solicitacaoServico.setNum_sserv(numreq);
				// atualizar o numero da requisicao na lista de itens
				for (ItemRequisicao item : itemRequisicaoList) {
					item.getRequisicao().setNum_req(numreq);
				}
			}

			// verifica itens zerados ou se ja existe requisicao para o item
			for (ItemRequisicao item : itemRequisicaoList) {
				if (item.getQuantidade() <= 0) {
					throw new ServiceException("Verificar quantidade item !"
							+ item.getInsumo().getCd_insumo());
				}

				if (itemRequisicaoDao.verificaItem(item.getInsumo()
						.getCd_insumo(), item.getRequisicao().getcCustoOrigem()
						.getCd_custo(), item.getAplicacao().getCd_aplicacao()) == true) {
					throw new ServiceException(
							"Item Requisitado pendente para esta aplicacao !");

				}

			}

			try {
				solicitacaoServicoDao.salvar(solicitacaoServico);
				itemRequisicaoDao.salvarList(itemRequisicaoList);
				Integer numero = buscarUltimo();
				// montando o historico movimentacao
				for (ItemRequisicao item : itemRequisicaoList) {

					movimentacaoInsumo = new MovimentacaoInsumo();
					movimentacaoInsumo.setDocumento(numero);
					movimentacaoInsumo.setMovimentacao(tipoMoveDao
							.buscarPorId(1));
					movimentacaoInsumo.setInsumo(item.getInsumo());
					movimentacaoInsumo.setUsuario(solicitacaoServico
							.getSolicitante());
					movimentacaoInsumoService.salvar(movimentacaoInsumo);

				}

				try {
					solicitacoes = solicitacoesDao.buscarSolicitacao(numero);
				} catch (Exception erro) {
					solicitacoes = null;
					erro.printStackTrace();
				}

				if (solicitacoes == null) { // verifica se nao existi
											// solicitacao salva
					solicitacoes = new Solicitacoes();
					solicitacoes.setNum_documento(numero);
					solicitacoes.setTipo("SM");
					solicitacoesDao.salvar(solicitacoes);
				}

			} catch (DAOException causa) {
				throw new ServiceException("Nao foi possivel salvar !", causa);

			}

		} else {
			throw new ServiceException("Requisicao sem Itens !");
		}

	}

	public List<SolicitacaoServico> buscaTodos() {

		List<SolicitacaoServico> lista = solicitacaoServicoDao.buscarTodos();
		return lista;

	}

	public void excluir(SolicitacaoServico solicitacaoServico) {
		// validacao a exclusao
		solicitacaoServicoDao.excluir(solicitacaoServico);

	}

	public SolicitacaoServico buscarId(Integer intid) {

		return solicitacaoServicoDao.buscarPorId(intid);
	}

	public Integer buscarUltimo() {

		return solicitacaoServicoDao.buscarUltimo();
	}

	public List<SolicitacaoServico> buscarcCustoSituacao(CentroCusto cCusto,
			int i) {

		return solicitacaoServicoDao.buscarcCustoSituacao(cCusto, i);
	}

	public void cancelarSolicitacaoServico(SolicitacaoServico solicitacaoServico)
			throws DAOException, ServiceException {

		// Validacao para cancelar requisicao

		SituacaoRequisicao sit = new SituacaoRequisicao();
		sit = situacaoRequisicaoDao.buscarPorId(3);
		try {
			solicitacaoServico.setSit(sit);
			solicitacaoServicoDao.salvar(solicitacaoServico);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel Cancelar Requisicao !"
					+ causa.getMessage());
		}

	}

	public MovimentacaoInsumo getMovimentacaoInsumo() {
		return movimentacaoInsumo;
	}

	public void setMovimentacaoInsumo(MovimentacaoInsumo movimentacaoInsumo) {
		this.movimentacaoInsumo = movimentacaoInsumo;
	}

	public List<SolicitacaoServico> buscarBaseSituacao(Base base, int i) {

		return solicitacaoServicoDao.buscarBaseSituacao(base, i);
	}

}
