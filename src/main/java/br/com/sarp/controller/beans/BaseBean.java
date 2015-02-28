
package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Cidades;
import br.com.sarp.model.entidades.Estados;
import br.com.sarp.model.service.BaseService;
import br.com.sarp.model.service.CidadesService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class BaseBean {

	private Base base = new Base(); // recebe os dados do objeto na

	@Inject
	BaseService baseService;

	List<Base> baseList;

	private List<Cidades> cidadesList;

	@Inject
	private CidadesService cidadesService;

	public void buscarCidades(ValueChangeEvent evento) { // verifica se usuario definiu estado para ativar o ajax
		                                                 //e atualizar as cidades

		if (evento.getNewValue() != evento.getOldValue()) {
			Estados estado = (Estados) evento.getNewValue();

			cidadesList = cidadesService.buscarCidades(estado);
		}

	}
	

	public void salvar() {
		try {
			baseService.salvar(base);
			base = new Base();
			baseList = baseService.buscaTodos();
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
		baseList = baseService.buscaTodos();

	}

	public void excluir() {
		baseService.excluir(base);
		baseList = baseService.buscaTodos();
		// limpando instancia do base
		base = new Base();
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public List<Base> getBaseList() {
		return baseList;
	}

	public void setBaseList(List<Base> baseList) {
		this.baseList = baseList;
	}

	public List<Cidades> getCidadesList() {
		return cidadesList;
	}

	public void setCidadesList(List<Cidades> cidadesList) {
		this.cidadesList = cidadesList;
	}

	public CidadesService getCidadesService() {
		return cidadesService;
	}

	public void setCidadesService(CidadesService cidadesService) {
		this.cidadesService = cidadesService;
	}


	
}
