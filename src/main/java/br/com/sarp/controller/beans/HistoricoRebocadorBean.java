package br.com.sarp.controller.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.HistoricoRebocador;
import br.com.sarp.model.entidades.Solicitacoes;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.CentroCustoService;
import br.com.sarp.model.service.HistoricoRebocadorService;
import br.com.sarp.model.service.RemessaMaterialService;
import br.com.sarp.model.service.RequisicaoMaterialService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class HistoricoRebocadorBean {

	private Usuario logado = new Usuario();
	private Tripulante tripulante = new Tripulante();
	private HistoricoRebocador historicoRebocador = new HistoricoRebocador();

	@Inject
	HistoricoRebocadorService historicoRebocadorService;
	@Inject
	TripulanteService tripulanteService;
	@Inject
	RequisicaoMaterialService requisicaoMaterialService;
	@Inject
	RemessaMaterialService remessaMaterialService;
	@Inject
	CentroCustoService centroCustoService;

	List<HistoricoRebocador> historicoRebocadorList = new ArrayList<>();
	List<String> tipohistoricoRebocadorList = new ArrayList<>();
	List<Solicitacoes> solicitacoesList;

	public void salvar() {

		try {
			Calendar c = Calendar.getInstance();
			Date dt = c.getTime();
			historicoRebocador.setData(dt);
			historicoRebocador.setRebocador(tripulante.getTripulacao()
					.getRebocador());
			historicoRebocador.setTripulante(tripulante);
			
			historicoRebocadorService.salvar(historicoRebocador);
			historicoRebocador = new HistoricoRebocador();
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

			e.printStackTrace();
		}

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

		tripulante = tripulanteService.buscarPorId(logado.getMatricula());

		return tripulante;
	}

	public void setTripulante(Tripulante tripulante) {
		this.tripulante = tripulante;
	}

	public HistoricoRebocador getHistoricoRebocador() {
		return historicoRebocador;
	}

	public void setHistoricoRebocador(HistoricoRebocador historicoRebocador) {
		this.historicoRebocador = historicoRebocador;
	}

	public List<HistoricoRebocador> getHistoricoRebocadorList() {

		historicoRebocadorList = historicoRebocadorService
				.buscaTodosRebocador(tripulante.getTripulacao().getRebocador());

		return historicoRebocadorList;
	}

	public void setHistoricoRebocadorList(
			List<HistoricoRebocador> historicoRebocadorList) {
		this.historicoRebocadorList = historicoRebocadorList;
	}

	public List<String> getTipohistoricoRebocadorList() {
		return tipohistoricoRebocadorList;
	}

	public void setTipohistoricoRebocadorList(
			List<String> tipohistoricoRebocadorList) {
		this.tipohistoricoRebocadorList = tipohistoricoRebocadorList;
	}

	public HistoricoRebocadorService getHistoricoRebocadorService() {
		return historicoRebocadorService;
	}

	public void setHistoricoRebocadorService(
			HistoricoRebocadorService historicoRebocadorService) {
		this.historicoRebocadorService = historicoRebocadorService;
	}

	public TripulanteService getTripulanteService() {
		return tripulanteService;
	}

	public void setTripulanteService(TripulanteService tripulanteService) {
		this.tripulanteService = tripulanteService;
	}

	public RequisicaoMaterialService getRequisicaoMaterialService() {
		return requisicaoMaterialService;
	}

	public void setRequisicaoMaterialService(
			RequisicaoMaterialService requisicaoMaterialService) {
		this.requisicaoMaterialService = requisicaoMaterialService;
	}

	public RemessaMaterialService getRemessaMaterialService() {
		return remessaMaterialService;
	}

	public void setRemessaMaterialService(
			RemessaMaterialService remessaMaterialService) {
		this.remessaMaterialService = remessaMaterialService;
	}

	public CentroCustoService getCentroCustoService() {
		return centroCustoService;
	}

	public void setCentroCustoService(CentroCustoService centroCustoService) {
		this.centroCustoService = centroCustoService;
	}

	public List<Solicitacoes> getSolicitacoesList() {
		return solicitacoesList;
	}

	public void setSolicitacoesList(List<Solicitacoes> solicitacoesList) {
		this.solicitacoesList = solicitacoesList;
	}

	
}
