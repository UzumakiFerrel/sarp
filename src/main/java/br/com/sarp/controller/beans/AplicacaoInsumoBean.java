package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.AplicacaoInsumo;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.AplicacaoInsumoService;

@Controller
@ViewScoped
public class AplicacaoInsumoBean {

	private AplicacaoInsumo aplicacaoInsumo = new AplicacaoInsumo(); // recebe os dados do objeto na

	@Inject
	AplicacaoInsumoService aplicacaoInsumoService;

	List<AplicacaoInsumo> aplicacaoInsumoList;

	public void salvar() {
		try {
			aplicacaoInsumoService.salvar(aplicacaoInsumo);
			aplicacaoInsumo = new AplicacaoInsumo();
			aplicacaoInsumoList = aplicacaoInsumoService.buscaTodos();
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
		aplicacaoInsumoList = aplicacaoInsumoService.buscaTodos();

	}

	public void excluir(){
		aplicacaoInsumoService.excluir(aplicacaoInsumo);
		aplicacaoInsumoList = aplicacaoInsumoService.buscaTodos();
		//limpando instancia do aplicacaoInsumo
		aplicacaoInsumo=new AplicacaoInsumo();
	}
	
	
	public AplicacaoInsumo getAplicacaoInsumo() {
		return aplicacaoInsumo;
	}

	public void setAplicacaoInsumo(AplicacaoInsumo aplicacaoInsumo) {
		this.aplicacaoInsumo = aplicacaoInsumo;
	}

	public AplicacaoInsumoService getAplicacaoInsumoService() {
		return aplicacaoInsumoService;
	}

	public void setAplicacaoInsumoService(AplicacaoInsumoService aplicacaoInsumoService) {
		this.aplicacaoInsumoService = aplicacaoInsumoService;
	}

	public List<AplicacaoInsumo> getAplicacaoInsumoList() {
		return aplicacaoInsumoList;
	}

	public void setAplicacaoInsumoList(List<AplicacaoInsumo> aplicacaoInsumoList) {
		this.aplicacaoInsumoList = aplicacaoInsumoList;
	}

}
