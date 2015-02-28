package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Prioridades;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.PrioridadesService;

@Controller
@ViewScoped
public class PrioridadesBean {

	private Prioridades prioridades = new Prioridades(); // recebe os dados do objeto na

	@Inject
	PrioridadesService prioridadesService;

	List<Prioridades> prioridadesList;

	public void salvar() {
		try {
			prioridadesService.salvar(prioridades);
			prioridades = new Prioridades();
			prioridadesList = prioridadesService.buscaTodos();
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
		prioridadesList = prioridadesService.buscaTodos();

	}

	public void excluir(){
		prioridadesService.excluir(prioridades);
		prioridadesList = prioridadesService.buscaTodos();
		//limpando instancia do prioridades
		prioridades=new Prioridades();
	}
	
	public Prioridades getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(Prioridades prioridades) {
		this.prioridades = prioridades;
	}

	public PrioridadesService getPrioridadesService() {
		return prioridadesService;
	}

	public void setPrioridadesService(PrioridadesService prioridadesService) {
		this.prioridadesService = prioridadesService;
	}

	public List<Prioridades> getPrioridadesList() {
		return prioridadesList;
	}

	public void setPrioridadesList(List<Prioridades> prioridadesList) {
		this.prioridadesList = prioridadesList;
	}

}
