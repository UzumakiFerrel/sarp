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
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.CadastroInsumo;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.ItemRequisicao;
import br.com.sarp.model.entidades.RequisicaoMaterial;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.CadastroInsumoService;
import br.com.sarp.model.service.CentroCustoService;
import br.com.sarp.model.service.ItemRequisicaoService;
import br.com.sarp.model.service.RequisicaoMaterialService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class CadastroInsumoBean {

	private Usuario logado = new Usuario();
	private Tripulante tripulante = new Tripulante();
	private Boolean todos;
	private CentroCusto cCusto = new CentroCusto();
	private CadastroInsumo cadastroInsumo = new CadastroInsumo(); // recebe os
																	// dados do
																	// objeto
	private List<CadastroInsumo> cadastroInsumoList;
	private List<CadastroInsumo> insumosPendentesLista;
	private List<RequisicaoMaterial> requisicaoMaterialLista;
	private List<CentroCusto> cCustoLista;
	private List<ItemRequisicao> itemRequisicaoLista;
	private List<ItemRequisicao> listaItensPendente = new ArrayList<>();

	@Inject
	private CentroCustoService cCustoService;
	@Inject
	private CadastroInsumoService cadastroInsumoService;
	@Inject
	private RequisicaoMaterialService requisicaoMaterialService;
	@Inject
	private TripulanteService tripulanteService;
	@Inject
	private ItemRequisicaoService itemRequisicaoService;

	public void salvar() {
		try {
			cadastroInsumoService.salvar(cadastroInsumo);
			cadastroInsumo = new CadastroInsumo();
			cadastroInsumoList = cadastroInsumoService.buscaTodos();
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
		cadastroInsumoList = cadastroInsumoService.buscaTodos();
		// gerarlistapendentes();
	}

	public void cCustoList() {

		cCustoLista = new ArrayList<>();

		if ((logado.getSetor().getCd_setor() == 1)) { // verifica
														// se
														// he
														// tripulante

			cCustoLista.add(cCustoService.buscarRebocador(getTripulante()
					.getTripulacao().getRebocador()));

		} else

		if ((logado.getSetor().getCd_setor() != 1)) {

			cCustoLista = cCustoService.buscaTodos();
		}
	}

	public void gerarlistapendentes(ValueChangeEvent evento)
			throws ParseException {

		listaItensPendente.clear();

		if (evento.getNewValue() != evento.getOldValue()) {
			cCusto = new CentroCusto();
			cCusto = null;
			requisicaoMaterialLista = new ArrayList<>();
			itemRequisicaoLista = new ArrayList<>();
			Integer vencido;

			try {
				cCusto = (CentroCusto) evento.getNewValue();
				requisicaoMaterialLista = requisicaoMaterialService
						.buscarcCustoSituacao(cCusto, 1);

				Calendar cPrazo = Calendar.getInstance();
				Calendar cAtual = Calendar.getInstance();
				cAtual.clear(Calendar.HOUR_OF_DAY);
				cAtual.clear(Calendar.MINUTE);
				cAtual.clear(Calendar.SECOND);
				
				DateFormat df = DateFormat.getDateInstance();

				for (RequisicaoMaterial rm : requisicaoMaterialLista) {
					// atualisar o valor da variavel vencido
					vencido = 0;
					
					Date prazo = df.parse(rm.getPrazo());
					cPrazo.setTime(prazo);
					if ((cAtual.compareTo(cPrazo) == 1)) {
						
						for (int i = 0; i<365; i++) {

							vencido = vencido + 1;

							cPrazo.add(Calendar.DAY_OF_MONTH, 1);

							if ((cAtual.compareTo(cPrazo)==-1)) {
								i = 999;
							}
						}
					}
					
					vencido=vencido-1;
					rm.setVencido(vencido);
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
			if (todos == true
					&& logado.getRegra().equalsIgnoreCase("ROLE_ADMIN")) {

				requisicaoMaterialLista = requisicaoMaterialService
						.buscarBaseSituacao(logado.getBase(), 1);
				for (RequisicaoMaterial rm : requisicaoMaterialLista) {

					itemRequisicaoLista = itemRequisicaoService
							.buscarItensSituacao(rm.getNum_req(), 1);

					for (ItemRequisicao item : itemRequisicaoLista) {
						listaItensPendente.add(item);
					}
				}

			} else if (logado.getRegra().equalsIgnoreCase("ROLE_ADMIN") == false) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Salvo com sucesso !", null));

			}

		}

	}

	public void excluir() {

		try {
			cadastroInsumoService.excluir(cadastroInsumo);
			cadastroInsumoList = cadastroInsumoService.buscaTodos();
			// limpando instancia do cadastroInsumo
			cadastroInsumo = new CadastroInsumo();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Item Excluido com Sucesso !", null));

		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Nao foi possivel Excluir ! " + e.getMessage(),
							null));
			// codigo que envia msg de erro pra tela
			e.printStackTrace();
		} catch (PSQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public CadastroInsumo getCadastroInsumo() {
		return cadastroInsumo;
	}

	public void setCadastroInsumo(CadastroInsumo cadastroInsumo) {
		this.cadastroInsumo = cadastroInsumo;
	}

	public CadastroInsumoService getCadastroInsumoService() {
		return cadastroInsumoService;
	}

	public void setCadastroInsumoService(
			CadastroInsumoService cadastroInsumoService) {
		this.cadastroInsumoService = cadastroInsumoService;
	}

	public List<CadastroInsumo> getCadastroInsumoList() {
		return cadastroInsumoList;
	}

	public void setCadastroInsumoList(List<CadastroInsumo> cadastroInsumoList) {
		this.cadastroInsumoList = cadastroInsumoList;
	}

	public List<CadastroInsumo> getInsumosPendentesLista() {
		return insumosPendentesLista;
	}

	public void setInsumosPendentesLista(
			List<CadastroInsumo> insumosPendentesLista) {
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
