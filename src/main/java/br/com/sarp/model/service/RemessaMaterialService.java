package br.com.sarp.model.service;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.ItemRemessaDAO;
import br.com.sarp.model.dao.ItemRequisicaoDAO;
import br.com.sarp.model.dao.RemessaMaterialDAO;
import br.com.sarp.model.dao.RequisicaoMaterialDAO;
import br.com.sarp.model.dao.TipoMoveDAO;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.ItemRemessa;
import br.com.sarp.model.entidades.ItemRequisicao;
import br.com.sarp.model.entidades.RemessaMaterial;
import br.com.sarp.model.entidades.RequisicaoMaterial;
import br.com.sarp.model.entidades.SituacaoRequisicao;
import br.com.sarp.model.entidades.TipoMove;

@Service
public class RemessaMaterialService {

	@Inject
	private RemessaMaterialDAO remessaMaterialDao;
	@Inject
	private ItemRemessaDAO itemRemessaDao;
	@Inject
	private ItemRequisicaoDAO itemRequisicaoDao;
	@Inject
	private SituacaoRequisicaoService situacaoRequisicaoService;
	@Inject
	private TipoMoveDAO tipoMoveDao;
	@Inject
	private RequisicaoMaterialDAO requisicaoMaterialDao;

	public void salvar(RemessaMaterial remessaMaterial,
			List<ItemRemessa> itemRemessaList) throws ServiceException {

		/*
		 * validacao das regras de negocio Verificar se tem itens na requisicao
		 * verifcar e corrigir o numero se necessario validar os campos
		 */

		if (itemRemessaList.isEmpty() == false) { // verifica se requisicao
													// tem pelo menos um
			// verificar numero da remessa
			Integer numrem = buscarUltimo();
			if (numrem == null) {
				numrem = 0;
			}
			numrem = numrem + 1;
			if (numrem != remessaMaterial.getNum_rem()) {
				remessaMaterial.setNum_rem(numrem);
				// atualizar o numero da requisiao na lista de itens
				for (ItemRemessa item : itemRemessaList) {
					item.getRemessa().setNum_rem(numrem);
				}
			}

			// verifica itens zerados ou se j existe requisiao para o item
			for (ItemRemessa item : itemRemessaList) {
				if (item.getQuantidade() <= 0) {
					throw new ServiceException("Verificar quantidade item !"
							+ item.getInsumo().getCd_insumo());
				}
				/*
				 * if (itemRemessaDao.verificaItem(
				 * item.getInsumo().getCd_insumo(), item.getRemessa()
				 * .getDestinoRb().getCd_rebocador()) == true) { throw new
				 * ServiceException("Item j Remetido !");
				 * 
				 * }
				 */
			}

			try {
				remessaMaterialDao.salvar(remessaMaterial);
				itemRemessaDao.salvarList(itemRemessaList);
			} catch (DAOException causa) {
				throw new ServiceException("Nao foi possivel salvar !", causa);

			}

		} else {
			throw new ServiceException("Requisio sem Itens !");
		}

	}

	public List<RemessaMaterial> buscaTodos() {

		List<RemessaMaterial> lista = remessaMaterialDao.buscarTodos();
		return lista;

	}

	public void excluir(RemessaMaterial remessaMaterial) {
		// validacao a exclusao
		remessaMaterialDao.excluir(remessaMaterial);

	}

	public RemessaMaterial buscarId(Integer intid) {

		return remessaMaterialDao.buscarPorId(intid);
	}

	public Integer buscarUltimo() {

		return remessaMaterialDao.buscarUltimo();
	}

	public List<RemessaMaterial> buscarRequisicaoSituacao(Integer num_req, int i) {

		return remessaMaterialDao.buscarRequisicaoSituacao(num_req, i);
	}

	public List<RemessaMaterial> buscarCCustoSituacao(CentroCusto cCusto,
			int situacao) {

		return remessaMaterialDao.buscarCCustoSituacao(cCusto, situacao);
	}

	public void baixarRemessaSolicitacao(RemessaMaterial remessaMaterial)
			throws DAOException, ServiceException {

		// validao
		SituacaoRequisicao status = new SituacaoRequisicao();
		TipoMove sitInsumoBaixado = new TipoMove();
		List<ItemRemessa> itemRemessaList;
		List<ItemRequisicao> itemRequisicaoList;
		RequisicaoMaterial requisicaoMaterial = new RequisicaoMaterial();
		boolean baixar = true;
		// atualisar as quantidades itens da requisicao

		// movimentar o estoque e gravar a movimentacao

		try {
			// altera o status da remessa para baixada
			status = situacaoRequisicaoService.buscarId(2);
			sitInsumoBaixado = tipoMoveDao.buscarPorId(2);
			remessaMaterial.setStatus(status);

			itemRemessaList = itemRemessaDao.buscarTodos(remessaMaterial);
			itemRequisicaoList = itemRequisicaoDao.buscarItensSituacao(
					remessaMaterial.getSolicitacao().getNum_req(), 1);

			for (ItemRemessa itemremessa : itemRemessaList) {

				for (ItemRequisicao itemrequisicao : itemRequisicaoList) {

					if (itemremessa.getInsumo().getCd_insumo() == itemrequisicao
							.getInsumo().getCd_insumo()) {

						itemrequisicao.setPendente(itemrequisicao.getPendente()
								- itemremessa.getQuantidade());
					}

					if (itemrequisicao.getPendente() == 0) {

						itemrequisicao.setSitInsumo(sitInsumoBaixado);

					}
				}
			}

			// verifica se existe alguam pendencia na requisicao
			// senao existe baixa a requisicao
			
			for (ItemRequisicao itemrequisicao : itemRequisicaoList) {

				FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Nao foi possivel salvar ! "+itemrequisicao.getSitInsumo().getCd_tpmove(), null));
				
				if (itemrequisicao.getSitInsumo().getCd_tpmove() == 1) {
					baixar = false;
				}

			}

			
			if (baixar == true) {
				requisicaoMaterial = requisicaoMaterialDao
						.buscarPorId(remessaMaterial.getSolicitacao()
								.getNum_req());

				requisicaoMaterial.setSit(status);
				
			}
			
			itemRequisicaoDao.salvarList(itemRequisicaoList);
			remessaMaterialDao.baixarRemessaSolicitacao(remessaMaterial);

		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel Baixar !", causa);

		}

	}

	public List<RemessaMaterial> buscarRemessaSolicitacoescCustoSituacao(
			CentroCusto cCusto, SituacaoRequisicao situacao) {
		
		return remessaMaterialDao.buscarRemessaSolicitacaoCCustoSituacao(cCusto, situacao);
	}
}
