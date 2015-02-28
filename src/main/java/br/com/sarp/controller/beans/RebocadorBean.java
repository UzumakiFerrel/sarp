package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.RebocadorService;

@Controller
@ViewScoped
public class RebocadorBean {

	private Rebocador rebocador = new Rebocador(); // recebe os dados do objeto
													// na

	@Inject
	RebocadorService rebocadorService;
	@Inject
	LoginBean loginBean;

	List<Rebocador> rebocadorList;
	List<Rebocador> rebocadorBaseList;

	public void salvar() {
		try {
			rebocadorService.salvar(rebocador);
			rebocador = new Rebocador();
			rebocadorList = rebocadorService.buscaTodos();
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
		rebocadorList = rebocadorService.buscaTodos();
	}

	public void excluir() {
		rebocadorService.excluir(rebocador);
		rebocadorList = rebocadorService.buscaTodos();
		// limpando instancia do rebocador
		rebocador = new Rebocador();
	}

	public Rebocador getRebocador() {
		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public RebocadorService getRebocadorService() {
		return rebocadorService;
	}

	public void setRebocadorService(RebocadorService rebocadorService) {
		this.rebocadorService = rebocadorService;
	}

	public List<Rebocador> getRebocadorList() {
		return rebocadorList;
	}

	public void setRebocadorList(List<Rebocador> rebocadorList) {
		this.rebocadorList = rebocadorList;
	}

	public List<Rebocador> getRebocadorBaseList() {

		rebocadorBaseList = rebocadorService.buscarTodosBase(loginBean.getLogado().getBase());

		return rebocadorBaseList;
	}

	public void setRebocadorBaseList(List<Rebocador> rebocadorBaseList) {
		this.rebocadorBaseList = rebocadorBaseList;
	}

}
