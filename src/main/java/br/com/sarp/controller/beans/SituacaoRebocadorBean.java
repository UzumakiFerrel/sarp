package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.SituacaoRebocador;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SituacaoRebocadorService;

@Controller
@ViewScoped
public class SituacaoRebocadorBean {

	private SituacaoRebocador situacaoRebocador = new SituacaoRebocador(); // recebe os dados do objeto na

	@Inject
	SituacaoRebocadorService situacaoRebocadorService;

	List<SituacaoRebocador> situacaoRebocadorList;

	public void salvar() {
		try {
			situacaoRebocadorService.salvar(situacaoRebocador);
			situacaoRebocador = new SituacaoRebocador();
			situacaoRebocadorList = situacaoRebocadorService.buscaTodos();
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
		situacaoRebocadorList = situacaoRebocadorService.buscaTodos();

	}

	public void excluir(){
		situacaoRebocadorService.excluir(situacaoRebocador);
		situacaoRebocadorList = situacaoRebocadorService.buscaTodos();
		//limpando instancia do situacaoRebocador
		situacaoRebocador=new SituacaoRebocador();
	}

	public SituacaoRebocador getSituacaoRebocador() {
		return situacaoRebocador;
	}

	public void setSituacaoRebocador(SituacaoRebocador situacaoRebocador) {
		this.situacaoRebocador = situacaoRebocador;
	}

	public SituacaoRebocadorService getSituacaoRebocadorService() {
		return situacaoRebocadorService;
	}

	public void setSituacaoRebocadorService(
			SituacaoRebocadorService situacaoRebocadorService) {
		this.situacaoRebocadorService = situacaoRebocadorService;
	}

	public List<SituacaoRebocador> getSituacaoRebocadorList() {
		return situacaoRebocadorList;
	}

	public void setSituacaoRebocadorList(
			List<SituacaoRebocador> situacaoRebocadorList) {
		this.situacaoRebocadorList = situacaoRebocadorList;
	}
	

}
