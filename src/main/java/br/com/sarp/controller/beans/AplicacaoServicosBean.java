package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.AplicacaoServicos;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.AplicacaoServicosService;

@Controller
@ViewScoped
public class AplicacaoServicosBean {

	private AplicacaoServicos aplicacaoServicos = new AplicacaoServicos(); // recebe os dados do objeto na

	@Inject
	AplicacaoServicosService aplicacaoServicosService;

	List<AplicacaoServicos> aplicacaoServicosList;

	public void salvar() {
		try {
			aplicacaoServicosService.salvar(aplicacaoServicos);
			aplicacaoServicos = new AplicacaoServicos();
			aplicacaoServicosList = aplicacaoServicosService.buscaTodos();
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
		aplicacaoServicosList = aplicacaoServicosService.buscaTodos();

	}
	

	public void excluir(){
		aplicacaoServicosService.excluir(aplicacaoServicos);
		aplicacaoServicosList = aplicacaoServicosService.buscaTodos();
		//limpando instancia do aplicacaoServicos
		aplicacaoServicos=new AplicacaoServicos();
	}

	public AplicacaoServicos getAplicacaoServicos() {
		return aplicacaoServicos;
	}

	public void setAplicacaoServicos(AplicacaoServicos aplicacaoServicos) {
		this.aplicacaoServicos = aplicacaoServicos;
	}

	public AplicacaoServicosService getAplicacaoServicosService() {
		return aplicacaoServicosService;
	}

	public void setAplicacaoServicosService(
			AplicacaoServicosService aplicacaoServicosService) {
		this.aplicacaoServicosService = aplicacaoServicosService;
	}

	public List<AplicacaoServicos> getAplicacaoServicosList() {
		return aplicacaoServicosList;
	}

	public void setAplicacaoServicosList(
			List<AplicacaoServicos> aplicacaoServicosList) {
		this.aplicacaoServicosList = aplicacaoServicosList;
	}
	
	
	
	

	
	
}
