package br.com.sarp.controller.beans;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.CadastroInsumo;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.ItemRemessa;
import br.com.sarp.model.entidades.ItemRequisicao;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.RemessaMaterial;
import br.com.sarp.model.entidades.RequisicaoMaterial;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.CadastroInsumoService;
import br.com.sarp.model.service.CentroCustoService;
import br.com.sarp.model.service.EmbarqueService;
import br.com.sarp.model.service.ItemRequisicaoService;
import br.com.sarp.model.service.RemessaMaterialService;
import br.com.sarp.model.service.RequisicaoMaterialService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SituacaoRequisicaoService;
import br.com.sarp.model.service.TipoDocumentoService;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class RemessaMaterialBean {

	private Usuario logado = new Usuario();
	private Tripulante tripulante = new Tripulante();
	private RemessaMaterial remessaMaterial = new RemessaMaterial();
	private ItemRemessa itens = new ItemRemessa();
	private CadastroInsumo insumo = new CadastroInsumo();
	private Integer numDocOrigem, numItem = 0;
	private float pendente;
	private RequisicaoMaterial requisicaoMaterial;
	private ItemRequisicao itemRequisicao;
	private Rebocador rebocador = new Rebocador();

	private List<ItemRemessa> itemRemessaList = new ArrayList<>();
	private List<CadastroInsumo> insumoList;
	private List<ItemRequisicao> itensRequisicao;
	private List<RemessaMaterial> remessaMaterialList;
	private List<RemessaMaterial> remessaMaterialSolicitacoesList;

	private List<RemessaMaterial> remessaMaterialBaixaList; // lista de remessas
															// por centro de
															// custo para baixar

	private List<RequisicaoMaterial> requisicaoMaterialList = new ArrayList<>();

	@Inject
	RequisicaoMaterialService requisicaoMaterialService;
	@Inject
	RemessaMaterialService remessaMaterialService;
	@Inject
	TripulanteService tripulanteService;
	@Inject
	CadastroInsumoService cadastroInsumoService;
	@Inject
	ItemRequisicaoService itemRequisicaoService;
	@Inject
	CentroCustoService centrocustoService;
	@Inject
	SituacaoRequisicaoService situacaoRequisicaoService;
	@Inject
	EmbarqueService embarqueService;
	@Inject
	TipoDocumentoService tipoDocumentoService;

	public void salvar() {

		try {
			remessaMaterial.setTpDocumento(tipoDocumentoService.buscarId(4));
			remessaMaterialService.salvar(remessaMaterial, itemRemessaList);
			remessaMaterial = new RemessaMaterial();
			itemRemessaList.clear();
			itensRequisicao.clear();
			remessaMaterialList = remessaMaterialService.buscaTodos();
			numItem = 0;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Salvo com sucesso !", null));

		} catch (ServiceException e) {
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Nao foi possivel salvar ! "
											+ e.getMessage(), null));
			// codigo que envia msg de erro pra tela
			e.printStackTrace();
		}

	}

	public void salvarRemessaTransferencia() {

		try {

			remessaMaterial.setTpDocumento(tipoDocumentoService.buscarId(3));
			remessaMaterialService.salvar(remessaMaterial, itemRemessaList);
			remessaMaterial = new RemessaMaterial();
			itemRemessaList.clear();
			remessaMaterialList = remessaMaterialService.buscaTodos();
			numItem = 0;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Salvo com sucesso !", null));

		} catch (ServiceException e) {
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Nao foi possivel salvar ! "
											+ e.getMessage(), null));
			// codigo que envia msg de erro pra tela
			e.printStackTrace();
		}

	}

	public void salvaItemRemessa() {

		// esta funcao salva os item da solicitacao em uma lista
		// os itens serao salvos no banco ao salvar a remessa

		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		DateFormat f = DateFormat.getDateInstance();
		String abertura = f.format(data);
		numItem = numItem + 1;

		itens.setData(abertura);
		itens.setRemessa(getRemessaMaterial());
		itens.setNumItem(numItem);

		// verifica quantidade dos itens

		if (itens.getQuantidade() > getPendente()) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Quantidade Item Superior ao Requisitado!!!",
									null));
			itens.setQuantidade(getPendente());
		}

		// impede que duplique os item no list
		if (itemRemessaList.contains(itens) == true) {
			itemRemessaList.remove(itens);
		}

		itemRemessaList.add(itens);
		itens = new ItemRemessa();
		pendente = 0;
		insumo = new CadastroInsumo();

	}

	public void salvaItemRemessaTransferencia() {

		// esta funcao salva os item da solicitacao em uma lista
		// os itens serao salvos no banco ao salvar a remessa

		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		DateFormat f = DateFormat.getDateInstance();
		String abertura = f.format(data);
		numItem = numItem + 1;

		itens.setData(abertura);
		itens.setRemessa(getRemessaMaterial());
		itens.setNumItem(numItem);

		// impede que duplique os item no list
		if (itemRemessaList.contains(itens) == true) {
			itemRemessaList.remove(itens);
		}

		itemRemessaList.add(itens);
		itens = new ItemRemessa();
		insumo = new CadastroInsumo();

	}

	public void converteItem() {

		itens.setInsumo(itemRequisicao.getInsumo());
		itens.setQuantidade(itemRequisicao.getPendente());
		setPendente(itemRequisicao.getPendente());
	}

	public void CancelaItens() {

		itemRemessaList.clear();
		numItem = 0;
	}

	public void ExcluirItem() {

		itemRemessaList.remove(itens);
		// atualisar numitem
		itens = new ItemRemessa();
		insumo = new CadastroInsumo();

	}

	public void buscarSolicitacao(ValueChangeEvent evento) { // verifica centro
																// de custo e
																// retorna uma
																// lista de
																// solicitacoes
																// do centro de
																// custo

		List<RequisicaoMaterial> requisicoes;
		int qt;
		requisicaoMaterialList.clear();

		if (evento.getNewValue() != evento.getOldValue()) {
			CentroCusto cCusto = (CentroCusto) evento.getNewValue();

			requisicoes = requisicaoMaterialService.buscarcCustoSituacao(
					cCusto, 1);

			for (RequisicaoMaterial req : requisicoes) {
				qt = 0;
				qt = remessaMaterialService.buscarRequisicaoSituacao(
						req.getNum_req(), 1).size();
				if (qt == 0) {
					requisicaoMaterialList.add(req);
				}

			}
		}

	}

	public void buscarItensSolicitacao(ValueChangeEvent event) {

		if (event.getNewValue() != event.getOldValue()) {
			RequisicaoMaterial requisicaoMaterial = (RequisicaoMaterial) event
					.getNewValue();
			itensRequisicao = itemRequisicaoService.buscarItensSituacao(
					requisicaoMaterial.getNum_req(), 1);

		}

	}

	public List<CadastroInsumo> listInsumo(String query) { // metodo de pesquisa
		insumoList = cadastroInsumoService.buscaQueryDescricao(query);

		return insumoList;
	}

	public List<ItemRequisicao> itemRequisicaoSituacao(Integer numreq,
			Integer situacao) {

		List<ItemRequisicao> lista = itemRequisicaoService.buscarItensSituacao(
				numreq, situacao);

		return lista;

	}

	public List<RemessaMaterial> remessaMaterialBaixaLista(Integer cd_rebocador) {

		remessaMaterialBaixaList = remessaMaterialService.buscarCCustoSituacao(
				centrocustoService.buscarRebocadorII(cd_rebocador), 1);

		return remessaMaterialBaixaList;
	}

	@PostConstruct
	public void init() {

	}

	public void excluir() {
		remessaMaterialService.excluir(remessaMaterial);
		remessaMaterialList = remessaMaterialService.buscaTodos();
		// limpando instancia do remessaMaterial
		remessaMaterial = new RemessaMaterial();
	}

	public void baixarRemessaSolicitacao() throws DAOException,
			ServiceException {

		remessaMaterialService.baixarRemessaSolicitacao(remessaMaterial);

	}

	public RemessaMaterial getRemessaMaterial() {

		Integer numreq = remessaMaterialService.buscarUltimo();
		if (numreq == null) {
			numreq = 0;
		}

		numreq = numreq + 1;

		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		DateFormat f = DateFormat.getDateInstance();
		String abertura = f.format(data);

		remessaMaterial.setNum_rem(numreq);// definindo o numero da
											// remessa

		remessaMaterial.setData(abertura);
		remessaMaterial.setBase(logado.getBase());
		remessaMaterial.setStatus(situacaoRequisicaoService.buscarId(1)); // definindo
																			// situacao
																			// da
																			// remessa

		if (logado.getSetor().getCd_setor() != 1) { // determina centro de custo
													// origem
			remessaMaterial.setcCustoOrigem(centrocustoService.buscarSetor(
					logado.getSetor(), logado.getBase()));
		} else {

			remessaMaterial.setcCustoOrigem(centrocustoService
					.buscarRebocador(getRebocador()));
		}

		return remessaMaterial;
	}

	public void setRemessaMaterial(RemessaMaterial remessaMaterial) {
		this.remessaMaterial = remessaMaterial;
	}

	public RemessaMaterialService getRemessaMaterialService() {
		return remessaMaterialService;
	}

	public void setRemessaMaterialService(
			RemessaMaterialService remessaMaterialService) {
		this.remessaMaterialService = remessaMaterialService;
	}

	public List<RemessaMaterial> getRemessaMaterialList() {
		return remessaMaterialList;
	}

	public void setRemessaMaterialList(List<RemessaMaterial> remessaMaterialList) {
		this.remessaMaterialList = remessaMaterialList;
	}

	public Usuario getLogado() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		logado = (Usuario) session.getAttribute("usuarioLogado");

		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public Tripulante getTripulante() {

		tripulante = tripulanteService.buscarPorId(getLogado().getMatricula());
		return tripulante;
	}

	public void setTripulante(Tripulante tripulante) {
		this.tripulante = tripulante;
	}

	public ItemRemessa getItens() {
		return itens;
	}

	public void setItens(ItemRemessa itens) {
		this.itens = itens;
	}

	public List<ItemRemessa> getItemRemessaList() {
		return itemRemessaList;
	}

	public void setItemRemessaList(List<ItemRemessa> itemRemessaList) {
		this.itemRemessaList = itemRemessaList;
	}

	public List<CadastroInsumo> getInsumoList() {
		return insumoList;
	}

	public void setInsumoList(List<CadastroInsumo> insumoList) {
		this.insumoList = insumoList;
	}

	public TripulanteService getTripulanteService() {
		return tripulanteService;
	}

	public void setTripulanteService(TripulanteService tripulanteService) {
		this.tripulanteService = tripulanteService;
	}

	public CadastroInsumoService getCadastroInsumoService() {
		return cadastroInsumoService;
	}

	public void setCadastroInsumoService(
			CadastroInsumoService cadastroInsumoService) {
		this.cadastroInsumoService = cadastroInsumoService;
	}

	public CadastroInsumo getInsumo() {
		return insumo;
	}

	public void setInsumo(CadastroInsumo insumo) {
		this.insumo = insumo;
	}

	public Integer getNumDocOrigem() {
		return numDocOrigem;
	}

	public void setNumDocOrigem(Integer numDocOrigem) {
		this.numDocOrigem = numDocOrigem;
	}

	public List<ItemRequisicao> getItensRequisicao() {
		return itensRequisicao;
	}

	public void setItensRequisicao(List<ItemRequisicao> itensRequisicao) {

		this.itensRequisicao = itensRequisicao;
	}

	public List<RequisicaoMaterial> getRequisicaoMaterialList() {
		return requisicaoMaterialList;
	}

	public void setRequisicaoMaterialList(
			List<RequisicaoMaterial> requisicaoMaterialList) {
		this.requisicaoMaterialList = requisicaoMaterialList;
	}

	public RequisicaoMaterial getRequisicaoMaterial() {
		return requisicaoMaterial;
	}

	public void setRequisicaoMaterial(RequisicaoMaterial requisicaoMaterial) {
		this.requisicaoMaterial = requisicaoMaterial;
	}

	public ItemRequisicao getItemRequisicao() {
		return itemRequisicao;
	}

	public void setItemRequisicao(ItemRequisicao itemRequisicao) {
		this.itemRequisicao = itemRequisicao;
	}

	public List<RemessaMaterial> getRemessaMaterialBaixaList() {

		return remessaMaterialBaixaLista(2);
	}

	public void setRemessaMaterialBaixaList(
			List<RemessaMaterial> remessaMaterialBaixaList) {

		this.remessaMaterialBaixaList = remessaMaterialBaixaList;
	}

	public float getPendente() {
		return pendente;
	}

	public void setPendente(float pendente) {
		this.pendente = pendente;
	}

	public Rebocador getRebocador() {

		rebocador = embarqueService.buscarRebocadorEmbarque(getLogado());

		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public List<RemessaMaterial> getRemessaMaterialSolicitacoesList() {

		remessaMaterialSolicitacoesList = remessaMaterialService
				.buscarRemessaSolicitacoescCustoSituacao(
						centrocustoService.buscarRebocador(getRebocador()),
						situacaoRequisicaoService.buscarId(1));

		return remessaMaterialSolicitacoesList;
	}

	public void setRemessaMaterialSolicitacoesList(
			List<RemessaMaterial> remessaMaterialSolicitacoesList) {
		this.remessaMaterialSolicitacoesList = remessaMaterialSolicitacoesList;
	}

}
