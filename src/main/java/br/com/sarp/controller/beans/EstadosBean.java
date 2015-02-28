package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Estados;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.EstadosService;

@Controller
@ViewScoped
public class EstadosBean {

	private Estados estados = new Estados(); // recebe os dados do objeto na

	@Inject
	EstadosService estadosService;

	List<Estados> estadosList;

	
	public void salvar() {
		try {
			estadosService.salvar(estados);
			estados = new Estados();
			estadosList = estadosService.buscaTodos();
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
		estadosList = estadosService.buscaTodos();

	}

	public void excluir(){
		estadosService.excluir(estados);
		estadosList = estadosService.buscaTodos();
		//limpando instancia do estados
		estados=new Estados();
	}
	
	
	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public EstadosService getEstadosService() {
		return estadosService;
	}

	public void setEstadosService(EstadosService estadosService) {
		this.estadosService = estadosService;
	}

	public List<Estados> getEstadosList() {
		return estadosList;
	}

	public void setEstadosList(List<Estados> estadosList) {
		this.estadosList = estadosList;
	}

}
