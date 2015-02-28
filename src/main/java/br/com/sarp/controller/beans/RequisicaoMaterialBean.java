package br.com.sarp.controller.beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.CadastroInsumo;
import br.com.sarp.model.entidades.ItemRequisicao;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.RequisicaoMaterial;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.CadastroInsumoService;
import br.com.sarp.model.service.CentroCustoService;
import br.com.sarp.model.service.RequisicaoMaterialService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SituacaoRequisicaoService;
import br.com.sarp.model.service.TipoMoveService;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class RequisicaoMaterialBean {

	private Usuario logado = new Usuario();
	private Tripulante tripulante = new Tripulante();
	private RequisicaoMaterial requisicaoMaterial = new RequisicaoMaterial();
	private ItemRequisicao itens = new ItemRequisicao();
	private CadastroInsumo insumo = new CadastroInsumo();
	private Integer numItem = 0;
	private Rebocador rebocador = new Rebocador();
	private Integer vencido;

	private List<ItemRequisicao> itemRequisicaoList = new ArrayList<>();
	private List<CadastroInsumo> insumoList;
	private List<RequisicaoMaterial> requisicaoMaterialList;
	// private List<RequisicaoMaterial> requisicaoMaterialCancelarList;

	@Inject
	RequisicaoMaterialService requisicaoMaterialService;
	@Inject
	TripulanteService tripulanteService;
	@Inject
	CadastroInsumoService cadastroInsumoService;
	@Inject
	CentroCustoService centroCustoService;
	@Inject
	TipoMoveService tipoMoveService;
	@Inject
	SituacaoRequisicaoService situacaoRequisicaoService;

	public void salvar() throws ParseException {

		try {

			// DETERMINAR PRAZO DE RESOLUCAO
			Calendar c = Calendar.getInstance();
			DateFormat f = DateFormat.getDateInstance();
			Date data = f.parse(requisicaoMaterial.getAbertura());

			c.setTime(data);

			if (requisicaoMaterial.getPrioridade().getCd_prioridade() == 1) {

				c.add(Calendar.DAY_OF_MONTH, requisicaoMaterial.getPrioridade()
						.getDias());
				Date dt = c.getTime();
				requisicaoMaterial.setPrazo(f.format(dt));

			} else if (requisicaoMaterial.getPrioridade().getCd_prioridade() == 2) {
				c.add(Calendar.DAY_OF_MONTH, requisicaoMaterial.getPrioridade()
						.getDias());
				Date dt = c.getTime();
				requisicaoMaterial.setPrazo(f.format(dt));
			} else if (requisicaoMaterial.getPrioridade().getCd_prioridade() == 3) {
				c.add(Calendar.DAY_OF_MONTH, requisicaoMaterial.getPrioridade()
						.getDias());
				Date dt = c.getTime();
				requisicaoMaterial.setPrazo(f.format(dt));
			} else if (requisicaoMaterial.getPrioridade().getCd_prioridade() == 4) {
				c.add(Calendar.DAY_OF_MONTH, requisicaoMaterial.getPrioridade()
						.getDias());
				Date dt = c.getTime();
				requisicaoMaterial.setPrazo(f.format(dt));
			} else if (requisicaoMaterial.getPrioridade().getCd_prioridade() == 5) {
				c.add(Calendar.DAY_OF_MONTH, requisicaoMaterial.getPrioridade()
						.getDias());
				Date dt = c.getTime();
				requisicaoMaterial.setPrazo(f.format(dt));
			} else if (requisicaoMaterial.getPrioridade().getCd_prioridade() == 6) {
				c.add(Calendar.DAY_OF_MONTH, requisicaoMaterial.getPrioridade()
						.getDias());
				Date dt = c.getTime();
				requisicaoMaterial.setPrazo(f.format(dt));
			}

			requisicaoMaterialService.salvar(requisicaoMaterial,
					itemRequisicaoList);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerado SM.: "
							+ requisicaoMaterial.getNum_req()
							+ " Prazo de Conclusao.: "
							+ requisicaoMaterial.getPrazo(), null));
			requisicaoMaterial = new RequisicaoMaterial();
			itemRequisicaoList.clear();
			requisicaoMaterialList = requisicaoMaterialService.buscaTodos();
			numItem = 0;

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

	public void salvaItemRequisicao() {
		// esta funcao salva os item da requisicao em uma lista
		// os itens serao salvos no banco ao salvar a requisicao

		System.out.println("Item :" + getInsumo().getCd_insumo());
		System.out.println("Descricao item :" + getInsumo().getDescricao());

		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		DateFormat f = DateFormat.getDateInstance();
		String abertura = f.format(data);
		numItem = numItem + 1;

		if (itens.getQuantidade() > 0) {
			itens.setPendente(itens.getQuantidade());

			itens.setData(abertura);
			itens.setRequisicao(getRequisicaoMaterial());
			itens.setNumItem(numItem);
			itens.setSitInsumo(tipoMoveService.buscarId(1));

			// impede que duplique os item no list
			if (itemRequisicaoList.contains(itens) == true) {
				itemRequisicaoList.remove(itens);
			}

			itemRequisicaoList.add(itens);
			itens = new ItemRequisicao();
			insumo = new CadastroInsumo();

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Nao foi Possivel Incluir : Verifique Qt Item ",
							null));

			itens = new ItemRequisicao();

		}
	}

	public void CancelaItens() {

		itemRequisicaoList.clear();
		numItem = 0;
	}

	public void ExcluirItem() {

		itemRequisicaoList.remove(itens);
		// atualisar numitem
		itens = new ItemRequisicao();
		insumo = new CadastroInsumo();

	}

	public List<CadastroInsumo> listInsumo(String query) { // metodo de pesquisa
															// do autocomplete

		insumoList = cadastroInsumoService.buscaQueryDescricao(query);

		return insumoList;

	}

	@PostConstruct
	public void init() {
		// cria o cabecalho da requisicao

	}

	public void excluir() {
		requisicaoMaterialService.excluir(requisicaoMaterial);
		requisicaoMaterialList = requisicaoMaterialService.buscaTodos();
		// limpando instancia do requisicaoMaterial
		requisicaoMaterial = new RequisicaoMaterial();
	}

	public void cancelarRequisicaoMaterial() throws DAOException,

	ServiceException {

		requisicaoMaterialService
				.cancelarRequisicaoMaterial(requisicaoMaterial);
	}

	public RequisicaoMaterial getRequisicaoMaterial() {

		Integer numreq = requisicaoMaterialService.buscarUltimo();
		numreq = numreq + 1;

		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		DateFormat f = DateFormat.getDateInstance();
		String abertura = f.format(data);

		requisicaoMaterial.setNum_req(numreq);// definindo o numero da
												// requisicao
		requisicaoMaterial.setAbertura(abertura);

		// verificar qual usuario se
		// for maritmo pegar rebocador
		// senao pegar setor

		if (getTripulante() != null) {

			requisicaoMaterial.setcCustoOrigem(centroCustoService
					.buscarRebocador(getRebocador()));
		} else {
			requisicaoMaterial.setcCustoOrigem(centroCustoService.buscarSetor(
					getLogado().getSetor(), getLogado().getBase()));
		}

		requisicaoMaterial.setSolicitante(logado);
		requisicaoMaterial.setSit(situacaoRequisicaoService.buscarId(1));

		return requisicaoMaterial;

	}

	public void setRequisicaoMaterial(RequisicaoMaterial requisicaoMaterial) {
		this.requisicaoMaterial = requisicaoMaterial;
	}

	public RequisicaoMaterialService getRequisicaoMaterialService() {
		return requisicaoMaterialService;
	}

	public void setRequisicaoMaterialService(
			RequisicaoMaterialService requisicaoMaterialService) {
		this.requisicaoMaterialService = requisicaoMaterialService;
	}

	public List<RequisicaoMaterial> getRequisicaoMaterialList() {
		return requisicaoMaterialList;
	}

	public void setRequisicaoMaterialList(
			List<RequisicaoMaterial> requisicaoMaterialList) {
		this.requisicaoMaterialList = requisicaoMaterialList;
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

	public ItemRequisicao getItens() {
		return itens;
	}

	public void setItens(ItemRequisicao itens) {
		this.itens = itens;
	}

	public List<ItemRequisicao> getItemRequisicaoList() {
		return itemRequisicaoList;
	}

	public void setItemRequisicaoList(List<ItemRequisicao> itemRequisicaoList) {
		this.itemRequisicaoList = itemRequisicaoList;
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

	public Integer getNumItem() {
		return numItem;
	}

	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}

	public Rebocador getRebocador() {

		rebocador = getTripulante().getTripulacao().getRebocador();

		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public Integer getVencido() throws ParseException {

		vencido = 0;
		Calendar cPrazo = Calendar.getInstance();
		Calendar cAtual = Calendar.getInstance();

		DateFormat df = DateFormat.getDateInstance();

		Date prazo = df.parse(getRequisicaoMaterial().getPrazo());
		cPrazo.setTime(prazo);

		for (int i = 0; i < 100000; i++) {

			if (cAtual.compareTo(cPrazo) == -1) {
				vencido = vencido + 1;
				cPrazo.add(Calendar.DAY_OF_MONTH, 1);
			} else if ((cAtual.compareTo(cPrazo) == 0)
					|| (cAtual.compareTo(cPrazo) == 1)) {
				vencido = 0;
				i = 9999999;

			}

		}

		return vencido;
	}
	
	public void setVencido(Integer vencido) {
		this.vencido = vencido;
	}

}
