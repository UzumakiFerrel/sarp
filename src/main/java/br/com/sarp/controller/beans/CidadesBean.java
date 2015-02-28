package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Cidades;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.CidadesService;

@Controller
@ViewScoped
public class CidadesBean {

	private Cidades cidades = new Cidades(); // recebe os dados do objeto na tela

	@Inject
	CidadesService cidadesService;

	List<Cidades> cidadesList;

	public void salvar() {
		try {
			cidadesService.salvar(cidades);
			cidades = new Cidades();
			cidadesList = cidadesService.buscaTodos();
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
		cidadesList = cidadesService.buscaTodos();

	}

	public void excluir(){
		cidadesService.excluir(cidades);
		cidadesList = cidadesService.buscaTodos();
		//limpando instancia do cidades
		cidades=new Cidades();
	}
	
	
	public Cidades getCidades() {
		return cidades;
	}

	public void setCidades(Cidades cidades) {
		this.cidades = cidades;
	}

	public CidadesService getCidadesService() {
		return cidadesService;
	}

	public void setCidadesService(CidadesService cidadesService) {
		this.cidadesService = cidadesService;
	}

	public List<Cidades> getCidadesList() {
		return cidadesList;
	}

	public void setCidadesList(List<Cidades> cidadesList) {
		this.cidadesList = cidadesList;
	}

}
