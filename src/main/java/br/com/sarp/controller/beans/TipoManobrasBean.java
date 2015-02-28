package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.TipoManobras;
import br.com.sarp.model.service.TipoManobrasService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class TipoManobrasBean {

	private TipoManobras tipoManobras = new TipoManobras(); // recebe os dados do objeto na

	@Inject
	TipoManobrasService tipoManobrasService;

	List<TipoManobras> tipoManobrasList;

	public void salvar() {
		try {
			tipoManobrasService.salvar(tipoManobras);
			tipoManobras = new TipoManobras();
			tipoManobrasList = tipoManobrasService.buscaTodos();
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
		tipoManobrasList = tipoManobrasService.buscaTodos();

	}

	public void excluir(){
		tipoManobrasService.excluir(tipoManobras);
		tipoManobrasList = tipoManobrasService.buscaTodos();
		//limpando instancia do tipoManobras
		tipoManobras=new TipoManobras();
	}

	public TipoManobras getTipoManobras() {
		return tipoManobras;
	}

	public void setTipoManobras(TipoManobras tipoManobras) {
		this.tipoManobras = tipoManobras;
	}

	public TipoManobrasService getTipoManobrasService() {
		return tipoManobrasService;
	}

	public void setTipoManobrasService(TipoManobrasService tipoManobrasService) {
		this.tipoManobrasService = tipoManobrasService;
	}

	public List<TipoManobras> getTipoManobrasList() {
		return tipoManobrasList;
	}

	public void setTipoManobrasList(List<TipoManobras> tipoManobrasList) {
		this.tipoManobrasList = tipoManobrasList;
	}

	
	
}
