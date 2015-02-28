package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Locais;
import br.com.sarp.model.service.LocaisService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class LocaisBean {

	private Locais locais = new Locais(); // recebe os dados do objeto na

	@Inject
	LocaisService locaisService;

	List<Locais> locaisList;

	public void salvar() {
		try {
			locaisService.salvar(locais);
			locais = new Locais();
			locaisList = locaisService.buscaTodos();
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
		locaisList = locaisService.buscaTodos();

	}

	public void excluir() {
		locaisService.excluir(locais);
		locaisList = locaisService.buscaTodos();
		// limpando instancia do locais
		locais = new Locais();
	}

	public Locais getLocais() {
		return locais;
	}

	public void setLocais(Locais locais) {
		this.locais = locais;
	}

	public LocaisService getLocaisService() {
		return locaisService;
	}

	public void setLocaisService(LocaisService locaisService) {
		this.locaisService = locaisService;
	}

	public List<Locais> getLocaisList() {
		return locaisList;
	}

	public void setLocaisList(List<Locais> locaisList) {
		this.locaisList = locaisList;
	}

	
}
