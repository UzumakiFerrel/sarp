package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Tripulacao;
import br.com.sarp.model.service.RebocadorService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TripulacaoService;

@Controller
@ViewScoped
public class TripulacaoBean {

	private Tripulacao tripulacao = new Tripulacao(); // recebe os dados do
														// objeto na tela
	@Inject
	TripulacaoService tripulacaoService;

	private List<Tripulacao> tripulacaoList;

	private List<Rebocador> rebocadorList;

	@Inject
	private RebocadorService rebocadorService;

	public void buscarTodosBase(ValueChangeEvent evento) {

		if (evento.getNewValue() != evento.getOldValue()) {

			Base base = (Base) evento.getNewValue();

			rebocadorList = rebocadorService.buscarTodosBase(base);

		}

	}

	public void salvar() {
		try {
			tripulacaoService.salvar(tripulacao);
			tripulacao = new Tripulacao();
			tripulacaoList = tripulacaoService.buscaTodos();
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
		tripulacaoList = tripulacaoService.buscaTodos();

	}

	public void excluir() {

		tripulacaoService.excluir(tripulacao);
		tripulacaoList = tripulacaoService.buscaTodos();
		// limpando instancia do tripulacao
		tripulacao = new Tripulacao();
	}

	public Tripulacao getTripulacao() {
		return tripulacao;
	}

	public void setTripulacao(Tripulacao tripulacao) {
		this.tripulacao = tripulacao;
	}

	public TripulacaoService getTripulacaoService() {
		return tripulacaoService;
	}

	public void setTripulacaoService(TripulacaoService tripulacaoService) {
		this.tripulacaoService = tripulacaoService;
	}

	public List<Tripulacao> getTripulacaoList() {
		return tripulacaoList;
	}

	public void setTripulacaoList(List<Tripulacao> tripulacaoList) {
		this.tripulacaoList = tripulacaoList;
	}

	public List<Rebocador> getRebocadorList() {
		return rebocadorList;
	}

	public void setRebocadorList(List<Rebocador> rebocadorList) {
		this.rebocadorList = rebocadorList;
	}

	public RebocadorService getRebocadorService() {
		return rebocadorService;
	}

	public void setRebocadorService(RebocadorService rebocadorService) {
		this.rebocadorService = rebocadorService;
	}

	
}
