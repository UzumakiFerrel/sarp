package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Unidades;
import br.com.sarp.model.service.UnidadesService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class UnidadesBean {

	private Unidades unidades = new Unidades(); // recebe os dados do objeto na

	@Inject
	UnidadesService unidadesService;

	List<Unidades> unidadesList;

	public void salvar() {
		try {
			unidadesService.salvar(unidades);
			unidades = new Unidades();
			unidadesList = unidadesService.buscaTodos();
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
		unidadesList = unidadesService.buscaTodos();

	}

	public void excluir() {
		unidadesService.excluir(unidades);
		unidadesList = unidadesService.buscaTodos();
		// limpando instancia do unidades
		unidades = new Unidades();
	}

	public Unidades getUnidades() {
		return unidades;
	}

	public void setUnidades(Unidades unidades) {
		this.unidades = unidades;
	}

	public UnidadesService getUnidadesService() {
		return unidadesService;
	}

	public void setUnidadesService(UnidadesService unidadesService) {
		this.unidadesService = unidadesService;
	}

	public List<Unidades> getUnidadesList() {
		return unidadesList;
	}

	public void setUnidadesList(List<Unidades> unidadesList) {
		this.unidadesList = unidadesList;
	}

	
}
