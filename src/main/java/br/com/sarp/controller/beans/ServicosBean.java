package br.com.sarp.controller.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Controller;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Servicos;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.ItemRequisicao;
import br.com.sarp.model.entidades.RequisicaoMaterial;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.ServicosService;
import br.com.sarp.model.service.CentroCustoService;
import br.com.sarp.model.service.ItemRequisicaoService;
import br.com.sarp.model.service.RequisicaoMaterialService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class ServicosBean {

	private Usuario logado = new Usuario();
	private Tripulante tripulante = new Tripulante();
	private Boolean todos;
	private CentroCusto cCusto = new CentroCusto();
	private Servicos servicos = new Servicos(); // recebe os
																	// dados do
																	// objeto

	private List<Servicos> servicosList;
	private List<Servicos> insumosPendentesLista;
	private List<RequisicaoMaterial> requisicaoMaterialLista;
	private List<CentroCusto> cCustoLista;
	private List<ItemRequisicao> itemRequisicaoLista;
	private List<ItemRequisicao> listaItensPendente = new ArrayList<>();;

	@Inject
	private CentroCustoService cCustoService;
	@Inject
	private ServicosService servicosService;
	@Inject
	private RequisicaoMaterialService requisicaoMaterialService;
	@Inject
	private TripulanteService tripulanteService;
	@Inject
	private ItemRequisicaoService itemRequisicaoService;

	public void salvar() {
		try {
			servicosService.salvar(servicos);
			servicos = new Servicos();
			servicosList = servicosService.buscaTodos();
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

	@PostConstruct
	public void init() {
		servicosList = servicosService.buscaTodos();
		// gerarlistapendentes();
	}

	public void cCustoList() {

		cCustoLista = new ArrayList<>();

		if ((logado.getSetor().getCd_setor() == 1)) { // verifica
														// se
														// he
														// tripulante

			cCustoLista.add(cCustoService.buscarRebocador(tripulante
					.getTripulacao().getRebocador()));

		} else

		if ((logado.getSetor().getCd_setor() != 1)) {

			cCustoLista = cCustoService.buscaTodos();
		}
	}

	public void gerarlistapendentes(ValueChangeEvent evento) {
	
		listaItensPendente.clear();
		
		if (evento.getNewValue() != evento.getOldValue()) {
			cCusto = new CentroCusto();
			cCusto = null;
			requisicaoMaterialLista = new ArrayList<>();
			itemRequisicaoLista = new ArrayList<>();

			try {
				cCusto = (CentroCusto) evento.getNewValue();
					requisicaoMaterialLista = requisicaoMaterialService
							.buscarcCustoSituacao(cCusto, 1);

					for (RequisicaoMaterial rm : requisicaoMaterialLista) {
						itemRequisicaoLista = itemRequisicaoService
								.buscarItensSituacao(rm.getNum_req(), 1);

						for (ItemRequisicao item : itemRequisicaoLista) {
							listaItensPendente.add(item);
						}

					}

			} catch (NullPointerException e) {
				e.printStackTrace();

			}
		}

	}

	public void gerarlistapendentesbase(ValueChangeEvent evento) {

		listaItensPendente.clear();
		
		if (evento.getNewValue() != evento.getOldValue()) {
			cCusto = new CentroCusto();
			cCusto = null;
			requisicaoMaterialLista = new ArrayList<>();
			itemRequisicaoLista = new ArrayList<>();

			todos = (Boolean) evento.getNewValue();
			if (todos == true && logado.getRegra().equalsIgnoreCase("ROLE_ADMIN")) {

				requisicaoMaterialLista = requisicaoMaterialService
						.buscarBaseSituacao(logado.getBase(), 1);
				for (RequisicaoMaterial rm : requisicaoMaterialLista) {

					itemRequisicaoLista = itemRequisicaoService
							.buscarItensSituacao(rm.getNum_req(), 1);

					for (ItemRequisicao item : itemRequisicaoLista) {
						listaItensPendente.add(item);
					}
				}

			} else  if(logado.getRegra().equalsIgnoreCase("ROLE_ADMIN")==false){
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Salvo com sucesso !", null));

			}

		}

	}

	public void excluir() throws DAOException, PSQLException {

		servicosService.excluir(servicos);
		servicosList = servicosService.buscaTodos();
		// limpando instancia do servicos
		servicos = new Servicos();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Item Excluido com Sucesso !", null));

	}

	public Servicos getServicos() {
		return servicos;
	}

	public void setServicos(Servicos servicos) {
		this.servicos = servicos;
	}

	public ServicosService getServicosService() {
		return servicosService;
	}

	public void setServicosService(
			ServicosService servicosService) {
		this.servicosService = servicosService;
	}

	public List<Servicos> getServicosList() {
		return servicosList;
	}

	public void setServicosList(List<Servicos> servicosList) {
		this.servicosList = servicosList;
	}

	public List<Servicos> getInsumosPendentesLista() {
		return insumosPendentesLista;
	}

	public void setInsumosPendentesLista(
			List<Servicos> insumosPendentesLista) {
		this.insumosPendentesLista = insumosPendentesLista;
	}

	public List<RequisicaoMaterial> getRequisicaoMaterialLista() {
		return requisicaoMaterialLista;
	}

	public void setRequisicaoMaterialLista(
			List<RequisicaoMaterial> requisicaoMaterialLista) {
		this.requisicaoMaterialLista = requisicaoMaterialLista;
	}

	public RequisicaoMaterialService getRequisicaoMaterialService() {
		return requisicaoMaterialService;
	}

	public void setRequisicaoMaterialService(
			RequisicaoMaterialService requisicaoMaterialService) {
		this.requisicaoMaterialService = requisicaoMaterialService;
	}

	public CentroCustoService getcCustoService() {
		return cCustoService;
	}

	public void setcCustoService(CentroCustoService cCustoService) {
		this.cCustoService = cCustoService;
	}

	public Tripulante getTripulante() {

		tripulante = tripulanteService.buscarPorId(logado.getMatricula());

		return tripulante;
	}

	public void setTripulante(Tripulante tripulante) {
		this.tripulante = tripulante;
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

	public List<CentroCusto> getcCustoLista() {
		cCustoList();
		return cCustoLista;
	}

	public void setcCustoLista(List<CentroCusto> cCustoLista) {
		this.cCustoLista = cCustoLista;
	}

	public List<ItemRequisicao> getItemRequisicaoLista() {
		return itemRequisicaoLista;
	}

	public void setItemRequisicaoLista(List<ItemRequisicao> itemRequisicaoLista) {
		this.itemRequisicaoLista = itemRequisicaoLista;
	}

	public CentroCusto getcCusto() {
		return cCusto;
	}

	public void setcCusto(CentroCusto cCusto) {
		this.cCusto = cCusto;
	}

	public Boolean getTodos() {
		return todos;
	}

	public void setTodos(Boolean todos) {
		this.todos = todos;
	}

	public List<ItemRequisicao> getListaItensPendente() {
		return listaItensPendente;
	}

	public void setListaItensPendente(List<ItemRequisicao> listaItensPendente) {
		this.listaItensPendente = listaItensPendente;
	}
	
	

}
